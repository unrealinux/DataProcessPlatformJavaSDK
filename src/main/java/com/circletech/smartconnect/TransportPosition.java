package com.circletech.smartconnect;

/**
 * Created by Administrator on 2016/12/14.
 */
public class TransportPosition implements TransportCommData {
    private Long deviceId;
    private Double posX;
    private Double posY;
    private Double posZ;

    private TransportPosition() {
    }
    private static class SingletonInstance {
        private static final TransportPosition INSTANCE = new TransportPosition();
    }

    public static TransportPosition getInstance() {
        return SingletonInstance.INSTANCE;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Double getPosX() {
        return posX;
    }

    public void setPosX(Double posX) {
        this.posX = posX;
    }

    public Double getPosY() {
        return posY;
    }

    public void setPosY(Double posY) {
        this.posY = posY;
    }

    public Double getPosZ() {
        return posZ;
    }

    public void setPosZ(Double posZ) {
        this.posZ = posZ;
    }

    @Override
    public String toString() {
        return "com.ceilcell.gps.TransportPosition{" +
                "deviceId=" + deviceId +
                ", posX=" + posX +
                ", posY=" + posY +
                ", posZ=" + posZ +
                '}';
    }
}
