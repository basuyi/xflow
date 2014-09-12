package net.ms.designer.editors.componentdetail.commands;


import java.util.Iterator;

import net.ms.designer.editors.componentdetail.models.Element;
import net.ms.designer.editors.componentdetail.models.LogicGuide;
import net.ms.designer.editors.componentdetail.models.Messages;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;



public class MoveGuideCommand extends Command 
{

	private int pDelta;

	private LogicGuide guide;

	public MoveGuideCommand(LogicGuide guide, int positionDelta) 
	{
		super(Messages.getString("MoveGuideCommand.Label"));
		this.guide = guide;
		pDelta = positionDelta;
	}

	public void execute() 
	{
		guide.setPosition(guide.getPosition() + pDelta);
		Iterator iter = guide.getParts().iterator();
		while (iter.hasNext()) 
		{
			Element part = (Element) iter.next();
			Point location = part.getLocation().getCopy();
			if (guide.isHorizontal()) 
			{
				location.y += pDelta;
			} else 
			{
				location.x += pDelta;
			}
			part.setLocation(location);
		}
	}

	public void undo() 
	{
		guide.setPosition(guide.getPosition() - pDelta);
		Iterator iter = guide.getParts().iterator();
		while (iter.hasNext()) 
		{
			Element part = (Element) iter.next();
			Point location = part.getLocation().getCopy();
			if (guide.isHorizontal()) 
			{
				location.y -= pDelta;
			} else {
				location.x -= pDelta;
			}
			part.setLocation(location);
		}
	}

}