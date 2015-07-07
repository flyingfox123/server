package com.manyi.point.support;

import com.manyi.point.bean.PointEvent;
import com.manyi.point.bean.PointLogBean;
import com.manyi.point.bean.QueryPointLogCondition;
import com.manyi.point.support.PointStrategy;
import com.manyi.point.support.dao.ExpDao;
import com.manyi.point.support.dao.PointDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
@Service("onceStrategy")
public class OnceStrategy implements PointStrategy {

    @Autowired
    private PointDao pointDao;

    @Override
    public boolean validate(PointEvent event ,long userId) {
        QueryPointLogCondition condition = new QueryPointLogCondition();
        condition.setUserId(userId);
        condition.setEventCode(event.getEventCode());
        List<PointLogBean> list = pointDao.queryPointLog(condition);
        if(StringUtils.isEmpty(list)|| list.size()==0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}
