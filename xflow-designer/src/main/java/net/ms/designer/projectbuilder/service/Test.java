package net.ms.designer.projectbuilder.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("workflow_context.xml");
		testWorkflow(context);
	}

	private static void testWorkflow(ApplicationContext context) {
//		ServiceTester engine = (ServiceTester)context.getBean("tester");
//		try {
//			engine.foo();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
