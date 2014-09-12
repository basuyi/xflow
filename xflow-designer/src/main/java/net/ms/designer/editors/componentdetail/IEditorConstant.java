package net.ms.designer.editors.componentdetail;


import net.ms.designer.editors.componentdetail.models.Messages;


public class IEditorConstant 
{
	 public static final String[] YES_NO = new String[]
	                                                  {            
         Messages.getString("CommonField.Property.Constant.No"),
         Messages.getString("CommonField.Property.Constant.Yes")
 };
 public static final String[] PRIVILEGE_ITEM = new String[] 
                                                          { "None","Menu" 
	 };
 
 public static final String ADD = Messages.getString("AddCommand.Label");
 public static final String NEW = Messages.getString("AddCommand.Label");
 
 public static final String SAVE = net.ms.designer.editors.componentdetail.models.Messages.getString("Editor.Save");
 
 public static String HAVE_WF = (new Integer(1)).toString();
 public static String HAVE_NO_WF = (new Integer(0)).toString();
 
 public static String STRINGFIELD_TYPE = (new Integer(1)).toString();
 public static String INTEGERFIELD_TYPE = (new Integer(2)).toString();
 public static String DATEFIELD_TYPE = (new Integer(3)).toString();
 public static String FLOATFIELD_TYPE = (new Integer(4)).toString();
 public static String ENUMFIELD_TYPE = (new Integer(5)).toString();
 
 
 public static String HAVE_SF = (new Integer(1)).toString();
 public static String HAVE_NO_SF = (new Integer(0)).toString();
 
}
