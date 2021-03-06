package net.ms.designer.editors.packages.models;

import java.util.ArrayList;
import java.util.List;

public class PackageDiagram extends Element
{
	final public static String prop_Node = "Node";
	
	private List nodes = new ArrayList();
	
//	add a node into diagram object
	public void addNode(Package node)
	{
		nodes.add(node);
		this.fireStructureChange(prop_Node,nodes);
	}
//	remove a node from diagram object
	public void removeNode(Package node)
	{
		nodes.remove(node);
		this.fireStructureChange(prop_Node,nodes);
	}
//	get the nodes list of this diagram
	public List getNodes()
	{
		return nodes;
	}
}