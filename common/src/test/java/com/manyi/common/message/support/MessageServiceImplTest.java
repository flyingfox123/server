package com.manyi.common.message.support;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.common.message.bean.IdentificationCode;
import com.manyi.common.message.bean.MessageSend;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.*;

import static org.junit.Assert.*;

public class MessageServiceImplTest {

   /* public MessageServiceImpl messageServiceImpl;

    @Before
    public void setUp() throws Exception {
        ApplicationContext aCtx = new FileSystemXmlApplicationContext("classpath*:spring-common-servlet.xml");
        this.messageServiceImpl = (MessageServiceImpl) aCtx.getBean("messageServiceImpl");
    }

    @Test
    public void testCreateIdentificationCode() throws Exception {
        System.out.println(messageServiceImpl.createIdentificationCode());

    }

    @Test
    public void testSaveIdentificationCode() throws Exception {
        IdentificationCode identificationCode = new IdentificationCode();
        identificationCode.setCode("456789");
        identificationCode.setMobile("13678876767");
        identificationCode.setState("normal");
        identificationCode.setType("login");
        identificationCode.setCreateTime(new Date());
        messageServiceImpl.saveIdentificationCode(identificationCode);
    }

    @Test
    public void testQueryIdentificationCode() {
        try {
            IdentificationCode identificationCode = new IdentificationCode();
            identificationCode.setMobile("13678876767");
            identificationCode.setType("login");
            System.out.println(messageServiceImpl.queryIdentificationCode(identificationCode));
        } catch (BusinessException ex) {
            assertEquals(Type.WRONG_PHONENO, ex.getErrorType());
        }
    }

    @Test
    public void testIsIdentificationCodeValid() throws Exception {
        try {
            System.out.println(messageServiceImpl.isIdentificationCodeValid("15665888893", "login","516995"));
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
            System.out.println(content);
        } catch (BusinessException ex) {
            assertEquals(Type.TEMPLATEID_NULL, ex.getErrorType());
        }
    }

    @Test
    public void testSaveMessage() throws Exception {
        MessageSend messageSend = new MessageSend();
        messageSend.setType("register");
        messageSend.setState("normal");
        messageSend.setMobile("13678879090");
        messageSend.setUserId("5431111");
        messageSend.setContent("您的验证码为876430,请在有效时间内使用");
        messageServiceImpl.saveMessage(messageSend);
    }

    @Test
    public void testQueryMessage() throws Exception {
        MessageSend messageSend = new MessageSend();
        messageSend.setMobile("13678879090");
        messageSend.setUserId("5431111");
        messageSend.setState("sent");
        //messageSend.setType("register");
        //messageSend.setId("1");
        List<MessageSend> messageSendList = messageServiceImpl.queryMessage(messageSend);
        if (messageSendList.size() != 0)
            for (MessageSend messageSend1 : messageSendList) {
                System.out.println(messageSend1.getContent());
            }

    }

    @Test
    public void testUpdateMessageSendtime() throws Exception {
        MessageSend messageSend = new MessageSend();
        messageSend.setMobile("13678879090");
        messageSend.setUserId("5431111");
        messageSend.setType("register");
        messageSend.setState("normal");
        messageSend.setSendTime(new Date());
        messageServiceImpl.updateMessage(messageSend);
    }

    @Test
    public void testSendMessage() throws Exception {
        List<MessageSend> messageSendList = new ArrayList<MessageSend>();
        MessageSend messageSend = new MessageSend();
        messageSend.setMobile("15665888893");
        messageSend.setContent("你好，你的验证码为77797");
        messageSendList.add(messageSend);
//        messageServiceImpl.sendMessage(messageSendList);
    }

    @Test
    public void testQueryUnsentMessage() throws Exception {
        List<MessageSend> messageSendList = messageServiceImpl.queryUnsentMessage();
        if (messageSendList.size() != 0) {
            for (MessageSend messageSend : messageSendList) {
                System.out.println(messageSend);
            }
        }
    }

    @Test
    public void testSendMessageCode() throws Exception {
        messageServiceImpl.sendMessageCode("15665888893","login","");
    }

    @Test
    public void testSendMessageService() throws Exception {
        Map paras = new HashMap();
        paras.put("name","校长");
        messageServiceImpl.sendMessageService("101","13678875232","register","newUser",paras);
    }*/
}