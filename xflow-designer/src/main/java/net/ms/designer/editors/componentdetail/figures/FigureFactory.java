package net.ms.designer.editors.componentdetail.figures;

import net.ms.designer.editors.componentdetail.models.Element;
import net.ms.designer.editors.componentdetail.models.Wire;

import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;


public class FigureFactory 
{

	public static CommonFigure createFigure(Element kcgField) 
	{
		CommonFigure cecFigure = null;
				cecFigure = new CommonFigure(kcgField.getName(), kcgField
				.getIcon());

//			if(	kcgField.getField_Type().equals
//					("Autonum"))
//			{
//				return null;
//			}
		return cecFigure;
	}

	public static PolylineConnection createNewWire(Wire wire) 
	{

		PolylineConnection conn = new PolylineConnection();
		PolygonDecoration arrow;

		if (wire == null)
			arrow = null;
		else 
		{
			arrow = new PolygonDecoration();
			arrow.setTemplate(PolygonDecoration.INVERTED_TRIANGLE_TIP);
			arrow.setScale(5, 2.5);
		}
		conn.setSourceDecoration(arrow);

		if (wire == null)
			arrow = null;
		else 
		{
			arrow = new PolygonDecoration();
			arrow.setTemplate(PolygonDecoration.INVERTED_TRIANGLE_TIP);
			arrow.setScale(5, 2.5);
		}
		conn.setTargetDecoration(arrow);
		return conn;
	}

	/**
	 * @param wire
	 * @return
	 */
	public static PolylineConnection createNewBendableWire(Wire wire) 
	{
		PolylineConnection conn = new PolylineConnection();
		//		conn.setSourceDecoration(new PolygonDecoration());
		//		conn.setTargetDecoration(new PolylineDecoration());
		return conn;
	}

	
}
