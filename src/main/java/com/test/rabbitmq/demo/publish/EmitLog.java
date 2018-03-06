package com.test.rabbitmq.demo.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class EmitLog {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME,"fanout");//交换机
            for (int i=0;i<5;i++){
                //Thread.sleep(1000);
                String message = "Hello World! " + i;
                channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
            }
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}