package com.manyi.bean;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @author wangpengfei
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class JsonResult {

    // 操作成功
    public static final String SUCCESS = "success";

    // 操作失败
    public static final String FAILURE = "failure";

    private String message = "成功";
    private String result = "success";


    public JsonResult(String result, String message) {
        this.result = result;
        this.message = message;
    }

    private ConcurrentHashMap <String, String> map = new ConcurrentHashMap<String, String>();

    public JsonResult(Errors errors) {
        this(FAILURE, "错误");
        for (FieldError e : errors.getFieldErrors()) {
            map.put(e.getField(), e.getDefaultMessage());
        }
    }

    public JsonResult() {
        this(FAILURE, "");
    }

    public String getErrorMsg() {
        StringBuilder errorMsg = new StringBuilder();
        Iterator iterator = map.keySet().iterator();
        while(iterator.hasNext()) {
            String key = (String) iterator.next();
            errorMsg.append(map.get(key) + "，");
        }
        return errorMsg.toString();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
