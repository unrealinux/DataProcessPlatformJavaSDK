package com.circletech.smartconnect;

/**
 * Created by Administrator on 2016/12/19.
 */
public interface TransducerDataProcessor {

    void newTransducerData(TransportCommData data);

    void newData(String data);
}
