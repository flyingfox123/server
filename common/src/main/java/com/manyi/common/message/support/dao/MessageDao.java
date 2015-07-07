package com.manyi.common.message.support.dao;

import com.manyi.common.message.bean.IdentificationCode;
import com.manyi.common.message.bean.MessageSend;
import com.manyi.common.message.bean.MessageTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 短信Dao
 * @author zhaoyuxin
 * @version: 1.0.0  2015-05-04.
 * @reviewer:
 */
@Repository
public interface MessageDao {

    /**
     * 保存验证码
     */
    public void saveIdentificationCode(IdentificationCode identificationCode);

    /**
     *根据手机号查询验证码
     * @return
     */
    public IdentificationCode queryIdentificationCode(IdentificationCode identificationCode);

    /**
     *
     * @return
     */
    public MessageTemplate queryTemplate(String templateId);

    /**
     *保存短信信息
     */
    public void saveMessage(MessageSend messageSend);

    /**
     * 更新短信信息发送时间
     * @param messageSend
     * @return
     */
    public void updateMessage(MessageSend messageSend);

    /**
     *查询短信
     * @param messageSend
     * @return
     */
    public List<MessageSend> queryMessage(MessageSend messageSend);

    /**
     *查询未发送短信
     * @return
     */
    public List<MessageSend> queryUnsentMessage();

}
