package sender;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;

public class MyPublisher {

    public static void main(String[] args) {
        try {

            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
            TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");

            Topic topic = (Topic) applicationContext.getBean("topic");

            // Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html

            TopicConnection connection = factory.createTopicConnection() ;

            // Open a session

            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE) ;

            // start the connection

            connection.start();

            // Create a publisher

            TopicPublisher publisher = session.createPublisher(topic);

            // Create a message

            TextMessage message = session.createTextMessage();

            message.setText("Hello");

            System.out.println("message: " + message.getText());

            // Send the message

            // send a persistent (DeliveryMode = 2) message with high priority which will never expire
            publisher.publish(message,DeliveryMode.PERSISTENT,9,0);


            //send a non-persistent (DeliveryMode = 1) message with low priority that will expire

            publisher.publish(message,DeliveryMode.NON_PERSISTENT,0,3);
            

            // Close the session

            session.close();

            // Close the connection

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}