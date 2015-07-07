package com.manyi.common.message.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.common.message.MessageService;
import com.manyi.common.message.bean.IdentificationCode;
import com.manyi.common.message.bean.MessageSend;
import com.manyi.common.message.bean.MessageTemplate;
import com.manyi.common.message.support.dao.MessageDao;
import com.manyi.common.message.util.*;
import com.manyi.common.util.DoHttpRequest;
import com.manyi.common.util.ReadPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhaoyuxin
 * @version 1.0.0 2015-05-04
 * @Description: 短信操作
 * @reviewer:
 */
@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final String defaultTemplateId = ReadPropertiesUtil.readProperties("message.properties").getProperty("defaultTemplateId");

    private final String smsParam = ReadPropertiesUtil.readProperties("message.properties").getProperty("smsParam");

    private final String activeTime = ReadPropertiesUtil.readProperties("message.properties").getProperty("activeTime");

    private final String smsHttpUrl = ReadPropertiesUtil.readProperties("message.properties").getProperty("smsHttpUrl");

    private final String registerType = ReadPropertiesUtil.readProperties("message.properties").getProperty("registerType");

    private final String registerTemplateid = ReadPropertiesUtil.readProperties("message.properties").getProperty("registerTemplateid");

    private final String findPwdType = ReadPropertiesUtil.readProperties("message.properties").getProperty("findPwdType");

    private final String findPwdTemplateid = ReadPropertiesUtil.readProperties("message.properties").getProperty("findPwdTemplateid");

    private final String regPurseType = ReadPropertiesUtil.readProperties("message.properties").getProperty("regPurseType");

    private final String regPurseTemplateid = ReadPropertiesUtil.readProperties("message.properties").getProperty("regPurseTemplateid");

    private final static int TIME_CONVERSION = 60;

    @Autowired
    private MessageDao messageDao;

    private static Map<String, MessageTemplate> messageTemplateMap = new HashMap<String, MessageTemplate>();

    /**
     * 生成验证码
     *
     * @return
     */
    @Override
    public String createIdentificationCode() {
        return CreateIdentificationCode.createIdentificationCode();
    }

    /**
     * 保存验证码
     *
     * @param identificationCode
     */
    @Override
    public void saveIdentificationCode(IdentificationCode identificationCode) {
        messageDao.saveIdentificationCode(identificationCode);
    }

    /**
     * 判断验证码是否有效
     *
     * @param mobile
     * @param code
     * @return
     */
    @Override
    public boolean isIdentificationCodeValid(String mobile, String type, String code) throws BusinessException {
        boolean isMobileNO = IsMobileNo.isMobileNO(mobile);
        if (isMobileNO == false) {
            logger.error("手机号错误:" + mobile);
            throw new BusinessException(Type.WRONG_PHONENO);
        }
        Pattern pattern = Pattern.compile("\\d{6}");
        Matcher matcher = pattern.matcher(code);
        if (!matcher.matches()) {
            logger.error(mobile + "验证码错误:" + code);
            throw new BusinessException(Type.WORONG_CODE);
        }
        IdentificationCode identificationCode = new IdentificationCode();
        identificationCode.setMobile(mobile);
        identificationCode.setType(type);
        IdentificationCode IdentificationCode = messageDao.queryIdentificationCode(identificationCode);
        boolean result = false;
        if ((IdentificationCode != null) && (code.equals(IdentificationCode.getCode()))) {
            result = true;
        }
        return result;
    }

    /**
     * 验证注册验证码
     *
     * @param mobile
     * @param code
     * @return
     * @throws BusinessException
     */
    @Override
    public boolean isIdentificationCodeValidForRegister(String mobile, String code) throws BusinessException {
        String type = registerType;
        return isIdentificationCodeValid(mobile, type, code);
    }

    /**
     * 验证找回密码的验证码
     *
     * @param mobile
     * @param code
     * @return
     * @throws BusinessException
     */
    @Override
    public boolean isIdentificationCodeValidForFindPwd(String mobile, String code) throws BusinessException {
        String type = findPwdType;
        return isIdentificationCodeValid(mobile, type, code);
    }

    /**
     * 验证注册钱包的验证码
     *
     * @param mobile
     * @param code
     * @return
     * @throws BusinessException
     */
    @Override
    public boolean isIdentificationCodeValidForPurse(String mobile, String code) throws BusinessException {
        String type = regPurseType;
        return isIdentificationCodeValid(mobile, type, code);
    }

    /**
     * 根据手机号查询验证码
     *
     * @param identificationCode
     * @return
     */
    @Override
    public String queryIdentificationCode(IdentificationCode identificationCode) throws BusinessException {
        boolean isMobileNO = IsMobileNo.isMobileNO(identificationCode.getMobile());
        if (isMobileNO == false) {
            logger.error("手机号错误:" + identificationCode.getMobile());
            throw new BusinessException(Type.WRONG_PHONENO);
        }
        IdentificationCode IdentificationCode = messageDao.queryIdentificationCode(identificationCode);
        String code = null;
        if (IdentificationCode != null) {
            code = IdentificationCode.getCode();
        }
        return code;
    }

    /**
     * 根据模板id查询模板内容
     *
     * @param templateId
     * @return
     */
    @Override
    public String queryTemplate(String templateId) throws BusinessException {
        if (templateId == null) {
            throw new BusinessException(Type.TEMPLATEID_NULL);
        }
        MessageTemplate messageTemplate = messageTemplateMap.get(templateId);
        if (messageTemplate == null) {
            messageTemplate = messageDao.queryTemplate(templateId);
            if (messageTemplate != null)
                messageTemplateMap.put(templateId, messageTemplate);
        }
        String content = null;
        if (messageTemplate != null) {
            content = messageTemplate.getContent();
        }
        return content;
    }

    /**
     * 保存信息
     */
    @Override
    public void saveMessage(MessageSend messageSend) {
        messageDao.saveMessage(messageSend);
    }

    @Override
    public List<MessageSend> queryMessage(MessageSend messageSend) {
        return messageDao.queryMessage(messageSend);
    }

    @Override
    public List<MessageSend> queryUnsentMessage() {
        return messageDao.queryUnsentMessage();
    }

    @Override
    public void updateMessage(MessageSend messageSend) {
        messageDao.updateMessage(messageSend);
    }

    @Override
    public void sendMessage(MessageSend messageSend) throws Exception {
        String Json = MessageParaToJson.messageParaToJson(messageSend.getMobile(), messageSend.getContent());
        String param = smsParam + "=" + Json;
        DoHttpRequest.doPostRequest(smsHttpUrl, param);
    }

    /**
     * 注册发送短信验证码
     *
     * @param mobile
     * @return
     * @throws Exception
     */
    @Override
    public boolean sendMessageCodeForRegister(String mobile) throws Exception {
        String type = registerType;
        String templateId = registerTemplateid;
        return sendMessageCode(mobile, type, templateId);
    }

    /**
     * 找回密码发送短信验证码
     *
     * @param mobile
     * @return
     * @throws Exception
     */
    @Override
    public boolean sendMessageCodeForFindPwd(String mobile) throws Exception {
        String type = findPwdType;
        String templateId = findPwdTemplateid;
        return sendMessageCode(mobile, type, templateId);
    }


    /**
     * 注册钱包发送短信验证码
     *
     * @param mobile
     * @return
     * @throws Exception
     */
    @Override
    public boolean sendMessageCodeForPurse(String mobile) throws Exception {
        String type = regPurseType;
        String templateId = regPurseTemplateid;
        return sendMessageCode(mobile, type, templateId);
    }

    @Override
    public boolean sendMessageCode(String mobile, String type, String templateId) throws Exception {
//验证手机是否正确
        boolean isMobileNO = IsMobileNo.isMobileNO(mobile);
        if (isMobileNO == false) {
            logger.error("手机号错误:" + mobile);
            throw new BusinessException(Type.WRONG_PHONENO);
        }
        //生成验证码并保存
        String smsCode = createIdentificationCode();
        IdentificationCode identificationCod = new IdentificationCode();
        identificationCod.setActiveTime(activeTime);
        identificationCod.setMobile(mobile);
        identificationCod.setCode(smsCode);
        identificationCod.setCreateTime(new Date());
        identificationCod.setType(type);
        Map<String, String> Paras = new HashMap<String, String>();
        int seconds = Integer.parseInt(activeTime);
        String minutes = String.valueOf(seconds / TIME_CONVERSION);
        Paras.put("smsCode", smsCode);
        Paras.put("activeTime", minutes);
        String smsContent = null;
        //查询短信模板
        if (StringUtils.isEmpty(templateId)) {
            smsContent = queryTemplate(templateId);
        } else {
            smsContent = queryTemplate(defaultTemplateId);

        }
        smsContent = MessageBindPara.MessageBindPara(smsContent, Paras);
        String json = MessageParaToJson.messageParaToJson(mobile, smsContent);
        String param = smsParam + "=" + json;
        String smsResult = DoHttpRequest.doPostRequest(smsHttpUrl, param);
        ObjectMapper objectMapper = new ObjectMapper();
        Map maps = objectMapper.readValue(smsResult, Map.class);
        Map rspHeader = (Map) maps.get("rspHeader");
        String rspCode = (String) rspHeader.get("rspCode");
        boolean result = false;
        if ("00000000".equals(rspCode)) {
            result = true;
            saveIdentificationCode(identificationCod);
        }
        return result;
    }

    @Override
    public void sendRealtimeMessage(String userId, String mobile, String type, String templateId, Map paras) throws Exception {
        //验证手机是否正确
        boolean isMobileNO = IsMobileNo.isMobileNO(mobile);
        if (isMobileNO == false) {
            logger.error("手机号错误:" + mobile);
            throw new BusinessException(Type.WRONG_PHONENO);
        }
        String smsContent = null;
        //查询短信模板
        if (StringUtils.isEmpty(templateId)) {
            smsContent = queryTemplate(templateId);
        } else {
            smsContent = queryTemplate(defaultTemplateId);

        }
        smsContent = MessageBindPara.MessageBindPara(smsContent, paras);

        MessageSend messageSend = new MessageSend();
        messageSend.setUserId(userId);
        messageSend.setMobile(mobile);
        messageSend.setCreateTime(new Date());
        messageSend.setContent(smsContent);
        messageSend.setType(type);
        messageSend.setState("unsent");
        //保存未发送短信
        saveMessage(messageSend);

        smsContent = MessageBindPara.MessageBindPara(smsContent, paras);
        String json = MessageParaToJson.messageParaToJson(mobile, smsContent);
        String param = smsParam + "=" + json;
        DoHttpRequest.doPostRequest(smsHttpUrl, param);

        //更新短信状态及发送时间
        updateMessage(messageSend);
    }
}
