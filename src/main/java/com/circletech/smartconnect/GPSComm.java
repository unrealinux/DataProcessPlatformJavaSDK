package com.circletech.smartconnect;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeoutException;

/**
 * Created by xieyingfei on 2016/12/17.
 */
public class GPSComm {

    private static final String EXCHANGE_NAME = "gps-rabbitmq";
    private static Channel channel;
    private static CustomConfig customConfig;
    private static String queueName;

    //成功返回0，失败返回1
    public static int init(InputStream configin){

        customConfig = new CustomConfig(configin);

        try {
            ConnectionFactory factory = new ConnectionFactory();
            //factory.setHost("192.168.1.120");
            factory.setHost(customConfig.getAmqpHost());
            factory.setPort(5672);
            //factory.setUsername("admin");
            //factory.setPassword("ceilcell");
            factory.setUsername(customConfig.getAmqpName());
            factory.setPassword(customConfig.getAmqpPassword());

            Connection connection = factory.newConnection();
            channel = connection.createChannel();

            channel.exchangeDeclare(customConfig.getAmqpExchangeName(), "topic", true);

            queueName = channel.queueDeclare().getQueue();

        }catch (IOException e){
            e.printStackTrace();
            return 1;
        }catch (TimeoutException e){
            e.printStackTrace();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }

        return 0;
    }

    //成功返回0，失败返回1
    public static int open(String message, PositionProcessor positionProcessor, TransducerDataProcessor transducerDataProcessor){
        try
        {
            //生产者
            String routingKey = customConfig.getAmqpRoutingKey();
            //String message = "android-open";
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

            if(message.equals("android-open-pos")){
                channel.queueBind(queueName, EXCHANGE_NAME, "android-gps-pos-data");
            }else if(message.equals("android-open-sensor")){
                channel.queueBind(queueName, EXCHANGE_NAME, "android-gps-sensor-data");
            }else{
                //for other
            }

            //消费者
            Consumer consumer = new CustomConsumer(channel, positionProcessor, transducerDataProcessor);
            channel.basicConsume(queueName, true, consumer);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        }
        catch (IOException e){
            e.printStackTrace();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }

        return 0;
    }

    //成功返回0，失败返回1
    public static int close(String message){

        try {
            //生产者
            String routingKey = customConfig.getAmqpRoutingKey();
            //String message = "open";
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());

            if(message.equals("android-close-pos")){
                channel.queueUnbind(queueName, EXCHANGE_NAME, "android-gps-pos-data");
            }else if(message.equals("android-close-sensor")){
                channel.queueUnbind(queueName, EXCHANGE_NAME, "android-gps-sensor-data");
            }else{

            }

        }catch (IOException e){
            e.printStackTrace();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }

        return 0;
    }
}
