package com.samples.rabbitmq.helloworld;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumer {
	private final static String QUEUE_NAME = "sample-queue";

	public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection;
		try {
			connection = factory.newConnection();
	        Channel channel = connection.createChannel();

	        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

	        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	            String message = new String(delivery.getBody(), "UTF-8");
	            System.out.println(" [x] Received '" + message + "'");
	        };
	        //Register a consumer to listen to a queue
	        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}

	}

}
