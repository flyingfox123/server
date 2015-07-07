package com.manyi.point.support;

import com.manyi.point.bean.PointEvent;
import com.manyi.point.support.dao.ExpDao;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public interface PointStrategy {

    public boolean validate(PointEvent event, long userId);

}
