package com.manyi.common.messagePush;

import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import com.manyi.common.messagePush.bean.PushedMessage;

import java.util.List;

/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public interface PushMessageService {

    /**
     * 新增推送消息
     * @param message
     */
  void addPushedMessage(PushedMessage message);


    /**
     * 查询用户私人推送消息
     * @param userId
     * @return
     */
    List<PushedMessage> queryPersonalMessage(long userId);


    /**
     * 查询公共推送消息(失效期未过)
     * @return
     */
    List<PushedMessage> queryPublicMessage();


  /**
   * 更新发送信息的状态
   * @param message
   */
    void updatePushedMessage(PushedMessage message);


  /**
   *
   * 发送未发送，且在有效期的公告给所有用户。
   */
    void sendPublicMessage2All();
}
