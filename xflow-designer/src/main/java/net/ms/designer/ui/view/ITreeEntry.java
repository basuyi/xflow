package net.ms.designer.ui.view;

import java.util.List;

import org.eclipse.swt.events.MouseEvent;

public interface ITreeEntry {

	 public String getName();
	 public void setName(String name);

	 public List getChildren();
	 public void setChildren(List children);
	 
	 public String getType();
	 
	 public void setProjectName(String projectName);
	 public String getProjectName();
	 
	 public void setPackageName(String packageName);
	 public String getPackageName();
	 
	 public void setComponentName(String componentName);
	 public String getComponentName();
	 
	 public void setWorkflowName(String workflowName);
	 public String getWorkflowName();
	 
	 public void setSubflowName(String subflowName);
	 public String getSubflowName();

}
