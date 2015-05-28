package com.manyi.common.message;

import com.manyi.base.exception.BusinessException;
import com.manyi.common.message.bean.IdentificationCode;
import com.manyi.common.message.bean.MessageSend;
import com.manyi.common.message.bean.MessageTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/5/4 .
 */
public interface MessageService {

    /**
     * 生成验证码
     * @return
     */
    public String createIdentificationCode();

    /**
     * 保存验证码
     * @param identificationCode
     */
    public void saveIdentificationCode(IdentificationCode identificationCode);

    /**
     *验证短信验证码是否有效接口
     * @param mobile
     * @param code
     * @return
     */
    public boolean isIdentificationCodeValid(String mobile,String type,String code) throws BusinessException;

    /**
     * 根据手机号查询验证码
     * @return
     */
    public String  queryIdentificationCode(IdentificationCode identificationCode) throws BusinessException;

    /**
     * 根据模板id查询模板内容
     * @param templateId
     * @return
     */
    public String queryTemplate(String templateId) throws BusinessException;

    /**
     *保存信息
     */
    public void saveMessage(MessageSend messageSend);

    /**
     *查询短信信息
     * @param messageSend
     * @return
     */
    public List<MessageSend> queryMessage(MessageSend messageSend);

    /**
     *查询短未发送信信息
     * @return
     */
    public List<MessageSend> queryUnsentMessage();

    /**
     *更新短信信息
     * @param messageSend
     * @return
     */
    public void updateMessage(MessageSend messageSend);

    /**
     * 发送短信
     * @param messageSend
     */
    public void sendMessage(MessageSend messageSend) throws Exception;

    /**
     * 发送短信验证码接口（外部调用）
     * @param mobile
     * @return
     */
    public boolean sendMessageCode(String mobile,String type,String templateId) throws Exception;

    /**
     * 发送短信接口（外部调用）
     * @param mobile
     * @param templateId
     * @param paras
     * @return
     */
    public void sendMessageService(String userId,String mobile,String type,String templateId,Map paras) throws BusinessException;

    /**
     *
     * @param userId
     * @param mobile
     * @param type
     * @param templateId
     * @param paras
     * @throws Exception
     */
    public void sendRealtimeMessage(String userId,String mobile,String type,String templateId,Map paras) throws Exception;
}
