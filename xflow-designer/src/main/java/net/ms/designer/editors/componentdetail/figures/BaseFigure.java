/*
 * 创建日期 2006-9-4
 *
 * TODO 
 */
package net.ms.designer.editors.componentdetail.figures;

import org.eclipse.draw2d.Figure;
/**
 * @author gaosi
 * @version 1.1.0
 * @explain 
 */

public class BaseFigure extends Figure
{
	 private String name;
	    /**
	     * @return Returns the name.
	     */
	    public String getName() 
	    {
	        return name;
	    }
	    /**
	     * @param name
	     *            The name to set.
	     */
	    public void setName(String name) 
	    {
	        this.name = name;
	    }

}
