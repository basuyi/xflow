/**
 * @author liuchunxia
 * 
 */
package net.ms.designer.editors.workflow.models;

import java.io.Serializable;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

public class WireBendPoint implements Serializable,Bendpoint
{
	/**
	 * add a default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	private float weight = 0.5f;
	private Dimension d1,d2;
	
	public WireBendPoint()
	{
		
	}
	
	public float getWeight()
	{
		return this.weight;
	}
	
	public void setWeight(float weight)
	{
		this.weight = weight;
	}
	
	/**
	 * get first relative dimension
	 * @return
	 */
	public Dimension getFirstRelativeDimension()
	{
		return d1;
	}
	
	/**
	 * get second relative dimension
	 * @return
	 */
	public Dimension getSecondRelativeDimension()
	{
		return d2;
	}
	
	/**
	 * set relative dimensions
	 * @param d1
	 * @param d2
	 */
	public void setRelativeDimensions(Dimension d1,Dimension d2)
	{
		this.d1 = d1;
		this.d2 = d2;
	}

	//------------------------
	//--the auto-genetated method
	public Point getLocation()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
