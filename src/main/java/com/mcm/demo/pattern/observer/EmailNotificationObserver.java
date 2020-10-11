//package com.mcm.demo.pattern.observer;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//public class EmailNotificationObserver extends Observer {
//
//	private static final Logger logger = LoggerFactory.getLogger(EmailNotificationObserver.class);
//	private EmployeeTweet subject;
//	public EmailNotificationObserver(EmployeeTweet subject) {
//		this.subject = subject;
//		this.subject.attach(this);
//	}
//
//	@Override
//	public void notifyEmployee() {
//		for(Employee employee: subject.getTweet().getTweetTo()) {
//			logger.info("Notification is sent to " + employee.getFirstName()+ ", " + employee.getLastName()  );
//		}
//	}
//
//}
