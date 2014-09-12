package net.ms.designer.ui.view;

import java.util.List;

public class SubflowEntity implements ITreeEntry{
	
	private String name;
	private List children;
	private String type = "subflow";
	private String projectName = "";
	private String packageName = "";
	private String componentName = "";
	private String workflowName = "";
	private String subflowName = "";
	
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	public List getChildren() {
		// TODO Auto-generated method stub
//		return this.children;
		return null;
	}

	public void setChildren(List children) {
		// TODO Auto-generated method stub
//		this.children = children;
	}

	public String getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	public void setProjectName(String projectName) {
		// TODO Auto-generated method stub
		this.projectName = projectName;
	}

	public String getProjectName() {
		// TODO Auto-generated method stub
		return this.projectName;
	}
	
	public void setPackageName(String packageName){
		this.packageName = packageName;
	}
	
	public String getPackageName(){
		return this.packageName;
	}
	
	public void setComponentName(String componentName){
		this.componentName = componentName;
	}
	
	public String getComponentName(){
		return this.componentName;
	}

	public void setWorkflowName(String workflowName) {
		// TODO Auto-generated method stub
		this.workflowName = workflowName;
	}

	public String getWorkflowName() {
		// TODO Auto-generated method stub
		return this.workflowName;
	}
	
	public void setSubflowName(String subflowName)
	{
		this.subflowName = subflowName;
	}
	
	public String getSubflowName()
	{
		return this.subflowName;
	}
}
