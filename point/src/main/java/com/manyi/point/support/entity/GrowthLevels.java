package com.manyi.point.support.entity;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.point.bean.GrowthLevel;

import java.util.List;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class GrowthLevels {

    /**
     * 当前service的级别
     */
    private List<GrowthLevel> growthLevelList;

    public List<GrowthLevel> getGrowthLevelList()
    {
        return growthLevelList;
    }

    public void setGrowthLevelList(List<GrowthLevel> growthLevelList)
    {
        this.growthLevelList = growthLevelList;
    }

    /**
     * 根据成长值获取level
     * @param growth
     * @return level
     */
    public int  getLevelByGrowth(int growth) throws BusinessException

    {

          for(GrowthLevel level : growthLevelList)
          {
              if(growth <= level.getEndValue() &&   growth>= level.getStartValue())
              {
                  return level.getLevel();
              }
          }
          throw new BusinessException(Type.GROWTH_LEVEL_ERROR);
    }


}
