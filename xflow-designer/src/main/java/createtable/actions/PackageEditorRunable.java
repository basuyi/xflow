package createtable.actions;

import org.eclipse.ui.IEditorPart;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import com.ceec.generator.Generator;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;



public class PackageEditorRunable implements Runnable {
	
//	project.xml�ж�ȡ��Ŀ����Ŀ¼�Լ���Ŀ����
	private String workplace=new String();  //����Ŀ¼
	private String projectName=new String();	 //��Ŀ����	
	private String projectPath=new String();   //��Ŀ·��
	private String componentName=new String();   //�齨����
	private String componentPath=new String();   //�齨·��
	private String packageName=new String();
    private Monitor rwp;
    private IEditorPart editor;
    private CommFunction commfun;
    
    public PackageEditorRunable(CommFunction commfun,IEditorPart editor,Monitor rwp){
    	this.editor=editor;
    	this.rwp=rwp;
    	this.commfun=commfun;
    }

	public void run() {
	   
	   IWorkspace myWorkspace = ResourcesPlugin.getWorkspace();
		workplace = myWorkspace.getRoot().getLocation().toOSString();
		// TODO Auto-generated method stub
		Object obj;
		try {
			obj = PropertyUtils.getProperty(editor.getEditorInput(),"project");
			this.projectName = (String)PropertyUtils.getProperty(obj,"projectName");
    
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			 commfun.openMessage();
			rwp.monitorCancel();
			
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			 commfun.openMessage();
			rwp.monitorCancel();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			 commfun.openMessage();
			rwp.monitorCancel();
		}
	


	    //�齨��ַ
		this.componentPath=workplace+"\\"+this.projectName+"\\.configure\\";
		
	
	try {
	
		createComponent();
		
		createEnum();//��������ö��

       rwp.monitorCancel();

	} catch (SAXException e) {
		// TODO �Զ����� catch ��
		e.printStackTrace();
		 commfun.openMessage();
		rwp.monitorCancel();
		} catch (IOException e) {
		// TODO �Զ����� catch ��
		e.printStackTrace();
		 commfun.openMessage();
		rwp.monitorCancel();
	} catch (ParserConfigurationException e) {
		// TODO �Զ����� catch ��
		e.printStackTrace();
		 commfun.openMessage();
		rwp.monitorCancel();
		
	}
	  	
		
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
					
						Document document = builder.parse(this.workplace+"\\"+this.projectName+"\\.configure\\project.xml");
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
								 			this.componentName=this.projectName+"."+this.packageName+"."+componentext.getNodeValue()+".xml";
								 			// added by mashuai at 2006/11/17
								 			// aimed at getting the "path" which is the id of this component
								 			//***********Begin********
								 			String componentFile = componentelement.getElementsByTagName("path").item(0).getFirstChild().getNodeValue() + ".xml";
								 			//************End*********
								 	     //��������ַ
										this.projectPath=workplace+"\\"+this.projectName+"\\";
											
											
////								 		��������������	
									     	Generator ganerator=new Generator(this.componentPath,componentFile,this.projectPath);
									
										try {
											ganerator.generate();
								        
											}catch (Exception e) {
												// TODO �Զ����� catch ��
												//System.out.println(e.getMessage());
												e.printStackTrace();
												 commfun.openMessage();
												rwp.monitorCancel();
												
											}
//											try{
//
//												//updaTED by lili
//												UpdateComponent upcom=new UpdateComponent(this.projectName,componentext.getNodeValue(),this.packageName);
//												upcom.updateProject(true,null);
//												//------------
//										        }catch(Exception e)
//										      {
//											  e.printStackTrace();
//											  commfun.openMessage();
//											  rwp.monitorCancel();
//												
//										      }
						        	 
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
					if(prot.getElementsByTagName("package")!=null){
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
								 	    // added by mashuai at 2006/11/17
							 			// aimed at getting the "path" which is the id of this component
							 			//***********Begin********
							 			String componentFile = enumsElement.getElementsByTagName("path").item(0).getFirstChild().getNodeValue() + ".xml";
							 			//************End*********
								 	     //��������ַ
										this.projectPath=workplace+"\\"+this.projectName+"\\";
										
								 			
////								 		��������������	
										Generator ganerator=new Generator(this.componentPath,componentFile,this.projectPath);
									    	try {
												ganerator.generate();
									
											} catch (Exception e) {
												// TODO �Զ����� catch ��
												e.printStackTrace();
												 commfun.openMessage();
												rwp.monitorCancel();
												
											}
//											try{
//
//												//updaTED by lili
//												UpdateComponent upcom=new UpdateComponent(this.projectName,this.componentName,this.packageName);
//												upcom.updateProject(true,null);
//												//------------
//										        }catch(Exception e)
//										      {
//											  e.printStackTrace();
//											  commfun.openMessage();
//											  rwp.monitorCancel();
//											
//										      }
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
