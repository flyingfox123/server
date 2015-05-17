package com.manyi.rating.entity.score;

import java.util.List;

/**
 * Created by Administrator on 2015/3/17.
 */
public class Score extends ScoreBase {

    private List<ScoreItem> scorelist;

    private List<ScoreReply> replyList;

    public List<ScoreItem> getScorelist() {
        return scorelist;
    }

    public void setScorelist(List<ScoreItem> scorelist) {
        this.scorelist = scorelist;
    }

    public List<ScoreReply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ScoreReply> replyList) {
        this.replyList = replyList;
    }

}
