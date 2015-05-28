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
import com.manyi.common.util.ReadPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 短信操作
 * Created by zhaoyuxin on 2015/5/4.
 */
@Service
public class MessageServiceImpl implements MessageService {

    private  static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    private  static String defaultTemplateId = ReadPropertiesUtil.readProperties("message.properties").getProperty("defaultTemplateId");

    public static String smsParam = ReadPropertiesUtil.readProperties("message.properties").getProperty("smsParam");

    public static String activeTime = ReadPropertiesUtil.readProperties("message.properties").getProperty("activeTime");

    public static String smsHttpUrl = ReadPropertiesUtil.readProperties("message.properties").getProperty("smsHttpUrl");

    @Autowired
    private MessageDao messageDao;

    public static Map<String,MessageTemplate>  messageTemplateMap = new HashMap<String, MessageTemplate>();

    /**
     * 生成验证码
     * @return
     */
    public String createIdentificationCode(){
        return CreateIdentificationCode.createIdentificationCode();
    }

    /**
     * 保存验证码
     * @param identificationCode
     */
    public void saveIdentificationCode(IdentificationCode identificationCode){
        messageDao.saveIdentificationCode(identificationCode);
    }

    /**
     * 判断验证码是否有效
     * @param mobile
     * @param code
     * @return
     */
    public boolean isIdentificationCodeValid(String mobile,String type,String code) throws BusinessException {
        boolean isMobileNO = IsMobileNo.isMobileNO(mobile);
        if (isMobileNO==false){
            logger.error("手机号错误:"+mobile);
            throw new BusinessException(Type.WRONG_PHONENO);
        }
        Pattern p = Pattern.compile("\\d{6}");
        Matcher m = p.matcher(code);
        if(!m.matches()){
            logger.error(mobile+"验证码错误:"+code);
            throw new BusinessException(Type.WORONG_CODE);
        }
        IdentificationCode identificationCode = new IdentificationCode();
        identificationCode.setMobile(mobile);
        identificationCode.setType(type);
        IdentificationCode IdentificationCode = messageDao.queryIdentificationCode(identificationCode);
        boolean result=false;
        if(IdentificationCode!=null){
            if(code.equals(IdentificationCode.getCode())){
                result=true;
            }
        }
        return result;
    }
    /**
     *根据手机号查询验证码
     * @param identificationCode
     * @return
     */
    public String queryIdentificationCode(IdentificationCode identificationCode) throws BusinessException {
        boolean isMobileNO = IsMobileNo.isMobileNO(identificationCode.getMobile());
        if (isMobileNO==false){
            logger.error("手机号错误:"+identificationCode.getMobile());
            throw new BusinessException(Type.WRONG_PHONENO);
        }
        IdentificationCode IdentificationCode = messageDao.queryIdentificationCode(identificationCode);
        String code = null;
        if(IdentificationCode!=null){
            code=IdentificationCode.getCode();
        }
        return code;
    }

    /**
     * 根据模板id查询模板内容
     * @return
     * @param templateId
     */
    public String queryTemplate(String templateId) throws BusinessException {
        if (templateId == null) {
            throw new BusinessException(Type.TEMPLATEID_NULL);
        }
        MessageTemplate messageTemplate = messageTemplateMap.get(templateId);
        if(messageTemplate==null) {
            messageTemplate = messageDao.queryTemplate(templateId);
            if(messageTemplate!=null)
            messageTemplateMap.put(templateId,messageTemplate);
        }
        String content = null;
        if (messageTemplate != null) {
            content = messageTemplate.getContent();
        }
        return content;
    }

    /**
     *保存信息
     */
    public void saveMessage(MessageSend messageSend){
        messageDao.saveMessage(messageSend);
    }

    @Override
    public List<MessageSend> queryMessage(MessageSend messageSend) {
        return messageDao.queryMessage(messageSend);
    }

    @Override
    public List<MessageSend> queryUnsentMessage(){
        return  messageDao.queryUnsentMessage();
    }

    @Override
    public void updateMessage(MessageSend messageSend) {
        messageDao.updateMessage(messageSend);
    }

    @Override
    public void sendMessage(MessageSend messageSend) throws Exception {
            String Json = MessageParaToJson.messageParaToJson(messageSend.getMobile(),messageSend.getContent());
            String param = smsParam + "=" + Json;
            DoHttpRequest.DoHttpRequest(smsHttpUrl,param,"POST");
    }

    public boolean  sendMessageCode(String mobile,String type,String templateId) throws Exception {
        boolean result=false;
        ObjectMapper objectMapper = new ObjectMapper();
        if("".equals(templateId)){
            templateId=defaultTemplateId;
        }//验证手机是否正确
        boolean isMobileNO = IsMobileNo.isMobileNO(mobile);
        if (isMobileNO==false){
            logger.error("手机号错误:"+mobile);
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
        Map<String,String> Paras = new HashMap<String,String>();
        Paras.put("smsCode",smsCode);
        //查询短信模板
        String smsContent = queryTemplate(templateId);
        smsContent = MessageBindPara.MessageBindPara(smsContent,Paras);
        String json = MessageParaToJson.messageParaToJson(mobile,smsContent);
        String param = smsParam + "=" + json;
        String smsResult  = DoHttpRequest.DoHttpRequest(smsHttpUrl,param,"POST");
        Map maps = objectMapper.readValue(smsResult, Map.class);
        Map rspHeader = (Map)maps.get("rspHeader");
        String rspCode = (String)rspHeader.get("rspCode");
        if("00000000".equals(rspCode)){
            result=true;
            saveIdentificationCode(identificationCod);
        }
        return result;
    }
    public void sendRealtimeMessage(String userId,String mobile,String type,String templateId,Map paras) throws Exception {
        if("".equals(templateId)){
            templateId=defaultTemplateId;
        }//验证手机是否正确
        boolean isMobileNO = IsMobileNo.isMobileNO(mobile);
        if (isMobileNO==false){
            logger.error("手机号错误:"+mobile);
            throw new BusinessException(Type.WRONG_PHONENO);
        }
        //查询短信模板
        String smsContent = queryTemplate(templateId);
        smsContent = MessageBindPara.MessageBindPara(smsContent,paras);

        MessageSend messageSend = new MessageSend();
        messageSend.setUserId(userId);
        messageSend.setMobile(mobile);
        messageSend.setCreateTime(new Date());
        messageSend.setContent(smsContent);
        messageSend.setType(type);
        messageSend.setState("sent");
        messageSend.setSendTime(new Date());

        smsContent = MessageBindPara.MessageBindPara(smsContent,paras);
        String json = MessageParaToJson.messageParaToJson(mobile,smsContent);
        String param = smsParam + "=" + json;
        DoHttpRequest.DoHttpRequest(smsHttpUrl,param,"POST");

        //保存已发送短信
        saveMessage(messageSend);
    }
    @Override
    public void sendMessageService(String userId,String mobile,String type,String templateId,Map paras) throws BusinessException {
        boolean isMobileNO = IsMobileNo.isMobileNO(mobile);
        if (isMobileNO==false){
            logger.error("手机号错误:"+mobile);
            throw new BusinessException(Type.WRONG_PHONENO);
        }
        //查询短信模板
        String smsContent = queryTemplate(templateId);
        smsContent = MessageBindPara.MessageBindPara(smsContent,paras);

        MessageSend messageSend = new MessageSend();
        messageSend.setUserId(userId);
        messageSend.setMobile(mobile);
        messageSend.setCreateTime(new Date());
        messageSend.setContent(smsContent);
        messageSend.setType(type);
        messageSend.setState("unsent");

        saveMessage(messageSend);
    }
}
