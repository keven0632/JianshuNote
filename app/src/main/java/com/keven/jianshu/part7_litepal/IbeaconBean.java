package com.keven.jianshu.part7_litepal;

import org.litepal.crud.LitePalSupport;

/**
 * Created by zhengjian on 2019/5/30.
 */
public class IbeaconBean extends LitePalSupport {
    /**
     * UUID : FDA50693-A4E2-4FB1-AFCF-C6EB07647825
     * accuracy : 0.01742948930021488
     * major : 16160
     * minor : 48905
     * rssi : -35
     * timeStamp : 1557918194312
     */

    private String UUID;
    private String accuracy;
    private int major;
    private int minor;
    private String rssi;
    private String timeStamp;
    private String src;
    private String deviceId;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public String getRssi() {
        return rssi;
    }

    public void setRssi(String rssi) {
        this.rssi = rssi;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "IbeaconBean{" +
                "UUID='" + UUID + '\'' +
                ", accuracy='" + accuracy + '\'' +
                ", major=" + major +
                ", minor=" + minor +
                ", rssi='" + rssi + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", src='" + src + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
