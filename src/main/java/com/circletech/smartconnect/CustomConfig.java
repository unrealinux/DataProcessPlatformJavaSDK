package com.circletech.smartconnect;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2017/1/3.
 */
public class CustomConfig {

    private String amqpHost;
    private String amqpName;
    private String amqpPassword;
    private String amqpExchangeName;
    private String amqpRoutingKey;

    public String getAmqpRoutingKey() {
        return amqpRoutingKey;
    }

    public void setAmqpRoutingKey(String amqpRoutingKey) {
        this.amqpRoutingKey = amqpRoutingKey;
    }

    public String getAmqpExchangeName() {
        return amqpExchangeName;
    }

    public void setAmqpExchangeName(String amqpExchangeName) {
        this.amqpExchangeName = amqpExchangeName;
    }

    public String getAmqpHost() {
        return amqpHost;
    }

    public void setAmqpHost(String amqpHost) {
        this.amqpHost = amqpHost;
    }

    public String getAmqpName() {
        return amqpName;
    }

    public void setAmqpName(String amqpName) {
        this.amqpName = amqpName;
    }

    public String getAmqpPassword() {
        return amqpPassword;
    }

    public void setAmqpPassword(String amqpPassword) {
        this.amqpPassword = amqpPassword;
    }

    public CustomConfig(InputStream configin){
        Properties properties = new Properties();
        try {
            properties.load(configin);
            configin.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        amqpHost = properties.getProperty("amqp.host");
        amqpName = properties.getProperty("amqp.name");
        amqpPassword = properties.getProperty("amqp.password");
        amqpExchangeName = properties.getProperty("amqp.exchangename");
        amqpRoutingKey = properties.getProperty("amqp.routingkey");
    }
}
