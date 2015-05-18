package com.manyi.common.message.support;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.common.message.bean.IdentificationCode;
import com.manyi.common.message.bean.MessageSend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-common-servlet-test.xml"})
public class MessageServiceImplTest {

    @Autowired
    public MessageServiceImpl messageServiceImpl;

    @Test
    public void testCreateIdentificationCode() throws Exception {
        String code = messageServiceImpl.createIdentificationCode();
        Pattern p = Pattern.compile("\\d{6}");
        Matcher m = p.matcher(code);
        assertEquals(m.matches(),true);
    }

    @Test
    public void testSaveIdentificationCode() throws Exception {
        IdentificationCode identificationCode = new IdentificationCode();
        identificationCode.setCode("456789");
        identificationCode.setMobile("15665888893");
        identificationCode.setActiveTime("120");
        identificationCode.setType("login");
        identificationCode.setCreateTime(new Date());
        messageServiceImpl.saveIdentificationCode(identificationCode);
        String code = messageServiceImpl.queryIdentificationCode(identificationCode);
        assertEquals(code,"456789");
    }

    @Test
    public void testQueryIdentificationCode() {
        try {
            IdentificationCode identificationCode = new IdentificationCode();
            identificationCode.setCode("456789");
            identificationCode.setMobile("15665888893");
            identificationCode.setActiveTime("120");
            identificationCode.setType("login");
            identificationCode.setCreateTime(new Date());
            messageServiceImpl.saveIdentificationCode(identificationCode);
            String code = messageServiceImpl.queryIdentificationCode(identificationCode);
            assertEquals(code, "456789");
        } catch (BusinessException ex) {
            assertEquals(Type.WRONG_PHONENO, ex.getErrorType());
        }
    }

    @Test
    public void testIsIdentificationCodeValid() throws Exception {
        try {
            IdentificationCode identificationCode = new IdentificationCode();
            identificationCode.setCode("110110");
            identificationCode.setMobile("15665888893");
            identificationCode.setActiveTime("120");
            identificationCode.setType("login");
            identificationCode.setCreateTime(new Date());
            messageServiceImpl.saveIdentificationCode(identificationCode);
            String code = messageServiceImpl.queryIdentificationCode(identificationCode);
            boolean result = messageServiceImpl.isIdentificationCodeValid("15665888893", "login", code);
            System.out.println(result);
            assertEquals(result, true);
        } catch (BusinessException ex) {
            assertEquals(Type.WRONG_PHONENO, ex.getErrorType());
            //assertEquals(Type.WORONG_CODE, ex.getErrorType());
        }
    }

    @Test
    public void testQueryTemplate() throws Exception {
        try {
            String templateId="MessageCode";
            //String templateId = null;
            String content = messageServiceImpl.queryTemplate(templateId);
            assertEquals(content,"您申请了手机号码注册，验证码为：<smsCode>,两分钟内有效，请在注册页面中及时输入以完成注册。");
        } catch (BusinessException ex) {
            assertEquals(Type.TEMPLATEID_NULL, ex.getErrorType());
        }
    }

    @Test
    public void testSaveMessage() throws Exception {
        MessageSend messageSend = new MessageSend();
        messageSend.setType("testSave");
        messageSend.setState("unsent");
        messageSend.setMobile("15665888893");
        messageSend.setUserId("5431111");
        messageSend.setCreateTime(new Date());
        messageSend.setContent("您的验证码为876430,请在有效时间内使用");
        messageServiceImpl.saveMessage(messageSend);
        List<MessageSend> messageSendList = messageServiceImpl.queryMessage(messageSend);
        MessageSend messageSend1 = messageSendList.get(0);
        assertEquals(messageSend1.getContent(),"您的验证码为876430,请在有效时间内使用");
        assertEquals(messageSend1.getType(),"testSave");
        assertEquals(messageSend1.getMobile(),"15665888893");
        assertEquals(messageSend1.getUserId(),"5431111");
        assertEquals(messageSend1.getState(),"unsent");
    }

    @Test
    public void testQueryMessage() throws Exception {
        MessageSend messageSend = new MessageSend();
        messageSend.setType("testQuery");
        messageSend.setState("unsent");
        messageSend.setMobile("15665888893");
        messageSend.setUserId("5431111");
        messageSend.setCreateTime(new Date());
        messageSend.setContent("您的验证码为876430,请在有效时间内使用");
        messageServiceImpl.saveMessage(messageSend);
        List<MessageSend> messageSendList = messageServiceImpl.queryMessage(messageSend);
        MessageSend messageSend1 = messageSendList.get(0);
        assertEquals(messageSend1.getContent(),"您的验证码为876430,请在有效时间内使用");
        assertEquals(messageSend1.getType(),"testQuery");
        assertEquals(messageSend1.getMobile(),"15665888893");
        assertEquals(messageSend1.getUserId(),"5431111");
        assertEquals(messageSend1.getState(),"unsent");

    }

