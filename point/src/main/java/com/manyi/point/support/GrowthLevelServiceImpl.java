package com.manyi.point.support;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.point.GrowthLevelService;
import com.manyi.point.bean.GrowthLevel;
import com.manyi.point.support.dao.GrowthLevelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/4/10.
 */
@Service("growthLevelService")
public class GrowthLevelServiceImpl implements GrowthLevelService
{

    public static int SUCCESS= 0;
    @Autowired
    private GrowthLevelDao levelDao;


    @Override
    public int addLevel(GrowthLevel level) {
        levelDao.createLevel(level);
        return SUCCESS;
    }

    @Override
    public int deleteLevel(int id) {
        levelDao.deleteLevel(id);
        return SUCCESS;
    }

    @Override
    public int updateLevel(GrowthLevel level)
    {
        levelDao.updateLevel(level);
        return SUCCESS;
    }

    @Override
    public List<GrowthLevel> queryLevels(String serviceId) {
        return levelDao.getLevelList(serviceId);
    }

    @Override
    public int getLevelByGrowth(String serviceId, int growth) throws BusinessException {

        List<GrowthLevel>  growthLevelList = levelDao.getLevelList(serviceId);
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
