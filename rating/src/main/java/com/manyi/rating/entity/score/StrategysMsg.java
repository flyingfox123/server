package com.manyi.rating.entity.score;


import com.manyi.rating.entity.common.RootMsg;

import java.util.List;

/**
 * Created by Administrator on 2015/3/18.
 */
public class StrategysMsg extends RootMsg {

  private List<GradeStrategy> list;

    public List<GradeStrategy> getList() {
        return list;
    }

    public void setList(List<GradeStrategy> list) {
        this.list = list;
    }
}
