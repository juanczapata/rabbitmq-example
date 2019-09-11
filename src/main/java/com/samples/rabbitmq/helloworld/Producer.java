package com.samples.rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
	
	private final static String QUEUE_NAME = "sample-queue";

	public static void main(String[] args) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection();
		    Channel channel = connection.createChannel()) {
			
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			String message = "Sample message in local queue";
			//Sending empty exchange means that we will use the default exchange, in this case the messages will be routed
			//to the queue with name "sample-queue" (routingKey)
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
