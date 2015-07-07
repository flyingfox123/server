package com.manyi.webext.highwayToll;

import com.manyi.base.entity.State;
import com.manyi.business.highwaytoll.HighwayTollService;
import com.manyi.business.highwaytoll.bean.HighwayResponseBean;
import com.manyi.business.highwaytoll.bean.HighwayTollBean;
import com.manyi.business.highwaytoll.bean.HistoryRouteBean;
import com.manyi.common.bean.response.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * @Description: 高速通行费Controller
 * @author zhaoyuxin
 * @version 1.0.0  2015-06-17.
 * @reviewer:
 */
@Controller
@RequestMapping("/highwayToll")
public class HighwayTollController {

    public static final Logger logger = LoggerFactory.getLogger(HighwayTollController.class);

    @Autowired
    private HighwayTollService highwayTollService;

    /**
     * 保存历史线路
     * @param historyRouteBean
     */
    @RequestMapping("/saveRoute")
    @ResponseBody
    public ResponseBean saveHistoryRoute(@RequestBody HistoryRouteBean historyRouteBean){
        highwayTollService.saveHistoryRoute(historyRouteBean);
        ResponseBean responseBean = new ResponseBean();
        responseBean.setState(State.SUCCESS.getString());
        return responseBean;
    }

    /**
     * 查询历史线路
     * @param historyRouteBean
     * @return
     */
    @RequestMapping("/getRoute")
    @ResponseBody
    public ResponseBean getHistoryRoute(@RequestBody HistoryRouteBean historyRouteBean){
        ResponseBean responseBean = new ResponseBean();
        List<HistoryRouteBean> historyRouteBeans = highwayTollService.getHistoryRoute(historyRouteBean.getUserId());
        responseBean.setBody(historyRouteBeans);
        responseBean.setState(State.SUCCESS.getString());
        return responseBean;
    }

    /**
     * 清空历史线路
     * @param historyRouteBean
     * @return
     */
    @RequestMapping("/clearRoute")
    @ResponseBody
    public ResponseBean deleteHistoryRoute(@RequestBody HistoryRouteBean historyRouteBean){
        highwayTollService.deleteHistoryRoute(historyRouteBean.getUserId());
        ResponseBean responseBean = new ResponseBean();
        responseBean.setState(State.SUCCESS.getString());
        return responseBean;
    }

    /**
     * 获取总费用
     * @param highwayTollBean
     * @return
     */
    @RequestMapping("/getTotalFee")
    @ResponseBody
    public ResponseBean getTotalFee(@RequestBody HighwayTollBean highwayTollBean){
        ResponseBean responseBean = new ResponseBean();
        try {
            HighwayResponseBean highwayResponseBean = highwayTollService.getTotalFee(highwayTollBean);
            responseBean.setBody(highwayResponseBean);
            responseBean.setState(State.SUCCESS.getString());
        } catch (IOException e) {
            responseBean.setErrMsg("error");
            logger.error("请求QQ地图失败！",e);
        }
        return responseBean;
    }
}
