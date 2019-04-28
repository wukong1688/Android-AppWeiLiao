package com.jack.appweiliao.bean;

import java.io.Serializable;

/**
 * Message 列表 Bean
 */
public class MessageListBean implements Serializable {
    private String nickName;
    private int avatar;
    private String lastMsg;
    private String time;

    public MessageListBean(String nickName, int avatar, String lastMsg, String time) {
        this.nickName = nickName;
        this.avatar = avatar;
        this.lastMsg = lastMsg;
        this.time = time;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}