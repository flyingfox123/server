package com.manyi.common.param.support.dao;

import com.manyi.common.param.support.entity.Param;

import java.util.List;

/**
 * @author ZhangYuFeng on 2015/6/23 0023,14:38.
 * @Description:
 * @reviewer:
 */
public interface ParamDao {
    List<Param> getParamList(@org.apache.ibatis.annotations.Param("paramNameEn")String paramNameEn);
}
