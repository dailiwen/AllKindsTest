package com.example.dailiwen.allkindstest.activity.Netty;

/**
 * @author dailiwen
 * @date 2018/02/27
 */

public class RequestInfo {
    private String body;
    private byte type;
    private int sequence;

    public void setBody(String body) {
        this.body = body;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getBody() {
        return body;
    }

    public byte getType() {
        return type;
    }

    public int getSequence() {
        return sequence;
    }
}
