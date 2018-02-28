package com.example.dailiwen.allkindstest.entity;

/**
 *
 * @author dailiwen
 * @date 2018/2/5
 */
public class SfkInfo {
    /**
     * 信息类型
     */
    private final int INFO_TYPE = 2;
    /**
     * 门店ID
     */
    private String storeId;
    /**
     * 终端ID
     */
    private String terminalId;
    /**
     * 服务器套接字
     */
    private String serviceSocket;
    /**
     * 火力大小
     */
    private int fireSize;
    /**
     * 风力大小
     */
    private int windSize;
    /**
     * 冷凝程度
     */
    private int condenseSize;
    /**
     * 开机时长
     */
    private String bootTime;

    public int getINFO_TYPE() {
        return INFO_TYPE;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getServiceSocket() {
        return serviceSocket;
    }

    public void setServiceSocket(String serviceSocket) {
        this.serviceSocket = serviceSocket;
    }

    public int getFireSize() {
        return fireSize;
    }

    public void setFireSize(int fireSize) {
        this.fireSize = fireSize;
    }

    public int getWindSize() {
        return windSize;
    }

    public void setWindSize(int windSize) {
        this.windSize = windSize;
    }

    public int getCondenseSize() {
        return condenseSize;
    }

    public void setCondenseSize(int condenseSize) {
        this.condenseSize = condenseSize;
    }

    public String getBootTime() {
        return bootTime;
    }

    public void setBootTime(String bootTime) {
        this.bootTime = bootTime;
    }
}
