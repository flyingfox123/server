package com.manyi.common.messagePush.support.dao;

import com.manyi.common.messagePush.bean.PushedMessage;
import com.manyi.common.messagePush.bean.QueryPushMessageCondition;

import java.util.List;

/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public interface PushMessageDao {

    void addPushedMessage(PushedMessage message);

    List<PushedMessage> queryPushedMessage(QueryPushMessageCondition condition);

    void updatePushedMessage(PushedMessage message);
}
