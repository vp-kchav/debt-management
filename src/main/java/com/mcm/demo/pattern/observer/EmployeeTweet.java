//package com.mcm.demo.pattern.observer;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.mcm.demo.pattern.mediator.IMediator;
//
//public class EmployeeTweet implements ITweet{
//
//	private static final Logger logger = LoggerFactory.getLogger(EmailNotificationObserver.class);
//
//	private IMediator mediator;
//
//	private Tweet tweet;
//
//	private List<Observer> observers = new ArrayList<Observer>();
//
//	public IMediator getMediator() {
//		return mediator;
//	}
//
//	public void setMediator(IMediator mediator) {
//		this.mediator = mediator;
//	}
//
//	public EmployeeTweet(IMediator mediator) {
//		this.mediator = mediator;
//	}
//
//	public void attach(Observer observer){
//      observers.add(observer);
//	}
//
//	public Tweet getTweet() {
//		return tweet;
//	}
//
//	public void setTweet(Tweet tweet) {
//		this.tweet = tweet;
//	}
//
//	@Override
//	public boolean tweet() {
//		logger.info("Observer design pattern do Tweet.");
//		boolean result = mediator.tweet();
//		notifyAllObservers();
//		return result;
//	}
//
//    public void notifyAllObservers(){
//    	logger.info("Observer design pattern Notify all Observer.");
//    	for (Observer observer : observers) {
//    		observer.notifyEmployee();;
//    	}
//    }
//
//}
