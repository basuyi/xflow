package net.ms.designer.editors.workflow.figures;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.AbstractRouter;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.Path;
import org.eclipse.draw2d.graph.ShortestPathRouter;

/**
 * Provides a {@link Connection} with a Shortest Path route that does not intersect
 * obstacles between the Connection's source and target anchors.
 * @author liuchunxia
 * @since 3.0
 */
public final class ShortestPathConnectionRouter	extends AbstractRouter
{	
	public static class LayoutWrapper implements LayoutManager 
	{
		private final LayoutManager delegate;
		private final ShortestPathConnectionRouter router;
		public LayoutWrapper(LayoutManager delegate, ShortestPathConnectionRouter router) 
		{
			this.delegate = delegate;
			this.router = router;
		}
		
		public Object getConstraint(IFigure child)
		{
			return delegate.getConstraint(child);
		}
		
		public Dimension getMinimumSize(IFigure container, int wHint, int hHint) 
		{
			return delegate.getMinimumSize(container, wHint, hHint);
		}
		
		public Dimension getPreferredSize(IFigure container, int wHint, int hHint) 
		{
			return delegate.getPreferredSize(container, wHint, hHint);
		}
		
		public void invalidate() 
		{
			delegate.invalidate();
		}
		
		public void layout(IFigure container)
		{
			delegate.layout(container);
			router.processLayout();
		}
		
		public void remove(IFigure child) 
		{
			router.removeChild(child);
			delegate.remove(child);
		}
		
		public void setConstraint(IFigure child, Object constraint) 
		{
			delegate.setConstraint(child, constraint);
			router.addChild(child);
		}
	}

	private Map constraintMap = new HashMap();
	private Map figuresToBounds;
	private Map connectionToPaths;
	private boolean isDirty;
	private ShortestPathRouter algorithm = new ShortestPathRouter();
	private IFigure container;
	private Set staleConnections = new HashSet();
	
	private FigureListener figureListener = new FigureListener() 
	{
		public void figureMoved(IFigure source)
		{
			Rectangle newBounds = source.getBounds().getCopy();
			if (algorithm.updateObstacle((Rectangle)figuresToBounds.get(source), newBounds)) 
			{
				queueSomeRouting();
				isDirty = true;
			}
				
			figuresToBounds.put(source, newBounds);
		}
	};
	private boolean ignoreInvalidate;
	
	/**
	 * Creates a new shortest path router with the given container. The container
	 * should contain all the obstacles the connetions will wrap around.
	 * 
	 * @param container the container
	 */
	public ShortestPathConnectionRouter(IFigure container)
	{
		isDirty = false;
		algorithm = new ShortestPathRouter();
		this.container = container;
	}
	
	void queueSomeRouting()
	{
		if (connectionToPaths == null || connectionToPaths.isEmpty())
			return;
		try 
		{
			ignoreInvalidate = true;
			((Connection)connectionToPaths.keySet().iterator().next()).revalidate();
		} 
		finally 
		{
			ignoreInvalidate = false;
		}
	}
	
	void addChild(IFigure child) 
	{
		if (connectionToPaths == null)
			return;
		if (figuresToBounds.containsKey(child))
			return;
		Rectangle bounds = child.getBounds().getCopy();
		algorithm.addObstacle(bounds);
		figuresToBounds.put(child, bounds);
		child.addFigureListener(figureListener);
		isDirty = true;
	}
	
	void removeChild(IFigure child) 
	{
		if (connectionToPaths == null)
			return;
		Rectangle bounds = child.getBounds().getCopy();
		boolean change = algorithm.removeObstacle(bounds);
		figuresToBounds.remove(child);
		child.removeFigureListener(figureListener);
		if (change) 
		{
			isDirty = true;
			queueSomeRouting();
		}
	}
	
	private void hookAll() 
	{
		figuresToBounds = new HashMap();
		for (int i = 0; i < container.getChildren().size(); i++)
			addChild((IFigure)container.getChildren().get(i));
	}
	
	private void unhookAll() 
	{
		if (figuresToBounds != null) 
		{
			Iterator figureItr = figuresToBounds.keySet().iterator();
			while (figureItr.hasNext())
			{
				//Must use iterator's remove to avoid concurrent modification
				IFigure child = (IFigure)figureItr.next();
				figureItr.remove();
				removeChild(child);
			}
			figuresToBounds = null;
		}
	}
	
