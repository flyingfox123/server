package com.manyi.rating.support;

import com.manyi.rating.support.dao.ScoreStrategyDao;
import com.manyi.rating.entity.common.MsgCode;
import com.manyi.rating.entity.common.RootMsg;
import com.manyi.rating.entity.score.GradeStrategy;
import com.manyi.rating.entity.score.GradeStrategyItem;
import com.manyi.rating.entity.score.StrategyMsg;
import com.manyi.rating.entity.score.StrategysMsg;
import com.manyi.rating.ScoreStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/3/18.
 */
@Service("scoreStrategyService")
public class ScoreStrategyServiceImpl implements ScoreStrategyService {
    @Autowired
    private ScoreStrategyDao scoreStrategyDao;

    @Override
    public StrategysMsg getScoreStrategys() {
        StrategysMsg msg = new StrategysMsg();
        msg.setCodeMsg(MsgCode.OK);
        try {
            List<GradeStrategy> gradeStrategies =
                    scoreStrategyDao.getGradeStrategyList();
            msg.setList(gradeStrategies);
        } catch (Exception e) {
            msg.setCodeMsg(MsgCode.EXCEPTATION_FAILED);
            msg.setErrorMsg(MsgCode.EXCEPTATION_FAILED_MSG);
        } finally {
            return msg;
        }


    }

    @Override
    public StrategyMsg getScoreStrategy(String gradeStrategyID) {
        StrategyMsg msg = new StrategyMsg();
        msg.setCodeMsg(MsgCode.OK);
        if (null != gradeStrategyID) {
            GradeStrategy strategy = scoreStrategyDao.getGradeStrategy(gradeStrategyID);
            if (null != strategy) {
                List<GradeStrategyItem> strategyItems = scoreStrategyDao.getGradeStrategyItem(gradeStrategyID);

                strategy.setStrategyItem(strategyItems);

                msg.setStrategy(strategy);
            } else {
                msg.setCodeMsg(MsgCode.EMPTY_OBJ);
                msg.setErrorMsg(MsgCode.EMPTY_OBJ_MSG);
            }

        } else {
            msg.setCodeMsg(MsgCode.EMPTY_OBJ);
            msg.setErrorMsg(MsgCode.EMPTY_OBJ_MSG);
        }
        return msg;
    }

    @Override
    public RootMsg addStrategy(GradeStrategy strategy) {

        RootMsg msg = new RootMsg();
        msg.setCodeMsg(MsgCode.OK);
        if (null != strategy) {

            if (checkScoreStrategyParam(strategy)) {
                scoreStrategyDao.addGradeStrategy(strategy);
                for (GradeStrategyItem item : strategy.getStrategyItem()) {
                    item.setGradeStrategyID(strategy.getGradeStrategyID());
                    scoreStrategyDao.addGradeStrategyItem(item);
                }
            } else {
                msg.setCodeMsg(MsgCode.BAD_REQUEST);
                msg.setErrorMsg(MsgCode.BAD_REQUEST_MSG);
            }
        } else {
            msg.setCodeMsg(MsgCode.EMPTY_OBJ);
            msg.setErrorMsg(MsgCode.EMPTY_OBJ_MSG);

        }

        return msg;
    }

    /**
     * 校验评价策略参数合法性
     *
     * @param strategy
     * @return
     */
    private boolean checkScoreStrategyParam(GradeStrategy strategy) {

        boolean result = true;
        if (null == strategy.getGradeStrategyID()) {
            return false;
        }
        if (null == strategy.getGradeStrategyName()) {
            return false;
        }

        if (null == strategy.getScoreRule()) {
            return false;
        }
        if (null == strategy.getStrategyItem()) {
            return false;
        }

        if (null != strategy.getStrategyItem()) {
            for (GradeStrategyItem item : strategy.getStrategyItem()) {
                if (0 == item.getWeight()) {
                    return false;
                }
                if (null == item.getName()) {
                    return false;
                }
            }

        }
        return result;
    }


    @Override
    public RootMsg editStrategy(GradeStrategy strategy) {

        RootMsg msg = new RootMsg();
        msg.setCodeMsg(MsgCode.OK);
        if (null != strategy) {
            // GradeStrategy oldStrategy  = scoreStrategyDao.getGradeStrategy(strategy.getGradeStrategyID());
            scoreStrategyDao.editGradeStrategy(strategy);
            if (null != strategy.getStrategyItem()) {
                for (GradeStrategyItem item : strategy.getStrategyItem()) {
                    scoreStrategyDao.editGradeStrategyItem(item);

                }
            }


        } else {
            msg.setCodeMsg(MsgCode.EMPTY_OBJ);
            msg.setErrorMsg(MsgCode.EMPTY_OBJ_MSG);
        }
        return msg;
    }

    @Override
    public RootMsg deleteStrategy(String gradeStrategyID) {

        RootMsg msg = new RootMsg();
        msg.setCodeMsg(MsgCode.OK);

        try {

            scoreStrategyDao.deleteGradeStrategy(gradeStrategyID);
            scoreStrategyDao.delGradeStrategyItem(gradeStrategyID);

        } catch (Exception e) {
            msg.setCodeMsg(MsgCode.EXCEPTATION_FAILED);
            msg.setErrorMsg(MsgCode.EXCEPTATION_FAILED_MSG);
        } finally {
            return msg;
        }
    }
}
