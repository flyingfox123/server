package com.manyi.rating.support.dao;


import com.manyi.rating.entity.score.Score;
import com.manyi.rating.entity.score.ScoreItem;
import com.manyi.rating.entity.score.ScoreReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2015/3/18.
 */
public interface ScoreDao {

    int addScore(Score score);

    int addScoreItem(ScoreItem item);

    int addScoreReply(ScoreReply reply);

    /**
     * 取得指定评价的评分条目明细
     *
     * @param basisID
     * @return
     */
    public List<ScoreItem> getScoreItem(String basisID);

    /**
     * 取得指定评价的回复明细
     *
     * @param basisID
     * @return
     */
    public List<ScoreReply> getScoreReply(String basisID);

    /**
     * 取得被评价方所有评价主表总条数
     *
     * @return
     */
    public int getAllScoreCount(@Param("channelID") String channelID, @Param("commodityID") String commodityID,
                                @Param("gradedID") String gradedID);

    /**
     * 查询被评价方所有评价信息
     *
     * @param channelID
     * @param gradedID
     * @return
     */
    public List getAllScore(@Param("channelID") String channelID, @Param("commodityID") String commodityID,
                            @Param("gradedID") String gradedID, @Param("startIndex") int startIndex,
                            @Param("pageSize") int pageSize);

    /**
     * 查询评价方所有评价信息
     *
     * @param channelID
     * @param commodityID
     * @param graderID
     * @return
     */
    public List getAllScoreOfGrader(@Param("channelID") String channelID, @Param("commodityID") String commodityID,
                                    @Param("graderID") String graderID, @Param("startIndex") int startIndex,
                                    @Param("pageSize") int pageSize);

    /**
     * 查询评价方在评价主表的条数
     *
     * @param channelID
     * @param commodityID
     * @param graderID
     * @return
     */
    public int getAllScoreCountOfGrader(@Param("channelID") String channelID, @Param("commodityID") String commodityID,
                                        @Param("graderID") String graderID);

    /**
     * 取得被评价者在指定时间内，评价主表的总条数
     *
     * @return
     */
    public int getScoreCountByTime(@Param("channelID") String channelID, @Param("commodityID") String commodityID,
                                   @Param("gradedID") String gradedID,
                                   @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查询被评价方在指定时间内的所有评价信息
     *
     * @param channelID
     * @param gradedID
     * @param startTime
     * @param endTime
     * @return
     */
    public List getScoreByTime(@Param("channelID") String channelID, @Param("commodityID") String commodityID,
                               @Param("gradedID") String gradedID,
                               @Param("startTime") String startTime, @Param("endTime") String endTime,
                               @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    /**
     * 评价者修改评分
     *
     * @param scoreItem
     * @return
     */
    int modifyScore(ScoreItem scoreItem);

    /**
     * 评价者修改评价
     *
     * @param score
     * @return
     */
    int modifyEvaluate(Score score);

    /**
     * 删除评分
     *
     * @param id
     * @return
     */
    int deleteScore(long id);

    /**
     * 删除评价
     *
     * @param id
     * @return
     */
    int deleteEvaluate(long id);


}
