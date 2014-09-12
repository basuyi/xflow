package net.ms.designer.editors.enumcomponentdetail.command;

import net.ms.designer.editors.enumcomponentdetail.model.Table;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;


public class TableMoveCommand extends Command 
{

    private ChangeBoundsRequest request;   
    private Table model;

    public void execute() 
    {
      Point old = getModel().getLocation();
      int x = request.getMoveDelta().x;
      int y = request.getMoveDelta().y;
      
      getModel().setLocation(new Point(old.x+x,old.y+y));
    }

    public ChangeBoundsRequest getRequest()
    {
        return request;
    }

    public void setRequest(ChangeBoundsRequest request) 
    {
        this.request = request;
    }

    public Table getModel() 
    {
        return model;
    }

    public void setModel(Table model) 
    {
        this.model = model;
    }
}
