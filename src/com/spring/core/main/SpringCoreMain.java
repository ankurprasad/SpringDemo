package com.spring.core.main;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.core.service.Shape;

public class SpringCoreMain {

	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("springCoreConfig.xml");
		context.registerShutdownHook();
		Shape circle = (Shape)context.getBean("circle");
		circle.display();
		System.out.println(context.getMessage("greeting", null, "Property Not Found", null));
		context.close();
	}

}
