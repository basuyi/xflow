package net.ms.designer.editors.componentdetail.commands;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import net.ms.designer.editors.componentdetail.models.AutoNumField;
import net.ms.designer.editors.componentdetail.models.ChildTable;
import net.ms.designer.editors.componentdetail.models.CommonField;
import net.ms.designer.editors.componentdetail.models.ComponentTable;
import net.ms.designer.editors.componentdetail.models.Container;
import net.ms.designer.editors.componentdetail.models.DateField;
import net.ms.designer.editors.componentdetail.models.Element;
import net.ms.designer.editors.componentdetail.models.EnumField;
import net.ms.designer.editors.componentdetail.models.FloatField;
import net.ms.designer.editors.componentdetail.models.FlowField;
import net.ms.designer.editors.componentdetail.models.IntegerField;
import net.ms.designer.editors.componentdetail.models.Label;
import net.ms.designer.editors.componentdetail.models.StringField;
import net.ms.designer.editors.componentdetail.models.Table;
import net.ms.designer.editors.componentdetail.models.Wire;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;


/**
 * @author lili
 * @version 1.1.0
 *defind the CopyCommand
 */
public class CopyCommand extends Command
{

	private List parts, newTopLevelParts, newConnections;

	private Container parent;
	
	private ComponentTable originalMainTable = null;

	private Map bounds, indices, connectionPartMap;

	//private ChangeGuideCommand vGuideCommand, hGuideCommand;
	//private LogicGuide hGuide, vGuide;
	private int hAlignment, vAlignment;

	private Point originalPoint;

	/**
	 * consturt of CopyCommand()
	 *
	 */
	public CopyCommand() 
	{
		super("Copy"); 
		parts = new LinkedList();
	}

	/**
	 * 
	 * @param part
	 * @param newBounds
	 */
	public void addPart(Element part, Rectangle newBounds) 
	{
		parts.add(part);
		if (bounds == null) 
		{
			bounds = new HashMap();
		}
		bounds.put(part, newBounds);
	}

	/**
	 * 
	 * @param part
	 * @param index
	 */
	public void addPart(Element part, int index) 
	{
		parts.add(part);
		if (indices == null) 
		{
			indices = new HashMap();
		}
		indices.put(part, new Integer(index));
	}

	protected void copyPart(Element oldPart, Container newParent,
			Rectangle newBounds, List newConnections, Map connectionPartMap,
			int index) 
	{
		Element newPart = null;

		if (oldPart instanceof AutoNumField) 
		{
			newPart = new AutoNumField();
		} else if (oldPart instanceof ComponentTable) 
		{
			if (newParent.hasMainTable()) 
			{
					ComponentTable kcgCt = newParent.getMainTable();
					
					Vector inConns = kcgCt.getTargetConnections();
					for(int i=0;i<inConns.size();i++)
					{
						Wire w = (Wire)inConns.get(i);
						newPart.connectInput(w);
					
					originalMainTable = kcgCt;
					
					newParent.removeChild(kcgCt);
					newParent.setHasMainTable(false);
				}
			}
		} else if (oldPart instanceof ChildTable) 
		{
			newPart = new ChildTable();
		} 
		else if (oldPart instanceof CommonField)
		{
			newPart = new CommonField();
		}
	
		else if (oldPart instanceof DateField) 
		{
			newPart = new DateField();
		} else if (oldPart instanceof FloatField) 
		{
			newPart = new FloatField();
		} else if (oldPart instanceof Label) 
		{
			newPart = new Label();
		} else if (oldPart instanceof IntegerField) 
		{
			newPart = new IntegerField();
		} else if (oldPart instanceof StringField) 
		{
			newPart = new StringField();
		}else if (oldPart instanceof FlowField)
		{
			newPart = new FlowField();
		}else if (oldPart instanceof EnumField)
		{
			newPart = new EnumField();
		} else if (oldPart instanceof Table) 
		{
			newPart = new Table();
		} 
		
		if(oldPart instanceof Element)
		{
			newPart.setName(oldPart.getName());
//			newPart.setFieldLabel(oldPart.getFieldLabel());
			newPart.setIsHidden(oldPart.getIsHidden());
			newPart.setIsReadOnly(oldPart.getIsReadOnly());
		}

		if (oldPart instanceof Container) 
		{
			Iterator i = ((Container) oldPart).getChildren().iterator();
			while (i.hasNext()) 
			{
				// for children they will not need new bounds
				copyPart((Element) i.next(), (Container) newPart, null,
						newConnections, connectionPartMap, -1);
			}
		}

		Iterator i = oldPart.getTargetConnections().iterator();
		while (i.hasNext()) 
		{
			Wire connection = (Wire) i.next();
			Wire newConnection = new Wire();
			newConnection.setValue(connection.getValue());
			newConnection.setTarget((Table) newPart);
			newConnection.setSource(connection.getSource());
//   Á¬Ïß¹Õµã
//			Iterator b = connection.getBendpoints().iterator();
//			Vector newBendPoints = new Vector();
//
//			while (b.hasNext()) {
//				WireBendpoint bendPoint = (WireBendpoint) b.next();
//				WireBendpoint newBendPoint = new WireBendpoint();
//				newBendPoint.setRelativeDimensions(bendPoint
//						.getFirstRelativeDimension(), bendPoint
//						.getSecondRelativeDimension());
//				newBendPoint.setWeight(bendPoint.getWeight());
//				newBendPoints.add(newBendPoint);
//			}
//
//			newConnection.setBendpoints(newBendPoints);
			newConnections.add(newConnection);
		}

		if (index < 0) 
		{
			newParent.addChild(newPart);
		} else 
		{
			newParent.addChild(newPart, newParent.getChildren().size());
			
		}

		newPart.setSize(oldPart.getSize());

		if (newBounds != null) 
		{
			newPart.setLocation(newBounds.getTopLeft());
		} else 
		{
			if (newParent instanceof Table) 
			{
//				newPart.setLocation(oldPart.getLocation());
				newPart.setLocation(new Point(newParent.getLocation().x+100,newParent.getLocation().y+100));
			} else 
			{
				newPart.setLocation(new Point(200,200));
//				originalPoint
			}
		}

		// keep track of the new parts so we can delete them in undo
		// keep track of the oldpart -> newpart map so that we can properly
		// attach
		// all connections.
		if (newParent == parent)
			newTopLevelParts.add(newPart);
		connectionPartMap.put(oldPart, newPart);
	}

