/**
 * @author liuchunxia
 * all needed constants when workflow is running
 *
 */
package net.ms.designer.editors.workflow;

import java.util.HashMap;

public class Constants 
{
	
	public static String DRAFT = (new Integer(0)).toString();  //editing
	public static String COMPLETE = (new Integer(1)).toString();  //finished
//	public static String SINGLE_ROUTE = (new Integer(0)).toString();  //single route
//	public static String ALL_ROUTE = (new Integer(1)).toString();  // all route
	public static String WF_ACTIVITY_TYPE_START = (new Integer(0)).toString();
	public static String WF_ACTIVITY_TYPE_END = (new Integer(9)).toString();
	public static String WF_ACTIVITY_TYPE_SUBFLOW = (new Integer(3)).toString();
	public static String WF_ACTIVITY_TYPE_USER_APP = (new Integer(2)).toString();
	public static String WF_ACTIVITY_TYPE_SYS_APP = (new Integer(1)).toString();
	public static String WF_DIAGRAM_CONNECTION_TYPE_ROUTER_MANUAL = (new Integer(1)).toString();  //   �ֶ�����
	public static String WF_DIAGRAM_CONNECTION_TYPE_ROUTER_MANHATTAN = (new Integer(2)).toString();  //������
	public static String WF_DIAGRAM_CONNECTION_TYPE_ROUTER_SHORTEST_PATH = (new Integer(3)).toString();  //���·��
	
	public static String WF_APPLICATION_TYPE_USER = (new Integer(1)).toString();
	public static String WF_APPLICATION_TYPE_SYS = (new Integer(0)).toString();

	public static String WF_PARA_TYPE_STRING = (new Integer(1)).toString();
	public static String WF_PARA_TYPE_DOUBLE = (new Integer(3)).toString();
	public static String WF_PARA_TYPE_LONG = (new Integer(2)).toString();
//	public static String WF_PARA_TYPE_BOOLEAN = (new Integer(6)).toString();     ////--------mine
	public static String WF_PARA_TYPE_DATE = (new Integer(4)).toString();
	public static String WF_PARA_TYPE_OBJECT = (new Integer(5)).toString();
	public static String WF_PARA_TYPE_NONE = (new Integer(0)).toString();
//	public static String WF_PARA_TYPE_BEAN = "ҵ������";
//	public static String WF_PARA_TYPE_ENTITYID = "ҵ��ʵ��";
//	public static String WF_PARA_TYPE_INFOR = "��ʾ��Ϣ";
	
	public static String WF_JOIN_TYPE_SINGLE = (new Integer(0)).toString();  //��ͨ
	public static String WF_JOIN_TYPE_WHOLE = (new Integer(1)).toString();   //ȫͨ
	
	public static String WF_SUBFLOW_EXECUTE_MODE_SYNCHRO = (new Integer(0)).toString(); //ͬ��
	public static String WF_SUBFLOW_EXECUTE_MODE_ASYNCHRO = (new Integer(1)).toString();  //�첽
	
	public static String WF_FINISH_TYPE_SINGLE_PEASON = (new Integer(0)).toString();  //�������
	public static String WF_FINISH_TYPE_MULTI_PEASON = (new Integer(1)).toString();  //�������
		
	public static HashMap DATE_TIME_DISPLAY = new HashMap();	//the date's style	
	static {
		DATE_TIME_DISPLAY.put("1","yyyy-MM-dd hh:mm");
		DATE_TIME_DISPLAY.put("2","yyyy-MM-dd hh");
		DATE_TIME_DISPLAY.put("3","yyyy-MM-dd");
		DATE_TIME_DISPLAY.put("4","yyyy-MM");
		DATE_TIME_DISPLAY.put("5","yyyy");
	}
}
