package net.ms.designer.editors.workflow.figures;

import net.ms.designer.editors.workflow.models.Wire;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;




public class FigureFactory {
	public static WorkflowActivityFigure createWFFigure(String name, Image img) {
		return new WorkflowActivityFigure(name, img);
	}

	public static PolylineConnection createNewBendableWire(final Wire wire) {
		PolylineConnection conn = new PolylineConnection();
		conn.setTargetDecoration(new PolygonDecoration());
		String content = wire.getDescription();
		if (content != null) {
			Label label = new Label();
			label.setForegroundColor(new Color(null, 1, 123, 233));
			label.setText(content);
			conn.add(label, new MidpointLocator(conn, 0));
			if (content.trim().equals("")) { 
				label.setOpaque(false);
			} else
				label.setOpaque(true);
		}
		return conn;
	}
}