package com.manyi.rating.controller;

import com.manyi.rating.ScoreService;
import com.manyi.rating.entity.common.RootMsg;
import com.manyi.rating.entity.score.GetScoreReq;
import com.manyi.rating.entity.score.Score;
import com.manyi.rating.entity.score.ScoreBase;
import com.manyi.rating.entity.score.ScoreMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Magic on 2015/1/6.
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({
//        "classpath:config/spring-base.xml",
//        "classpath:config/mybatis-config.xml",
//        "classpath:config/spring-servlet.xml"
//})
@Controller
@RequestMapping("/score")
public class ScoreController {

    private final static Logger logger = LoggerFactory.getLogger(ScoreController.class);

    @Autowired
    private ScoreService scoreService;

    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping("/select/graded")
    @ResponseBody
    public List<Score> scoreSelectGraded(GetScoreReq req) {

        ScoreMsg msg = (ScoreMsg) scoreService.getAllScore(req.getChannelID(),
                req.getCommodityID(), req.getGradedID(), req.getPagenum(), req.getPagesize());
        return msg.getScore();
    }


    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping("/select/grader")
    @ResponseBody
    public List<Score> scoreSelectGrader(GetScoreReq req) {

        ScoreMsg msg = (ScoreMsg) scoreService.getAllScoreOfGrader(req.getChannelID(),
                req.getCommodityID(), req.getGraderID(), req.getPagenum(), req.getPagesize());
        return msg.getScore();


    }


    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping(value = "/save")

    public
    @ResponseBody
    RootMsg addScore(@RequestBody ScoreBase scorebase) {
        System.out.println("This is add score");
        Score score = paserScore(scorebase);
        RootMsg msg = scoreService.addScore(score);
        return msg;
    }


    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping(value = "/update")

    public
    @ResponseBody
    RootMsg updateScore(@RequestBody ScoreBase scorebase) {
        Score score = paserScore(scorebase);
        RootMsg msg = scoreService.modifyEvaluate(score);
        return msg;
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping(value = "/delete")

    public
    @ResponseBody
    RootMsg addScore(long id) {
        RootMsg msg = scoreService.deleteEvaluate(id);
        return msg;
    }


    private Score paserScore(ScoreBase scorebase) {

        Score score = new Score();
        score.setId(scorebase.getId());
        score.setScoreRule(scorebase.getScoreRule());
        score.setGradeStrategyName(scorebase.getGradeStrategyName());
        score.setChannelID(scorebase.getChannelID());
        score.setCommodityID(scorebase.getCommodityID());
        score.setTradeOrder(scorebase.getTradeOrder());
        score.setEvaluateContent(scorebase.getEvaluateContent());
        score.setGradedID(scorebase.getGradedID());
        score.setGraderID(scorebase.getGraderID());
        score.setGradeStrategyID(scorebase.getGradeStrategyID());
        score.setTotalScore(scorebase.getTotalScore());
        return score;
    }

}
