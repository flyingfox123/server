package com.manyi.point.support.dao;

import com.manyi.point.support.entity.DimensionExp;

import java.util.List;

/**
 * Created by Administrator on 2015/4/24.
 */
public interface ExpDao {

    /**
     *
     * @param code
     * @return
     */
    List<DimensionExp>  getExpList(String code);
}
