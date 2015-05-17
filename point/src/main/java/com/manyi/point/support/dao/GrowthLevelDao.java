package com.manyi.point.support.dao;

import com.manyi.point.bean.GrowthLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2015/4/10.
 */
public interface GrowthLevelDao {

    /**
     * 增加一个级别
     * @param level
     * @return
     */
    int createLevel(GrowthLevel level);



    /**
     * 删除一个级别
     * @param id
     * @return
     */
    int deleteLevel(int id);


    /**
     * 根据服务获取评价等级列表
     * @param serviceId
     * @return
     */
    List<GrowthLevel> getLevelList( @Param("serviceId") String serviceId) ;

    /**
     * 更新级别
     * @param level
     * @return
     */
    int updateLevel(GrowthLevel level);
}
