package com.manyi.business.pay.check;

import com.manyi.business.pay.check.support.entity.Check;

/**
 * @author ZhangYuFeng on 2015/6/10 0010,9:54.
 * @Description: 下载对账文件并解析对账文件
 * @reviewer:
 */
public interface CheckService {

    /**
     * 得到对账文件的url
     */
    String getCheckFileUrl();

    /**
     * 下载对账文件
     * @param fileName 对账文件的文件名
     */
    Check getCheckFile(String fileName);

    /**
     * 解析对账文件
     * @param fileName
     */
    void parseCheckFile(String fileName);

    /**
     * 将对账文件保存到数据库中
     * @param strings
     */
    void addCheckFileToDb(String[] strings);

    /**
     * 下载对账文件并且插入数据库中
     */
    void dealCheckFile();


}