    @Test
    public void testUpdateMessageSendtime() throws Exception {
        MessageSend messageSend = new MessageSend();
        messageSend.setType("testUpdate");
        messageSend.setState("unsent");
        messageSend.setMobile("15665888893");
        messageSend.setUserId("5431112");
        messageSend.setCreateTime(new Date());
        messageSend.setContent("您的验证码为876430,请在有效时间内使用");
        messageServiceImpl.saveMessage(messageSend);
        List<MessageSend> messageSendList1 = messageServiceImpl.queryMessage(messageSend);
        MessageSend messageSend1 = messageSendList1.get(0);
        assertEquals(messageSend1.getContent(),"您的验证码为876430,请在有效时间内使用");
        assertEquals(messageSend1.getType(),"testUpdate");
        assertEquals(messageSend1.getMobile(),"15665888893");
        assertEquals(messageSend1.getUserId(),"5431112");
        assertEquals(messageSend1.getState(),"unsent");
        messageSend.setId(messageSend1.getId());
        messageSend.setSendTime(new Date());
        messageServiceImpl.updateMessage(messageSend);
        MessageSend messageSend0 = new MessageSend();
        messageSend0.setId(messageSend1.getId());
        List<MessageSend> messageSendList2 = messageServiceImpl.queryMessage(messageSend0);
        MessageSend messageSend2 = messageSendList2.get(0);
        assertEquals(messageSend2.getState(),"sent");
        assertNotNull(messageSend2.getSendTime());
    }

    @Test
    public void testSendMessage() throws Exception {
        MessageSend messageSend = new MessageSend();
        messageSend.setMobile("15665888893");
        messageSend.setContent("你好，你的验证码为121211");
        messageServiceImpl.sendMessage(messageSend);
    }

    @Test
    public void testQueryUnsentMessage() throws Exception {
        List<MessageSend> messageSendList0 = messageServiceImpl.queryUnsentMessage();
        assertEquals(messageSendList0.size()==0,true);
        MessageSend messageSend = new MessageSend();
        messageSend.setType("testQuery");
        messageSend.setState("unsent");
        messageSend.setMobile("15665888893");
        messageSend.setUserId("5431111");
        messageSend.setCreateTime(new Date());
        messageSend.setContent("您的验证码为876430,请在有效时间内使用");
        messageServiceImpl.saveMessage(messageSend);
        List<MessageSend> messageSendList = messageServiceImpl.queryUnsentMessage();
        assertEquals(messageSendList.size()>0,true);
    }

    @Test
    public void testSendMessageCode() throws Exception {
        IdentificationCode identificationCode = new IdentificationCode();
        identificationCode.setMobile("15665888893");
        identificationCode.setType("test");
        String code = messageServiceImpl.queryIdentificationCode(identificationCode);
        assertNull(code);
        messageServiceImpl.sendMessageCode("15665888893","test","");
        code = messageServiceImpl.queryIdentificationCode(identificationCode);
        assertNotNull(code);
    }

    @Test
    public void testSendMessageService() throws Exception {
        MessageSend messageSend = new MessageSend();
        messageSend.setState("unsent");
        messageSend.setMobile("15665888893");
        messageSend.setType("register");
        List<MessageSend> list0 = messageServiceImpl.queryMessage(messageSend);
        assertEquals(list0.size()==0,true);
        Map paras = new HashMap();
        paras.put("name","校长");
        messageServiceImpl.sendMessageService("101","15665888893","register","newUser",paras);
        List<MessageSend> list1 = messageServiceImpl.queryMessage(messageSend);
        assertEquals(list1.size()>0,true);
    }
    @Test
    public void sendRealtimeMessage() throws Exception {
        MessageSend messageSend = new MessageSend();
        messageSend.setUserId("102");
        messageSend.setState("unsent");
        messageSend.setMobile("15665888893");
        messageSend.setType("register");
        List<MessageSend> list0 = messageServiceImpl.queryMessage(messageSend);
        assertEquals(list0.size()==0,true);
        Map paras = new HashMap();
        paras.put("name","校长");
        messageServiceImpl.sendRealtimeMessage("102", "15665888893", "register", "newUser", paras);
        messageSend.setState("sent");
        List<MessageSend> list1 = messageServiceImpl.queryMessage(messageSend);
        assertEquals(list1.size()>0,true);
    }
}