package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import receiver.MyReceiver;
import sender.MySender;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		MySender sender1 = new MySender();
		MySender sender2 = new MySender();

		MyReceiver receiver1 = new MyReceiver();



	}
}

	// QUESTION QUEUE
	//
	//Is a queue able to receive message from many senders? YES
	//
	//Is a queue able to send message to many receivers? NO
	//
	// QUESTION TOPIC
	//
	//Is a queue able to receive message from many senders? YES
	//
	//Is a queue able to send message to many receivers? YES