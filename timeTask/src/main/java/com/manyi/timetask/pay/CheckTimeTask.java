package com.manyi.timetask.pay;

import com.manyi.business.pay.check.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author ZhangYuFeng on 2015/6/10 0010,8:42.
 * @Description: 对账任务定时
 * @reviewer:
 */
public class CheckTimeTask {

    @Autowired
    @Qualifier("dealCheckFileSeviceTestImpl")
    private CheckService checkService;

    public void checkStart(){
        checkService.dealCheckFile();
    }

    public void task1(){
        System.out.println("task1....");
    }
}
