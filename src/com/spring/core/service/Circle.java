package com.spring.core.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.spring.core.event.DrawEvent;
import com.spring.core.model.Point;

@Component
public class Circle implements Shape, ApplicationEventPublisherAware
{
	private Point center;
	private Point xycoordinate;
	@Autowired
	private MessageSource messageSource;
	private ApplicationEventPublisher publisher;
	
	public Point getCenter() {
		return center;
	}
	@Resource(name="pointB")
	public void setCenter(Point center) {
		this.center = center;
	}

	public Point getXycoordinate() {
		return xycoordinate;
	}
	@Autowired
	@Qualifier(value="pointA")
	public void setXycoordinate(Point xycoordinate) {
		this.xycoordinate = xycoordinate;
	}
	public MessageSource getMessageSource() {
		return messageSource;
	}
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	@Override
	public void display() {
		System.out.println(this.messageSource.getMessage("greeting", null, "Property Not Found", null));
		System.out.println(center.getX()+","+center.getY());
		System.out.println(xycoordinate.getX()+","+xycoordinate.getY());
		System.out.println(this.messageSource.getMessage("draw.circle", new Object[] {center.getX(),center.getY()}, "Property Not Found", null));
		DrawEvent event = new DrawEvent(this);
		publisher.publishEvent(event);
	}
	
	@PostConstruct
	public void myInit()
	{
		System.out.println("Initilizing my custom bean");
	}
	
	@PreDestroy
	public void myDestroy()
	{
		System.out.println("Perform clean up before Destroy this Bean");
	}
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
		
	}
	

}
