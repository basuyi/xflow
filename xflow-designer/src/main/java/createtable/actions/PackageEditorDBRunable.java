package createtable.actions;


import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.sql.Connection;

import com.ceec.generator.DBGenerator;


public class PackageEditorDBRunable  implements Runnable{
	
	
	/**
	 * The constructor.
	 */

//	project.xml�ж�ȡ��Ŀ����Ŀ¼�Լ���Ŀ����
	private String workplace=new String();  //����Ŀ¼
	private String projectName=new String();	 //��Ŀ����	
	private String componentName=new String();   //�齨����
	private String componentPath=new String();   //�齨·��
	private String packageName=new String();
	private Connection con;
	private Monitor rwp;
	private CommFunction commfun;
	

	public PackageEditorDBRunable(CommFunction commfun,Monitor rwp,Connection con,String projectname,String componentpath,String packagename){
	    this.rwp=rwp;
	    this.con=con;
	    this.projectName=projectname;
	    this.componentPath=componentpath;
	    this.packageName=packagename;
	    this.commfun=commfun;
	}
	
	public void run() {
		 
		

			try {
//				���������齨
				this.createComponent();
				
				this.createEnum();//��������ö��
	 

			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 commfun.openMessage();
				rwp.monitorCancel();
				
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 commfun.openMessage();
				rwp.monitorCancel();
				
				return;
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 commfun.openMessage();
				rwp.monitorCancel();
			
				return;
			}
			rwp.monitorCancel();

		// TODO Auto-generated method stub
		
	}
	

