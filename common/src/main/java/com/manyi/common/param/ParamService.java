package com.manyi.common.param;

import com.manyi.common.param.support.entity.Param;
import com.manyi.common.util.Constant.ParamName;

import java.util.List;

/**
 * @author ZhangYuFeng on 2015/6/23 0023,14:32.
 * @Description:
 * @reviewer:
 */
public interface ParamService {
    List<Param> getParamList(ParamName paramNameEn);
}
