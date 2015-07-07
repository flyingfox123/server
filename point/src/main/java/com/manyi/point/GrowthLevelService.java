package com.manyi.point;

/**
 * Created by Administrator on 2015/4/8.
 */

import com.manyi.base.exception.BusinessException;
import com.manyi.point.bean.GrowthLevel;

import java.util.*;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public interface GrowthLevelService {
    /**
     * 增加一个级别
     * */
    int addLevel(GrowthLevel level);
    /**
     * 删除一个级别
     * */
    int deleteLevel(int id);
    /**
     *  更新一个级别
     * */
    int updateLevel(GrowthLevel level);
    /**
     * 条件查询级别
     * */
    List<GrowthLevel> queryLevels(String serviceId);
    /**
     * 根据成长值获取级别
     **/
    int getLevelByGrowth(String servId , int growth) throws BusinessException;

}
