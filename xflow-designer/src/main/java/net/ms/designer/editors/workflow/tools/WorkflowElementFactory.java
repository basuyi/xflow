package net.ms.designer.editors.workflow.tools;

import org.eclipse.gef.requests.CreationFactory;

public class WorkflowElementFactory implements CreationFactory{

	public Object getNewObject() {
		// TODO Auto-generated method stub
		 try {
	            return ((Class) template).newInstance();
	         }
	         catch (Exception e) {
	            return null;
	         }
	}

	public Object getObjectType() {
		// TODO Auto-generated method stub
		return template;
	}

	private Object template;

	public WorkflowElementFactory(Object template) {
	    this.template = template;
	}
}
