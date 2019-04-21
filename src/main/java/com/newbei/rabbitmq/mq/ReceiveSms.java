package com.newbei.rabbitmq.mq;

import com.newbei.rabbitmq.tools.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveSms {

    private static final String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = ConnectionUtils.getConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            DefaultConsumer consumer =  new DefaultConsumer(channel){
                //一旦有消息进入队列就会触发
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msg = new String (body,"utf-8");
                    System.out.println("receive msg :" + msg);
                }
            };
            //监听队列
            channel.basicConsume(QUEUE_NAME,true,consumer);

        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

}
