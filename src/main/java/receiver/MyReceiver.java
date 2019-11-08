package receiver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;

public class MyReceiver {

	public static void main(String[] args) {
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			
			Queue queue = (Queue) applicationContext.getBean("queue");
			
			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html

			QueueConnection connection = factory.createQueueConnection() ;

			// Open a session

			QueueSession session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE) ;

			// start the connection

			connection.start();

			// Create a receive

			QueueReceiver receiver = session.createReceiver(queue);

			// Receive the message

			TextMessage m = (TextMessage)receiver.receive();

			System.out.println("\n\nLE MESSAGE EST :" + m.getText() + "\n\n");


			TextMessage m2 = (TextMessage)receiver.receive();

			System.out.println("\n\nLE MESSAGE EST :" + m2.getText() + "\n\n");




		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
