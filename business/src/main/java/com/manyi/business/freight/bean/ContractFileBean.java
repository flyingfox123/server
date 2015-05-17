package com.manyi.business.freight.bean;

import java.sql.Date;

/**
 * Created by zhangyufeng on 2015/4/13 0013.
 */
public class ContractFileBean {
    private long id;
    private long freightGuaranteeID;
    private String fileName;
    private String fileUrl;
    private Date uploadTime;
    private String fileState;
    private int order;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFreightGuaranteeID() {
        return freightGuaranteeID;
    }

    public void setFreightGuaranteeID(long freightGuaranteeID) {
        this.freightGuaranteeID = freightGuaranteeID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getFileState() {
        return fileState;
    }

    public void setFileState(String fileState) {
        this.fileState = fileState;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
