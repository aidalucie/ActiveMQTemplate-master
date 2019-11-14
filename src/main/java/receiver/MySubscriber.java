package receiver;

import javax.jms.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MySubscriber {

    public static void main(String[] args) {


    
    	try{

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
        TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");

        Topic topic = (Topic) applicationContext.getBean("topic");

        // Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html

        TopicConnection connection = factory.createTopicConnection() ;

        // Open a session

        TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE) ;

        // start the connection

        connection.start();

        // Create a subscription

            TopicSubscriber subscriber = session.createDurableSubscriber(topic, "M1") ;


            // Receive the message

        TextMessage m = (TextMessage)subscriber.receive();

        System.out.println("\n\nLE MESSAGE EST :" + m.getText() + "\n\n");


        TextMessage m2 = (TextMessage)subscriber.receive();

        System.out.println("\n\nLE MESSAGE EST :" + m2.getText() + "\n\n");




    }catch(Exception e){
        e.printStackTrace();
    }
    }
}
