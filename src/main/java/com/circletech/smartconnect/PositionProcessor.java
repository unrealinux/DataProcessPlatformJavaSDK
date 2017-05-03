package com.circletech.smartconnect;

/**
 * Created by xieyingfei on 2016/12/19.
 */
public interface PositionProcessor {
    void newPosition(TransportCommData data);

    void newData(String data);
}
