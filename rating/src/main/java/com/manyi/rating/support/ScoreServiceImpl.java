package com.manyi.rating.support;

import com.manyi.rating.ScoreService;
import com.manyi.rating.support.dao.ScoreDao;
import com.manyi.rating.entity.common.MsgCode;
import com.manyi.rating.entity.common.RootMsg;
import com.manyi.rating.entity.score.Score;
import com.manyi.rating.entity.score.ScoreItem;
import com.manyi.rating.entity.score.ScoreMsg;
import com.manyi.rating.entity.score.ScoreReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;


/**
 * Created by Administrator on 2015/3/18.
 */

@Service("scoreService")
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreDao scoreDao;

    @Override
    public RootMsg addScore(Score score) {

        RootMsg msg = new RootMsg();
        msg.setCodeMsg(MsgCode.OK);
        if(null != score)
        {
            Date date =new Date (System.currentTimeMillis());
            score.setEvaluateTime(date);
          if(checkScoreParam(score))
          {
              List<ScoreItem> itemlist = score.getScorelist();
              if(null !=  itemlist)
              {
                  float totalscore = calcScore(itemlist);
                  score.setTotalScore(totalscore);
              }

              scoreDao.addScore(score);
              long scoreid = score.getId();
              if(null != itemlist)
              {
                  for(ScoreItem item : score.getScorelist())
                  {
                      item.setScoreid(scoreid);
                      scoreDao.addScoreItem(item);
                  }

              }

          }
          else {
              msg.setCodeMsg(MsgCode.BAD_REQUEST);
              msg.setErrorMsg(MsgCode.BAD_REQUEST_MSG);
          }
        }
        else
        {
            msg.setCodeMsg(MsgCode.EMPTY_OBJ);
            msg.setErrorMsg(MsgCode.EMPTY_OBJ_MSG);

        }

        return msg;
    }



    @Override
    public RootMsg addScoreReply(ScoreReply reply) {
        {

            RootMsg msg = new RootMsg();
            msg.setCodeMsg(MsgCode.OK);
            if(null != reply)
            {
                Date date =new Date (System.currentTimeMillis());
                reply.setReplyTime(date);
                if(checkScoreReplyParam(reply))
                {
                    scoreDao.addScoreReply(reply);
                }
                else {
                    msg.setCodeMsg(MsgCode.BAD_REQUEST);
                    msg.setErrorMsg(MsgCode.BAD_REQUEST_MSG);
                }
            }
            else
            {
                msg.setCodeMsg(MsgCode.EMPTY_OBJ);
                msg.setErrorMsg(MsgCode.EMPTY_OBJ_MSG);

            }

            return msg;
        }
    }
    @Override
    public RootMsg getAllScore(String channelID, String commodityID, String gradedID,int pageNum,int pageSize) {
        ScoreMsg msg = new ScoreMsg();
        msg.setCodeMsg(MsgCode.OK);
        int startIndex = (pageNum - 1)*pageSize;
        if (channelID!=null && commodityID != null && gradedID!=null){
            List<Score> list = scoreDao.getAllScore(channelID, commodityID, gradedID,startIndex,pageSize);
            msg.setScore(list);
        }else {
            msg.setCodeMsg(MsgCode.BAD_REQUEST);
            msg.setErrorMsg(MsgCode.BAD_REQUEST_MSG);
        }
        return msg;
    }

    @Override
    public RootMsg getAllScorePageCount(String channelID, String commodityID, String gradedID,int pageSize) {
        ScoreMsg msg = new ScoreMsg();
        msg.setCodeMsg(MsgCode.OK);
        if (channelID!=null && commodityID != null && gradedID!=null && pageSize >0){
            int count = scoreDao.getAllScoreCount(channelID,commodityID,gradedID);
            int pageCount = (count + pageSize -1)/pageSize;
            msg.setPageCount(pageCount);
        }else {
            msg.setCodeMsg(MsgCode.BAD_REQUEST);
            msg.setErrorMsg(MsgCode.BAD_REQUEST_MSG);
        }
        return msg;
    }

    @Override
    public RootMsg getScoreByTime(String channelID, String commodityID, String gradedID, String startTime,
                                  String endTime,int pageNum,int pageSize) {
        ScoreMsg msg = new ScoreMsg();
        msg.setCodeMsg(MsgCode.OK);
        int startIndex = (pageNum - 1)*pageSize;
        if (channelID!=null && commodityID != null && gradedID!=null
                && startTime !=null && endTime != null){
            List<Score> list = scoreDao.getScoreByTime(channelID, commodityID, gradedID, startTime, endTime,startIndex,pageSize);
            msg.setScore(list);
        }else {
            msg.setCodeMsg(MsgCode.BAD_REQUEST);
            msg.setErrorMsg(MsgCode.BAD_REQUEST_MSG);
        }
        return msg;
    }

    @Override
    public RootMsg getScorePageCountByTime(String channelID, String commodityID, String gradedID, String startTime, String endTime, int pageSize) {
        ScoreMsg msg = new ScoreMsg();
        msg.setCodeMsg(MsgCode.OK);
        if (channelID!=null && commodityID != null && gradedID!=null
                && startTime !=null && endTime != null && pageSize >0){
            int count = scoreDao.getScoreCountByTime(channelID,commodityID,gradedID,startTime,endTime);
            int pageCount = (count + pageSize -1)/pageSize;
            msg.setPageCount(pageCount);
        }else {
            msg.setCodeMsg(MsgCode.BAD_REQUEST);
            msg.setErrorMsg(MsgCode.BAD_REQUEST_MSG);
        }
        return msg;
    }

    @Override
    public RootMsg getAllScoreOfGrader(String channelID, String commodityID, String graderID,int pageNum,int pageSize) {
        ScoreMsg msg = new ScoreMsg();
        msg.setCodeMsg(MsgCode.OK);
        int startIndex = (pageNum - 1)*pageSize;
        if (channelID!=null && commodityID != null && graderID!=null){
            List<Score> list = scoreDao.getAllScoreOfGrader(channelID, commodityID, graderID, startIndex, pageSize);
            msg.setScore(list);
        }else {
            msg.setCodeMsg(MsgCode.BAD_REQUEST);
            msg.setErrorMsg(MsgCode.BAD_REQUEST_MSG);
        }
        return msg;
    }

    @Override
    public RootMsg getAllScoreCountOfGrader(String channelID, String commodityID, String graderID, int pageSize) {
        ScoreMsg msg = new ScoreMsg();
        msg.setCodeMsg(MsgCode.OK);
        if (channelID!=null && commodityID != null && graderID!=null && pageSize >0){
            int count = scoreDao.getAllScoreCountOfGrader(channelID, commodityID, graderID);
            int pageCount = (count + pageSize -1)/pageSize;
            msg.setPageCount(pageCount);
        }else {
            msg.setCodeMsg(MsgCode.BAD_REQUEST);
            msg.setErrorMsg(MsgCode.BAD_REQUEST_MSG);
        }
        return msg;
    }

    @Override
    public RootMsg getScoreItem(String basisID) {
        ScoreMsg msg = new ScoreMsg();
        msg.setCodeMsg(MsgCode.OK);
        if (basisID!=null){
            List<ScoreItem> list = scoreDao.getScoreItem(basisID);
            msg.setScoreItemList(list);
        }else {
            msg.setCodeMsg(MsgCode.BAD_REQUEST);
            msg.setErrorMsg(MsgCode.BAD_REQUEST_MSG);
        }
        return msg;
    }

    @Override
    public RootMsg getScoreReply(String basisID) {
        ScoreMsg msg = new ScoreMsg();
        msg.setCodeMsg(MsgCode.OK);
        if (basisID!=null){
            List<ScoreReply> list = scoreDao.getScoreReply(basisID);
            msg.setScoreReplyList(list);
        }else {
            msg.setCodeMsg(MsgCode.BAD_REQUEST);
            msg.setErrorMsg(MsgCode.BAD_REQUEST_MSG);
        }
        return msg;
    }

    @Override
    public RootMsg modifyScore(ScoreItem scoreItem) {
        ScoreMsg msg = new ScoreMsg();
        msg.setCodeMsg(MsgCode.OK);
        if (scoreItem!=null){
            int ret = scoreDao.modifyScore(scoreItem);
            if (ret <= 0) {
                msg.setCodeMsg(MsgCode.BAD_REQUEST);
                msg.setErrorMsg(MsgCode.BAD_REQUEST_MSG);
            }
        }else {
            msg.setCodeMsg(MsgCode.BAD_REQUEST);
            msg.setErrorMsg(MsgCode.BAD_REQUEST_MSG);
        }
        return msg;
    }

    @Override
    public RootMsg modifyEvaluate(Score score) {
        ScoreMsg msg = new ScoreMsg();
        msg.setCodeMsg(MsgCode.OK);
        if (score!=null){
            int ret = scoreDao.modifyEvaluate(score);
            if (ret <= 0) {
                msg.setCodeMsg(MsgCode.BAD_REQUEST);
                msg.setErrorMsg(MsgCode.BAD_REQUEST_MSG);
            }
        }else {
            msg.setCodeMsg(MsgCode.BAD_REQUEST);
            msg.setErrorMsg(MsgCode.BAD_REQUEST_MSG);
        }
        return msg;
    }

    @Override
    public RootMsg deleteScore(long id) {
        ScoreMsg msg = new ScoreMsg();
        msg.setCodeMsg(MsgCode.OK);
        if (id != 0){
            int ret = scoreDao.deleteScore(id);
            System.out.println(ret);
        }else {
            msg.setCodeMsg(MsgCode.BAD_REQUEST);
            msg.setErrorMsg(MsgCode.BAD_REQUEST_MSG);
        }
        return msg;
    }

    @Override
    public RootMsg deleteEvaluate(long id) {
        ScoreMsg msg = new ScoreMsg();
        msg.setCodeMsg(MsgCode.OK);
        if (id != 0){
            int ret = scoreDao.deleteEvaluate(id);
            System.out.println(ret);
        }else {
            msg.setCodeMsg(MsgCode.BAD_REQUEST);
            msg.setErrorMsg(MsgCode.BAD_REQUEST_MSG);
        }
        return msg;
    }

    /**
     *  检查追评参数合法性
     * @param reply
     * @return
     */
    private boolean checkScoreReplyParam(ScoreReply reply) {

        boolean result = true;
        if(0 == reply.getScoreid())
        {
            return false;
        }
        if(null == reply.getReplyContent())
        {
            return false;
        }

        if(null == reply.getReplyFlag())
        {
            return false;
        }
        return result;
    }

    /**
     * 计算分数
     * @param itemlist
     * @return
     */

    private float calcScore(List<ScoreItem> itemlist) {
        float totalscore = 0;
        for(ScoreItem item : itemlist)
        {
            totalscore += item.getItemScore()*item.getWeight();
        }
        return totalscore;
    }




    /**
     * 检查参数是否合法
     * @param score
     * @return
     */
    private boolean checkScoreParam(Score score) {

        boolean result = true;
        if(null == score.getChannelID())
        {
            return false;
        }
        if(null == score.getCommodityID())
        {
            return false;
        }
        if(null == score.getEvaluateContent())
        {
            return false;
        }

        if(null == score.getGradedID())
        {
            return false;
        }
        if(null == score.getGraderID())
        {
            return false;
        }
        if(null == score.getTradeOrder())
        {
            return false;
        }
        if(null == score.getGradeStrategyID())
        {
            return false;
        }
        if(null != score.getScorelist())
        {
            for(ScoreItem item :score.getScorelist() )
            {
                if(0 == item.getWeight()||
                        0 == item.getItemId())
                {
                    return false;
                }
            }
        }
        return result;
    }


}
