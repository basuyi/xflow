package net.ms.designer.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MsElement implements Serializable
{
	String id;
	String nodeName;
	String nodeType;
	String key;
	Object container;
	MsElement parent;
	List children = new ArrayList();
	
	// id
	public String getId()
	{
		return this.id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	// nodeName
	public String getNodeName()
	{
		return this.nodeName;
	}
	public void setNodeName(String nodeName)
	{
		this.nodeName = nodeName;
	}
	//nodeType
	public String getNodeType()
	{
		return this.nodeType;
	}
	public void setNodeType(String nodeType)
	{
		this.nodeType = nodeType;
	}
	//key
	public String getKey()
	{
		this.key = this.nodeType + "_" + this.nodeName;
		return this.key;
	}
	public void setKey(String key)
	{
		this.key = key;
	}
	// container
	public Object getContainer()
	{
		return this.container;
	}
	public void setContainer(Object container)
	{
		this.container = container;
	}
	// parent
	public MsElement getParent()
	{
		return this.parent;
	}
	public void setParent(MsElement parent)
	{
		this.parent = parent;
	}
	// get children
	public List getChildren()
	{
		return this.children;
	}
	// add a child
	public void addChild(MsElement element)
	{
		children.add(element);
	}
	// remove a child
	public void removeChild(MsElement element)
	{
		children.remove(element);
	}
}