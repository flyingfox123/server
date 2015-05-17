package com.manyi.rating.entity.score;


import com.manyi.rating.entity.common.RootMsg;

import java.util.List;

/**
 * Created by zhangyf on 2015/3/19 0019.
 */
public class ScoreMsg extends RootMsg {
    private  int pageCount;
    private List<Score> scoreList;
    private List<ScoreItem> scoreItemList;
    private List<ScoreReply> scoreReplyList;

    public List<ScoreReply> getScoreReplyList() {
        return scoreReplyList;
    }

    public void setScoreReplyList(List<ScoreReply> scoreReplyList) {
        this.scoreReplyList = scoreReplyList;
    }

    public List<ScoreItem> getScoreItemList() {
        return scoreItemList;
    }

    public void setScoreItemList(List<ScoreItem> scoreItemList) {
        this.scoreItemList = scoreItemList;
    }



    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List getScore() {
        return scoreList;
    }

    public void setScore(List<Score> scoreList) {
        this.scoreList = scoreList;
    }
}
