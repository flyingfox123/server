package com.manyi.bean;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 操作
 * Created by Magic on 2015/1/15.
 */
public class JsonResult {

    // 操作成功
    public static final String SUCCESS = "success";

    // 操作失败
    public static final String FAILURE = "failure";

    private String message = "成功";
    private String result = "success";

    public String errorMsg;

    public JsonResult(String result, String message) {
        this.result = result;
        this.message = message;
    }

    private Map<String, String> map = new HashMap<String, String>();

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
        String errorMsg = "";
        Iterator it = map.keySet().iterator();
        while(it.hasNext()) {
            String key = (String) it.next();
            errorMsg += map.get(key) + "，";
        }
        return errorMsg;
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
