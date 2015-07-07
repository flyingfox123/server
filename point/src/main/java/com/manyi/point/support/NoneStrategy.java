package com.manyi.point.support;

import com.manyi.point.bean.PointEvent;
import com.manyi.point.bean.PointLogBean;
import com.manyi.point.bean.QueryPointLogCondition;
import com.manyi.point.support.PointStrategy;
import com.manyi.point.support.dao.ExpDao;
import com.manyi.point.support.dao.PointDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
@Service("noneStrategy")
public class NoneStrategy implements PointStrategy {

    @Override
    public boolean validate(PointEvent event ,long userId) {
            return true;
    }
}