	/**
	 * Gets the constraint for the given {@link Connection}.  The constraint is the paths
	 * list of bend points for this connection.
	 *
	 * @param connection The connection whose constraint we are retrieving
	 * @return The constraint
	 */
	public Object getConstraint(Connection connection)
	{
		return constraintMap.get(connection);
	}
	
	/**
	 * @see org.eclipse.draw2d.ConnectionRouter#invalidate(org.eclipse.draw2d.Connection)
	 */
	public void invalidate(Connection connection) 
	{
		if (ignoreInvalidate)
			return;
		staleConnections.add(connection);
		isDirty = true;
	}
	
	/**
	 * @see org.eclipse.draw2d.ConnectionRouter#remove(org.eclipse.draw2d.Connection)
	 */
	public void remove(Connection connection)
	{
		Path path = (Path)connectionToPaths.remove(connection);
		staleConnections.remove(connection);
		constraintMap.remove(connection);
		algorithm.removePath(path);
		isDirty = true;
		if (connectionToPaths.isEmpty())
		{
			unhookAll();
			connectionToPaths = null;
		} 
		else 
		{
			//Make sure one of the remaining is revalidated so that we can re-route again.
			queueSomeRouting();
		}
	}
	
	private void processLayout() 
	{
		if (staleConnections.isEmpty())
			return;
		((Connection)staleConnections.iterator().next()).revalidate();
	}
	
	/**
	 * @see ConnectionRouter#route(Connection)
	 */
	public void route(Connection conn) 
	{
		if (isDirty) 
		{
			ignoreInvalidate = true;
			processStaleConnections();
			isDirty = false;
			List updated = algorithm.solve();
			Connection current;
			for (int i = 0; i < updated.size(); i++) 
			{
				Path path = (Path) updated.get(i);
				current = (Connection)path.data;
				current.revalidate();
				
				PointList points = path.getPoints().getCopy();
				Point ref1, ref2, start, end;
				ref1 = new PrecisionPoint(points.getPoint(1));
				ref2 = new PrecisionPoint(points.getPoint(points.size() - 2));
				current.translateToAbsolute(ref1);
				current.translateToAbsolute(ref2);
				
				start = current.getSourceAnchor().getLocation(ref1).getCopy();
				end = current.getTargetAnchor().getLocation(ref2).getCopy();
				
				current.translateToRelative(start);
				current.translateToRelative(end);
				points.setPoint(start, 0);
				points.setPoint(end, points.size() - 1);
	
				current.setPoints(points);
			}
			ignoreInvalidate = false;
		}
	}
	
	/**
	 * @since 3.0
	 */
	private void processStaleConnections() 
	{
		Iterator iter = staleConnections.iterator();
		if (iter.hasNext() && connectionToPaths == null) 
		{
			connectionToPaths = new HashMap();
			hookAll();
		}
	
		while (iter.hasNext()) 
		{
			Connection conn = (Connection)iter.next();
	
			Path path = (Path)connectionToPaths.get(conn);
			if (path == null) 
			{
				path = new Path(conn);
				connectionToPaths.put(conn, path);
				algorithm.addPath(path);
			}
	
			List constraint = (List)getConstraint(conn);
			if (constraint == null)
				constraint = Collections.EMPTY_LIST;
			
			Point start = conn.getSourceAnchor().getReferencePoint().getCopy();
			Point end = conn.getTargetAnchor().getReferencePoint().getCopy();
	
			container.translateToRelative(start);
			container.translateToRelative(end);
			
			path.setStartPoint(start);
			path.setEndPoint(end);
			
			if (!constraint.isEmpty()) 
			{
				PointList bends = new PointList(constraint.size());
				for (int i = 0; i < constraint.size(); i++) 
				{
					Bendpoint bp = (Bendpoint)constraint.get(i);
					bends.addPoint(bp.getLocation());
				}
				path.setBendPoints(bends);
			} 
			else
				path.setBendPoints(null);
			
			isDirty |= path.isDirty;
		}
		staleConnections.clear();
	}
	
	public void setConstraint(Connection connection, Object constraint) 
	{
		//Connection.setConstraint() already calls revalidate, so we know that a
		// route() call will follow.
		staleConnections.add(connection);
		constraintMap.put(connection, constraint);
		isDirty = true;
	}

}
