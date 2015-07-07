package com.manyi.business.highwaytoll;

import com.manyi.business.highwaytoll.bean.HighwayResponseBean;
import com.manyi.business.highwaytoll.bean.HighwayTollBean;
import com.manyi.business.highwaytoll.bean.HistoryRouteBean;

import java.io.IOException;
import java.util.List;

/**
 * @Description: 通行费Service
 * @author zhaoyuxin
 * @version: 1.0.0 2015-06-16
 * @reviewer:
 */
public interface HighwayTollService {

    /**
     * 保存历史路线
     * @param historyRouteBean
     * @return
     */
    void saveHistoryRoute(HistoryRouteBean historyRouteBean);

    /**
     * 取得历史路线
     * @param userId
     * @return
     */
    List<HistoryRouteBean> getHistoryRoute(long userId);

    /**
     * 删除历史路线
     * @param userId
     * @return
     */
    void deleteHistoryRoute(long userId);


    /**
     * 获取总费用
     * @param highwayTollBean
     * @return
     */
    HighwayResponseBean getTotalFee(HighwayTollBean highwayTollBean) throws IOException;
}

