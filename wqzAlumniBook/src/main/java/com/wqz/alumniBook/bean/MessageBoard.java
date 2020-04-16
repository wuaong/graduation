package com.wqz.alumniBook.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MessageBoard {
    private Integer nid;
    private String content;
    private String receiver;

    @JsonFormat(timezone = "GMT+8", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String sender;

    public MessageBoard() {
    }

    public MessageBoard(Integer nid, String content, String receiver, Date createTime, String sender) {
        this.nid = nid;
        this.content = content;
        this.receiver = receiver;
        this.createTime = createTime;
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "MessageBoard{" +
                "nid=" + nid +
                ", content='" + content + '\'' +
                ", receiver='" + receiver + '\'' +
                ", createTime=" + createTime +
                ", sender='" + sender + '\'' +
                '}';
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
