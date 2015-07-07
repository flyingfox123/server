package com.manyi.business.highwaytoll.support.dao;

import com.manyi.business.highwaytoll.bean.HistoryRouteBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 通行费Dao
 * @author zhaoyuxin
 * @version: 1.0.0  2015-06-16.
 * @reviewer:
 */
@Repository
public interface HighwayTollDao {
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
}