	public void execute() 
	{
		connectionPartMap = new HashMap();
		newConnections = new LinkedList();
		newTopLevelParts = new LinkedList();

		Iterator i = parts.iterator();

		Element part = null;
		while (i.hasNext()) 
		{
			part = (Element) i.next();
			if (bounds != null && bounds.containsKey(part)) 
			{
				copyPart(part, parent, (Rectangle) bounds.get(part),
						newConnections, connectionPartMap, -1);
			} else if (indices != null && indices.containsKey(part)) 
			{
				copyPart(part, parent, null, newConnections,
						connectionPartMap, ((Integer) indices.get(part))
								.intValue());
			} else 
			{
				copyPart(part, parent, null, newConnections,
						connectionPartMap, -1);
			}
		}

		//		 go through and set the source of each connection to the proper
		//		 source.
		Iterator c = newConnections.iterator();

		while (c.hasNext()) 
		{
			Wire conn = (Wire) c.next();
			Element source = conn.getSource();
			if (connectionPartMap.containsKey(source)) 
			{
				conn.setSource((Table) connectionPartMap.get(source));
				conn.attachSource();
				conn.attachTarget();
			}
		}

		//		if (hGuide != null) {
		//			hGuideCommand = new ChangeGuideCommand(
		//					(LogicSubpart) connectionPartMap.get(parts.get(0)), true);
		//			hGuideCommand.setNewGuide(hGuide, hAlignment);
		//			hGuideCommand.execute();
		//		}
		//
		//		if (vGuide != null) {
		//			vGuideCommand = new ChangeGuideCommand(
		//					(LogicSubpart) connectionPartMap.get(parts.get(0)), false);
		//			vGuideCommand.setNewGuide(vGuide, vAlignment);
		//			vGuideCommand.execute();
		//		}
	}

	public void setParent(Container parent) 
	{
		this.parent = parent;
	}

	public void redo() 
	{
		for (Iterator iter = newTopLevelParts.iterator(); iter.hasNext();)
		{
			Object o =  iter.next();
			if(o instanceof ComponentTable && parent.hasMainTable())
			{
				parent.removeChild(parent.getMainTable());
			}

			parent.addChild((Element)o);
		}

		for (Iterator iter = newConnections.iterator(); iter.hasNext();) 
		{
			Wire conn = (Wire) iter.next();
			Element source = conn.getSource();
			if (connectionPartMap.containsKey(source)) 
			{
				conn.setSource((Table) connectionPartMap.get(source));
				conn.attachSource();
				conn.attachTarget();
			}
		}
	}

	public void undo() 
	{
		for (Iterator iter = newTopLevelParts.iterator(); iter.hasNext();)
		{
			Object o = iter.next();
			parent.removeChild((Element)o);
			if (o instanceof ComponentTable) 
			{
				parent.addChild(originalMainTable);
			}
		}
	}

	/**
	 * @param pasteLocation
	 */
	public void setPastePoint(Point pasteLocation) 
	{
		originalPoint = pasteLocation;
	}


}
