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
 * @Description: 短信Service
 * @author zhaoyuxin
 * @version: 1.0.0  2015-05-04.
 * @reviewer:
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
     *
     * @param userId
     * @param mobile
     * @param type
     * @param templateId
     * @param paras
     * @throws Exception
     */
    public void sendRealtimeMessage(String userId,String mobile,String type,String templateId,Map paras) throws Exception;

    /**
     * 注册钱包发送短信验证码
     * @param mobile
     * @return
     * @throws Exception
     */
    public boolean  sendMessageCodeForPurse(String mobile)throws Exception;


    /**
     *验证注册钱包的验证码
     * @param mobile
     * @param code
     * @return
     * @throws BusinessException
     */
    public boolean isIdentificationCodeValidForPurse(String mobile,String code) throws BusinessException;


    /**
     *验证找回密码的验证码
     * @param mobile
     * @param code
     * @return
     * @throws BusinessException
     */
    public boolean isIdentificationCodeValidForFindPwd(String mobile,String code) throws BusinessException;


    /**
     *验证注册验证码
     * @param mobile
     * @param code
     * @return
     * @throws BusinessException
     */
    public boolean isIdentificationCodeValidForRegister(String mobile,String code) throws BusinessException;


    /**
     * 注册发送短信验证码
     * @param mobile
     * @return
     * @throws Exception
     */
    public boolean  sendMessageCodeForRegister(String mobile)throws Exception;


    /**
     * 找回密码发送短信验证码
     * @param mobile
     * @return
     * @throws Exception
     */
    public boolean  sendMessageCodeForFindPwd(String mobile)throws Exception;
}
