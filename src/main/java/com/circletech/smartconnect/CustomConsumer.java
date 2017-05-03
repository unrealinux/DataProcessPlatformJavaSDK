package com.circletech.smartconnect;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * Created by xieyingfei on 2016/12/19.
 */
public class CustomConsumer extends DefaultConsumer {



    private PositionProcessor positionProcessor;
    private TransducerDataProcessor transducerDataProcessor;
    private TransportPosition devicepostion;
    private TransportTransducerData transportTransducerData;

    CustomConsumer(Channel channel, PositionProcessor positionProcessor, TransducerDataProcessor transducerDataProcessor){
            super(channel);
            this.positionProcessor = positionProcessor;
            this.transducerDataProcessor = transducerDataProcessor;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope,
                        AMQP.BasicProperties properties, byte[] body) throws IOException{

            String message = new String(body, "UTF-8");
            try {

                String routingKey = envelope.getRoutingKey();
                if(routingKey.equals("android-gps-pos-data")){
                    if(positionProcessor != null){
                        positionProcessor.newData(message);
                    }
                }else{
                    if(transducerDataProcessor != null){
                        transducerDataProcessor.newData(message);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
        }
}
