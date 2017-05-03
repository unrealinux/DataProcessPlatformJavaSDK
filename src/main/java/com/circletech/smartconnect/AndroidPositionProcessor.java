package com.circletech.smartconnect;

/**
 * Created by Administrator on 2016/12/19.
 */
public class AndroidPositionProcessor implements PositionProcessor {

    public void newPosition(TransportCommData transportPosition) {
        System.out.println(transportPosition.toString());
    }

    public void newData(String data){
        System.out.println(data);
    }
}
