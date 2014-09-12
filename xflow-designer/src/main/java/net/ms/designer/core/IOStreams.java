package net.ms.designer.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;



public class IOStreams 
{
	
	public void outputs(Object obj,String file) throws IOException
	{
		
		FileOutputStream ostream = new FileOutputStream(file);// �����ļ������
		ObjectOutputStream outputs = new ObjectOutputStream(ostream);// ��
		outputs.writeObject(obj); // ����̳������л��ӿڵ���
		outputs.flush();
		outputs.close();
		ostream.close();

		
	}
	
	public Object inputs(String file) throws IOException, ClassNotFoundException
	{
		Object obj=new Object();
		FileInputStream istream = new FileInputStream(file); // �����ļ�������
		ObjectInputStream inputs = new ObjectInputStream(istream); // ��
        obj = (Object)inputs.readObject(); // �������л�����
        istream.close();
		return obj;
	}
	
	
	
	public void transformToFile(Document doc,String outFile) throws TransformerException{
		File file = new File(outFile);
		StreamResult sr = new StreamResult(file);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		Properties properties = t.getOutputProperties();
        properties.setProperty(OutputKeys.ENCODING, "UTF-8");
        properties.setProperty(OutputKeys.METHOD, "xml");
        properties.setProperty(OutputKeys.VERSION, "1.0");
        properties.setProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperties(properties);
        // now proceed with the transformation
        DOMSource doms = new DOMSource(doc);
        t.transform(doms, sr);		
	}
}
