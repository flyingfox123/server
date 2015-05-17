package com.manyi.rating.controller;

import com.manyi.rating.entity.common.RootMsg;
import com.manyi.rating.entity.score.GradeStrategy;
import com.manyi.rating.entity.score.GradeStrategyItem;
import com.manyi.rating.entity.score.StrategyMsg;
import com.manyi.rating.ScoreStrategyService;
import com.manyi.rating.entity.score.StrategysMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by zhangyf on 2015/3/20 0020.
 */
@Controller
@RequestMapping("/score/scoreStrategy")
public class ScoreStrategyController {

    @Autowired
    private ScoreStrategyService service;

    @RequestMapping("/selectStrategy")
    @ResponseBody
    public List<GradeStrategy> getGradeStrategy() {
        String gradeStrategyID = "1";//strategy.getGradeStrategyID();
        String scoreRule ="1";//strategy.getScoreRule();
        String gradeStrategyName = "1";//strategy.getGradeStrategyName();
        StrategysMsg msg = service.getScoreStrategys();
        return msg.getList();
    }

    @RequestMapping(value = {"/getGradeStrategyItem"})
    @ResponseBody
    public List<GradeStrategyItem> getGradeStrategyItem(String gradeStrategyID){
        StrategyMsg msg = service.getScoreStrategy(gradeStrategyID);
        GradeStrategy strategy = msg.getStrategy();
        List<GradeStrategyItem> listItem = strategy.getStrategyItem();
        return listItem;
    }

    @RequestMapping(value = {"/updateScoreStrategy"})
    @ResponseBody
    public RootMsg updateScoreStrategy(@Valid @RequestBody GradeStrategy strategy){
        RootMsg msg = service.editStrategy(strategy);
        return msg;
    }

    @RequestMapping(value = {"/deleteScoreStratety"})
    @ResponseBody
    public RootMsg deleteScoreStratety(String gradeStrategyID) {
        RootMsg msg = service.deleteStrategy(gradeStrategyID);
        return msg;
    }
}