	/**
	 * create component read project.xml
	 * @param project
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
		
		public void createComponent() throws SAXException, IOException, ParserConfigurationException{

			
		      //		��ʼ��ȡxml
						DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
						
						DocumentBuilder builder;
						
							builder = factory.newDocumentBuilder();
					
						Document document = builder.parse(this.componentPath+"\\project.xml");
			  //      	root=richweb ȡ�ø�Ԫ��		
						Element root = document.getDocumentElement();
			
			//	root��һ��project		
						if(root.getElementsByTagName("project")!=null){
					Element prot = (Element) root.getElementsByTagName("project").item(0);
										
			//	get package
					if(prot.getElementsByTagName("package")!=null){
						NodeList packages = prot.getElementsByTagName("package");
				
						for(int i=0;i<packages.getLength();i++)
						
						{
							Element packagelement = (Element)packages.item(i);
						if(packagelement.getElementsByTagName("name")!=null){
							Element packageElement=(Element)packagelement.getElementsByTagName("name").item(0);
							Text packagetext = (Text)packageElement.getFirstChild();
							
						         if(packagetext.getNodeValue().equalsIgnoreCase(this.packageName)||this.packageName.equals(null)||this.packageName.equals("")){
						 
						        	 this.packageName=packagetext.getNodeValue();
						        	 //get component
						        	 if(packagelement.getElementsByTagName("component")!=null){
						        	 NodeList component =packagelement.getElementsByTagName("component");
						        	
						        	 for(int j=0;j<component.getLength();j++){
						        		 Element componentelement = (Element)component.item(j);
						        		 if(componentelement.getElementsByTagName("name")!=null){
											Element comElement=(Element)componentelement.getElementsByTagName("name").item(0);
						        		 Text componentext=(Text)comElement.getFirstChild();
						        		 
						        		 //	�齨����
//						        		 this.componentName=this.projectName+"."+this.packageName+"."+componentext.getNodeValue()+".xml";
						        		 
								 		// added at 2006/11/21
								 		// aimed at getting the "path" which is the id of this component
								 		//***********Begin********
						        		 this.componentName = componentelement.getElementsByTagName("path").item(0).getFirstChild().getNodeValue() + ".xml";
								 		//************End*********											
						        		 if (commfun.ifExists(this.con,componentext.getNodeValue())){
												try{
											commfun.dropTable(this.con,componentext.getNodeValue());
											}catch(Exception e){
												e.printStackTrace();
												 commfun.openMessage();
												rwp.monitorCancel();
												
												return;
												
											}	
											}
////								 		��������������	
									     	DBGenerator ganerator=new DBGenerator();
									     	ganerator.setComponentXMLName(this.componentName);
									     	ganerator.setComponentXMLPath(this.componentPath);
									     	ganerator.setConnection(this.con);
									     	
										try {
												ganerator.generate();
										
											} 
										catch (Exception e) {
												// TODO �Զ����� catch ��
												//System.out.println(e.getMessage());
												e.printStackTrace();
												 commfun.openMessage();											
												rwp.monitorCancel();
											
												return;
											}
											}
						        	 }
						        	 
		 
						         }

						}
						        	 }
						         }
						}
						}
					}
		
		
	/**
	 * create enum read project.xml
	 * @param project
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public void createEnum() throws SAXException, IOException, ParserConfigurationException{

			
		      //		��ʼ��ȡxml
						DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
						
						DocumentBuilder builder;
						
							builder = factory.newDocumentBuilder();
					
						Document document = builder.parse(workplace+"\\"+this.projectName+"\\.configure\\project.xml");
			  //      	root=richweb ȡ�ø�Ԫ��		
						Element root = document.getDocumentElement();
			//	root��һ��project		
						if(root.getElementsByTagName("project")!=null){
					Element prot = (Element) root.getElementsByTagName("project").item(0);
										
			//	get package
					if( prot.getElementsByTagName("package")!=null){
						NodeList packages = prot.getElementsByTagName("package");
						
						for(int i=0;i<packages.getLength();i++)
						
						{
							Element packagelement = (Element)packages.item(i);
							if(packagelement.getElementsByTagName("name")!=null){
							Element packageElement=(Element)packagelement.getElementsByTagName("name").item(0);
							Text packagetext=(Text)packageElement.getFirstChild();
						         if(packagetext.getNodeValue().equals(this.packageName)||this.packageName.equals(null)||this.packageName.equals("")){
		                        //��һ������������component editor
						        //�ڶ���������null,""������package editor
						        	 //get Enum
						        	 if(packagelement.getElementsByTagName("enumeration")!=null){
						        	 NodeList enums =packagelement.getElementsByTagName("enumeration");
						        	 for(int j=0;j<enums.getLength();j++){
						        		 Element enumsElement = (Element)enums.item(i);
						        		 if(enumsElement.getElementsByTagName("name")!=null){
											Element comElement=(Element)enumsElement.getElementsByTagName("name").item(0);
						        		 Text enumtext=(Text)comElement.getFirstChild();
						        		 
										 //	�齨����								 		
						        		 this.componentName=this.projectName+"."+this.packageName+"."+enumtext.getNodeValue()+".xml";
						        		 
									 		// added at 2006/11/21
									 		// aimed at getting the "path" which is the id of this component
									 		//***********Begin********
							        		 this.componentName = enumsElement.getElementsByTagName("path").item(0).getFirstChild().getNodeValue() + ".xml";
									 		//************End*********	
													if(commfun.ifExists(this.con,enumtext.getNodeValue())){
														try{
															commfun.dropTable(this.con,enumtext.getNodeValue());
														}catch(Exception e){
															e.printStackTrace();
															 commfun.openMessage();
															rwp.monitorCancel();
															
															return;
														}
													}
////								 		��������������	
										DBGenerator ganerator=new DBGenerator();
										ganerator.setComponentXMLName(this.componentName);
								     	ganerator.setComponentXMLPath(this.componentPath);
								     	ganerator.setConnection(this.con);
										
									    	try {
												ganerator.generate();
										
											} catch (Exception e) {
												// TODO �Զ����� catch ��
												e.printStackTrace();
												 commfun.openMessage();
												rwp.monitorCancel();
											
												return;
											}
													}
						        	 }
						        	 
		 
						         }

						}
						        	 }
						         }
							}
						}
					}
		}

	
	
	