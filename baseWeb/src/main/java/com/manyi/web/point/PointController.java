package com.manyi.web.point;

import com.manyi.point.PointService;
import com.manyi.point.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @Description: 积分控制层
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
@Controller
@RequestMapping("/point")
public class PointController {

    @Autowired
    private PointService pointService;



    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping("/sys/select/points")
    @ResponseBody
    public  QueryPointResult queryPoints( int page , int size ,Long userId) {

        QueryPointCondition condition = new QueryPointCondition();
        condition.setPageNum(page);
        condition.setPageSize(size);
        if(StringUtils.isEmpty(userId))
        {
            condition.setUserId(0);
        }
        return  pointService.queryPoints(condition);


    }



    /**
     * 查询日志
     *
     * @return
     */
    @RequestMapping("/sys/select/pointLogs")
    @ResponseBody
    public   List<PointLogBean>  queryPointLogs(Long userId) {
        QueryPointLogCondition condition = new QueryPointLogCondition();
        if(StringUtils.isEmpty(userId))
        {
            condition.setUserId(0);
        }
        else
        {
            condition.setUserId(userId);
        }
        PointLogResult result =  pointService.queryUserPointLog(condition);
        return   result.getList();
    }
}
