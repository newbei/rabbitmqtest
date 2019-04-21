package com.newbei.rabbitmq.tools;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtils {

    public static Connection getConnection() throws TimeoutException,IOException {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("192.168.0.232");

        factory.setPort(5672);

        factory.setVirtualHost("vhost_kobe");

        factory.setUsername("guest");

        factory.setPassword("guest");

        return factory.newConnection();
    }

}
