package com.manyi.common.messagePush.bean;

/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class PushedMessage
{
    private  long  id;

    private  String  content;

    private  String  title;

    private  String sendTime;

    private  String invalidTime;

    private int  type;

    private  long   userId;

    private  Boolean  readMark;

    private  String url;

    private  Boolean  isSended;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Boolean getReadMark() {
        return readMark;
    }

    public void setReadMark(Boolean readMark) {
        this.readMark = readMark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getIsSended() {
        return isSended;
    }

    public void setIsSended(Boolean isSended) {
        this.isSended = isSended;
    }
}
