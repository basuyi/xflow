package org.basuyi.xflow.model;

import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class SubflowActivity extends Activity implements Serializable {

//    /** identifier field */
//    private String subflowActivityId;

    /** nullable persistent field */
    private String subflowId;

    /** nullable persistent field */
    private String workflowId;

    /** full constructor */
//    public SubflowAcrtivity(String subflowActivityId, String subflowId, String workflowId) {
//        this.subflowActivityId = subflowActivityId;
//        this.subflowId = subflowId;
//        this.workflowId = wokflowId;
//    }

    /** default constructor */
    public SubflowActivity() {
    }

    /** minimal constructor */
//    public SubflowActivity(String subflowActivityId) {
//        this.subflowActivityId = subflowActivityId;
//    }

//    public String getSubflowActivityId() {
//        return this.subflowActivityId;
//    }
//
//    public void setSubflowActivityId(String subflowActivityId) {
//        this.subflowActivityId = subflowActivityId;
//    }

    public String getSubflowId() {
        return this.subflowId;
    }

    public void setSubflowId(String subflowId) {
        this.subflowId = subflowId;
    }

    public String getWorkflowId() {
        return this.workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

//    public String toString() {
//        return new ToStringBuilder(this)
//            .append("subflowActivityId", getSubflowActivityId())
//            .toString();
//    }

}
