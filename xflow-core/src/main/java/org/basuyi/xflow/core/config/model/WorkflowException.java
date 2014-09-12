package org.basuyi.xflow.core.config.model;

/**
 * �������쳣����
 * @author mashuai
 * @version 0.1
 * @date 2009-10-19
 */
public class WorkflowException extends Exception {

	private static final long serialVersionUID = 1L;

	/*�쳣��Ϣ*/
	private String message = null;
	
	/*�쳣����*/
	private Exception e = null;
	
	public WorkflowException() {
		super();
	}
	
	public WorkflowException(String message) {
		this.message = message;
	}
	
	public WorkflowException(String message, Exception e) {
		this.message = message;
		this.e = e;
	}

	/**
	 * @return �쳣��Ϣ
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message �쳣��Ϣ
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return �쳣����
	 */
	public Exception getE() {
		return e;
	}

	/**
	 * @param message �쳣����
	 */
	public void setE(Exception e) {
		this.e = e;
	}
	
	
}
