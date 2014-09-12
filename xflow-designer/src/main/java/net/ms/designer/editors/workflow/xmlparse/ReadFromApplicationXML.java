package net.ms.designer.editors.workflow.xmlparse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.ms.designer.editors.workflow.models.ApplicationActivity;
import net.ms.designer.editors.workflow.models.ParameterEntire;

import org.eclipse.core.resources.ResourcesPlugin;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ReadFromApplicationXML {
	// private List applicationList; //存放读取结果

	public ReadFromApplicationXML() {
	}

	public List readFromApplicationXML(String filePath) throws Exception {
		// String filePath =
		// ResourcesPlugin.getWorkspace().getRoot().getFullPath().toOSString();

		File file = new File(filePath);
		if (!file.exists()) {
			return null;
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(filePath);
		document.normalize();

		List appList = new ArrayList();

		if (document.getDocumentElement() == null) {
			return null;
		}

		if (document.getDocumentElement() != null) {
			Element wfapplications = document.getDocumentElement();

			if (wfapplications.getElementsByTagName("application") != null
					|| wfapplications.getElementsByTagName("application")
							.getLength() > 0) {
				NodeList applicationNodeList = wfapplications
						.getElementsByTagName("application");

				if (applicationNodeList == null
						|| applicationNodeList.getLength() < 1) {
					return null;
				} else {
					for (int i = 0; i < applicationNodeList.getLength(); i++) {
						Element applicationNode = (Element) applicationNodeList
								.item(i);

						ApplicationActivity applicationActivity = new ApplicationActivity();

						if (applicationNode.getElementsByTagName("id") != null
								&& applicationNode.getElementsByTagName("id")
										.getLength() > 0) {
							applicationActivity
									.setApplicationId(applicationNode
											.getElementsByTagName("id").item(0)
											.getFirstChild().getNodeValue());
						}
						if (applicationNode.getElementsByTagName("name") != null
								&& applicationNode.getElementsByTagName("name")
										.getLength() > 0) {
							applicationActivity
									.setApplicationName(applicationNode
											.getElementsByTagName("name")
											.item(0).getFirstChild()
											.getNodeValue());
						}
						if (applicationNode.getElementsByTagName("desc") != null
								&& applicationNode.getElementsByTagName("desc")
										.getLength() > 0) {
							applicationActivity
									.setApplicationDesc(applicationNode
											.getElementsByTagName("desc")
											.item(0).getFirstChild()
											.getNodeValue());
						}
						if (applicationNode.getElementsByTagName("type") != null
								&& applicationNode.getElementsByTagName("type")
										.getLength() > 0) {
							applicationActivity
									.setApplicationType(applicationNode
											.getElementsByTagName("type")
											.item(0).getFirstChild()
											.getNodeValue());
						}
						if (applicationNode.getElementsByTagName("path") != null
								&& applicationNode.getElementsByTagName("path")
										.getLength() > 0) {
							applicationActivity
									.setApplicationPath(applicationNode
											.getElementsByTagName("path")
											.item(0).getFirstChild()
											.getNodeValue());
						}
						if (applicationNode.getElementsByTagName("params") != null
								&& applicationNode.getElementsByTagName(
										"params").getLength() > 0) {
							List applicationParaList = new ArrayList();
							NodeList applicationParaNodeList = ((Element) applicationNode
									.getElementsByTagName("params").item(0))
									.getElementsByTagName("param");
							if (applicationParaNodeList != null
									&& applicationParaNodeList.getLength() > 0) {
								for (int j = 0; j < applicationParaNodeList
										.getLength(); j++) {
									Element applicationPara = (Element) applicationParaNodeList
											.item(j);
									ParameterEntire para = new ParameterEntire();
									if (applicationPara
											.getElementsByTagName("name") != null
											&& applicationPara
													.getElementsByTagName(
															"name").getLength() > 0) {
										para.setParaName(applicationPara
												.getElementsByTagName("name")
												.item(0).getFirstChild()
												.getNodeValue());
									}
									if (applicationPara
											.getElementsByTagName("type") != null
											&& applicationPara
													.getElementsByTagName(
															"type").getLength() > 0) {
										para.setParaType(applicationPara
												.getElementsByTagName("type")
												.item(0).getFirstChild()
												.getNodeValue());
									}
									if (applicationPara
											.getElementsByTagName("isinput") != null
											&& applicationPara
													.getElementsByTagName(
															"isinput")
													.getLength() > 0) {
										int isInputTemp = new Integer(
												applicationPara
														.getElementsByTagName(
																"isinput")
														.item(0)
														.getFirstChild()
														.getNodeValue())
												.intValue();
										if (isInputTemp == 1) {
											para.setIsInput(true);
										}
										if (isInputTemp == 0) {
											para.setIsInput(false);
										}
									}
									if (applicationPara
											.getElementsByTagName("isoutput") != null
											&& applicationPara
													.getElementsByTagName(
															"isoutput")
													.getLength() > 0) {
										int isOutputTemp = new Integer(
												applicationPara
														.getElementsByTagName(
																"isoutput")
														.item(0)
														.getFirstChild()
														.getNodeValue())
												.intValue();
										if (isOutputTemp == 1) {
											para.setIsOutput(true);
										}
										if (isOutputTemp == 0) {
											para.setIsOutput(false);
										}
									}
									applicationParaList.add(para);
								}
							}
							applicationActivity
									.setWfApplicationParam(applicationParaList);
						}
						appList.add(applicationActivity);
					}
				}
			}
		}
		// this.applicationList = appList;
		return appList;
	}
}
