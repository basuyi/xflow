package createtable.actions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.ms.designer.core.DBTool;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class CommFunction {
	private boolean message;
	
	
	public CommFunction(){
		this.message=false;
	}
	public void openMessage(){
		this.message=true;
	}
	public void closeMessage(){
		this.message=false;
	}
	public boolean getMessageStat(){
		return this.message;
	}
	
	public DBTool getDBTool(String workplace,String projectName){

				try{
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(workplace+"\\"+projectName+"\\.configure\\project.xml");
				document.normalize();
				if(document.getDocumentElement()==null){
					return null;
				}					
				if(document.getDocumentElement()!=null){
					Element richweb = document.getDocumentElement();
					
					if(richweb.getElementsByTagName("project")!=null||richweb.getElementsByTagName("project").getLength()>0){
						Element projectXML = (Element) richweb.getElementsByTagName("project").item(0);
						NodeList DataSourceList = projectXML.getElementsByTagName("DataSource");
						if(DataSourceList == null||DataSourceList.getLength()<1){
							return null;
						}
						else{
							DBTool tool = new DBTool();
							Element dataSource = (Element) DataSourceList.item(0);
							if(dataSource.getElementsByTagName("DataSourceName") != null 
									|| dataSource.getElementsByTagName("DataSourceName").getLength()>0)
								tool.setConName(dataSource.getElementsByTagName("DataSourceName").item(0).getFirstChild().getNodeValue());
							if(dataSource.getElementsByTagName("username") != null 
									|| dataSource.getElementsByTagName("username").getLength()>0)
								tool.setUsername(dataSource.getElementsByTagName("username").item(0).getFirstChild().getNodeValue());
							if(dataSource.getElementsByTagName("password") != null 
									|| dataSource.getElementsByTagName("password").getLength()>0)
								tool.setPassword(dataSource.getElementsByTagName("password").item(0).getFirstChild().getNodeValue());
							if(dataSource.getElementsByTagName("type") != null 
									|| dataSource.getElementsByTagName("type").getLength()>0)
								tool.setDbType(dataSource.getElementsByTagName("type").item(0).getFirstChild().getNodeValue());
							if(dataSource.getElementsByTagName("database") != null 
									|| dataSource.getElementsByTagName("database").getLength()>0)
								tool.setDbName(dataSource.getElementsByTagName("database").item(0).getFirstChild().getNodeValue());
							if(dataSource.getElementsByTagName("port") != null 
									|| dataSource.getElementsByTagName("port").getLength()>0)
								tool.setPort(dataSource.getElementsByTagName("port").item(0).getFirstChild().getNodeValue());
							if(dataSource.getElementsByTagName("server") != null 
									|| dataSource.getElementsByTagName("server").getLength()>0)
								tool.setServer(dataSource.getElementsByTagName("server").item(0).getFirstChild().getNodeValue());
//							if(dataSource.getElementsByTagName("maxConnections") != null 
//									|| dataSource.getElementsByTagName("maxConnections").getLength()>0)
							return tool;
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
//		}
		return null;
		

	}

	public boolean ifExists(Connection con,String componentname) {
		
//		数据库连接查找表
		 String sql="select * from "+componentname;

  
			Statement stmt;
			ResultSet rs;
			try {
				stmt = con.createStatement();
				rs=stmt.executeQuery(sql);

			    return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
	      
//	    	 e.printStackTrace();
				return false;
	      
		
			}
	}
	
	
	public void dropTable(Connection con,String componentname){
		
		Statement stmt;
		String  sql="DROP TABLE "+componentname;
		
			try {
				stmt=con.createStatement();
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
	


