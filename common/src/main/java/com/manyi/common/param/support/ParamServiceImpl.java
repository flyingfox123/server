package com.manyi.common.param.support;

import com.manyi.common.param.ParamService;
import com.manyi.common.param.support.dao.ParamDao;
import com.manyi.common.param.support.entity.Param;
import com.manyi.common.util.Constant.ParamName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhangYuFeng on 2015/6/23 0023,14:37.
 * @Description:
 * @reviewer:
 */
@Service
public class ParamServiceImpl implements ParamService {

    @Autowired
    private ParamDao paramDao;

    @Override
    public List<Param> getParamList(ParamName paramNameEn) {
        return paramDao.getParamList(paramNameEn.getParamName());
    }
}
