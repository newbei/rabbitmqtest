package com.newbei.rabbitmq.mq;

import com.newbei.rabbitmq.tools.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class SendSms {
    private static final String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = ConnectionUtils.getConnection();

            while (true){
                Thread.sleep(1000);
                sendMsg(connection);
            }
        } catch (Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void sendMsg(Connection connection) throws IOException {
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String msg = "hello rabbitmq : " + System.currentTimeMillis();
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        System.out.println("send msg to rabbitmq:" + msg );
    }
}
