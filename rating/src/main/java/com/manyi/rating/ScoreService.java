package com.manyi.rating;


import com.manyi.rating.entity.common.RootMsg;
import com.manyi.rating.entity.score.Score;
import com.manyi.rating.entity.score.ScoreItem;
import com.manyi.rating.entity.score.ScoreReply;

/**
 * Created by Administrator on 2015/3/18.
 */
public interface ScoreService {

    /**
     *  增加评论
     * @param score
     * @return
     */
    RootMsg addScore(Score score);


    /**
     *  追加评语
     * @param reply
     * @return
     */
    RootMsg addScoreReply(ScoreReply reply);

    /**
     * 查询被评价方所有主表评价
     * @param channelID
     * @param commodityID
     * @param gradedID
     * @return
     */
    RootMsg getAllScore(String channelID, String commodityID, String gradedID, int pageNum, int pageSize);


    /**
     * 取得被评价者所有评价主表的页数
     * @param channelID
     * @param commodityID
     * @param gradedID
     * @return
     */
    RootMsg getAllScorePageCount(String channelID, String commodityID, String gradedID, int pageSize);

    /**
     * 查询被评价方指定时间内所有评价主表的信息
     * @param channelID
     * @param commodityID
     * @param gradedID
     * @param startTime
     * @param endTime
     * @return
     */
    RootMsg getScoreByTime(String channelID, String commodityID,
                           String gradedID, String startTime, String endTime, int pageNum, int pageSize);

    /**
     * 取得被评价方在指定时间内评价主表的总页数
     * @param channelID
     * @param commodityID
     * @param gradedID
     * @param startTime
     * @param endTime
     * @param pageSize
     * @return
     */
    RootMsg getScorePageCountByTime(String channelID, String commodityID,
                                    String gradedID, String startTime, String endTime, int pageSize);

    /**
     * 查询评价方所有评价信息
     * @param channelID
     * @param commodityID
     * @param graderID
     * @return
     */
    RootMsg getAllScoreOfGrader(String channelID, String commodityID, String graderID, int pageNum, int pageSize);

    /**
     * 查询评价者所有评价信息
     * @param channelID
     * @param commodityID
     * @param graderID
     * @param pageSize
     * @return
     */
    RootMsg getAllScoreCountOfGrader(String channelID, String commodityID, String graderID, int pageSize);

    /**
     * 取得指定评价的评分条目明细信息
     * @param basisID
     * @return
     */
    RootMsg getScoreItem(String basisID);

    /**
     * 取得指定评价的回复明细信息
     * @param basisID
     * @return
     */
    RootMsg getScoreReply(String basisID);

    /**
     * 评价者修改评分
     * @param scoreItem
     * @return
     */
    RootMsg modifyScore(ScoreItem scoreItem);

    /**
     * 评价者修改评价
     * @param score
     * @return
     */
    RootMsg modifyEvaluate(Score score);

    /**
     * 删除评分
     * @param id
     * @return
     */
    RootMsg deleteScore(long id);

    /**
     * 删除评价
     * @param id
     * @return
     */
    RootMsg deleteEvaluate(long id);

}
