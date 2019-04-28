package com.jack.appweiliao.bean;

/**
 * 展示聊天消息的模型
 */
public class MessageChatBean {
    private String msg;// 消息内容
    private int type;// 消息类型，1 对方消息，2 我的消息
    private String date;// 日期
    private int state; //消息发送状态   0发送中  1发送成功   -1 发送失败

    public MessageChatBean() {
    }

    public MessageChatBean(String msg, int type, String date, int state) {
        this.msg = msg;
        this.type = type;
        this.date = date;
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
