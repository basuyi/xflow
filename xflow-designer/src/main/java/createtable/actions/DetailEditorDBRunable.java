package createtable.actions;

import net.ms.designer.core.DBTool;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.ceec.generator.DBGenerator;

public class DetailEditorDBRunable implements Runnable{
	
	/**
	 * The constructor.
	 */	
//	project.xml中读取项目工作目录以及项目名称
	private String componentName=new String();   //组建名称
	private String componentPath=new String();   //组建路径
	private String componentname=new String();
	private Connection con;
	private Monitor rwp;
	private CommFunction commfun;
	
	 
	public DetailEditorDBRunable(String componentname,CommFunction commfun,Monitor rwp,Connection con,String ComponentName,String componentpath){
	    this.rwp=rwp;
	    this.con=con;
	    this.componentname=componentname;
	    this.componentName=ComponentName;
	    this.componentPath=componentpath;
	    this.commfun=commfun;
	}
	
	

	public void setCon(Connection con){this.con=con;}
	public void setComponentPath(String componentpath){this.componentPath=componentpath;}
	
	public void run() {
		
		//chuanzhi
		
		
			if(commfun.ifExists(this.con,this.componentname))
			{
				try{
				commfun.dropTable(this.con,this.componentname);
				
				}catch(Exception e){
					e.printStackTrace();
					rwp.monitorCancel();
					commfun.openMessage();
					 return;
				}
			}
			
		////	调用生成器方法	

		
		DBGenerator ganerator=new DBGenerator();
		ganerator.setComponentXMLName(this.componentName);
		ganerator.setComponentXMLPath(this.componentPath);
		ganerator.setConnection(this.con);
	
				try {
					ganerator.generate();
			    
			       

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					commfun.openMessage();
					//rwp.monitorCancel();
					
				}
				   rwp.monitorCancel();
			}

		
		// TODO Auto-generated method stub
		
	}


	
		


	

