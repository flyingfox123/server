package com.manyi.rating.entity.score;

import java.sql.Date;

/**
 * Created by Administrator on 2015/3/17.
 */
public class ScoreReply {

    private long id;
    private long scoreid;
    private String replyFlag;
    private String replyContent;
    private Date replyTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getScoreid() {
        return scoreid;
    }

    public void setScoreid(long scoreid) {
        this.scoreid = scoreid;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyFlag() {
        return replyFlag;
    }

    public void setReplyFlag(String replyFlag) {
        this.replyFlag = replyFlag;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }
}
