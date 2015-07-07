package com.manyi.point.support;

import com.manyi.common.util.DateUtil;
import com.manyi.point.bean.PointEvent;
import com.manyi.point.bean.PointLogBean;
import com.manyi.point.bean.QueryPointLogCondition;
import com.manyi.point.support.PointStrategy;
import com.manyi.point.support.dao.ExpDao;
import com.manyi.point.support.dao.PointDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
@Service("dailyStrategy")
public class DailyStrategy implements PointStrategy {

    @Autowired
    private PointDao pointDao;

    public static final int   TIME_HOUR_LONG = 10;

    @Override
    public boolean validate(PointEvent event ,long userId) {
        QueryPointLogCondition condition = new QueryPointLogCondition();
        condition.setUserId(userId);
        condition.setEventCode(event.getEventCode());
        String dateNow  =DateUtil.getCurrentString(new Date());
        String start = dateNow.substring(0,TIME_HOUR_LONG)+" 00:00:00";
        String end = dateNow.substring(0,TIME_HOUR_LONG)+" 23:59:59";
        condition.setStarttime(start);
        condition.setEndtime(end);
        List<PointLogBean> list = pointDao.queryPointLog(condition);
        if(null == list || list.size() < event.getTime())
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}
