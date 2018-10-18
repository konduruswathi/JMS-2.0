package com.capgemini.sender;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Sender {
	public static void main(String[] args) {
		/*if (args.length == 0) {
			System.out.println("Must give amessage");
			System.out.println("Hello");
			return;
		}

		else {
			System.out.println(args[0]);
		}*/
		ConnectionFactory connectionFactory=null;
		Connection connection = null;
		Queue queue=null;
		try {
			InitialContext initialContext = new InitialContext();
			 queue = (Queue) initialContext.lookup("jms/P2PQueue");
		connectionFactory = (ConnectionFactory) initialContext.lookup("jms/__defaultConnectionFactory");
			/*connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			MessageProducer messageProducer = session.createProducer(queue);
			TextMessage textMessage = session.createTextMessage("swathi");
			messageProducer.send(textMessage);
			System.out.println("Message produced");*/

		} catch (NamingException e) {
			e.printStackTrace();
		}
		try(JMSContext context=connectionFactory.createContext()){
			TextMessage message = context.createTextMessage("swathi");
		context.createProducer().send(queue,message);
			
			
		/*} catch (JMSException e) {
			e.printStackTrace();

		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}*/
		}
	}
}
