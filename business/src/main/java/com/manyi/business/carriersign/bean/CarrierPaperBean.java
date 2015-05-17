package com.manyi.business.carriersign.bean;

import java.sql.Date;

/**
 * Created by zhangyufeng on 2015/4/8 0008.
 */
public class CarrierPaperBean {
    private long id;

    private long carrierQualificationID;

    private String paperType;

    private String fileName;

    private String fileUrl;

    private String fileState;

    private String fileOrder;

    private Date uploadTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCarrierQualificationID() {
        return carrierQualificationID;
    }

    public void setCarrierQualificationID(long carrierQualificationID) {
        this.carrierQualificationID = carrierQualificationID;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
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

    public String getFileState() {
        return fileState;
    }

    public void setFileState(String fileState) {
        this.fileState = fileState;
    }

    public String getFileOrder() {
        return fileOrder;
    }

    public void setFileOrder(String fileOrder) {
        this.fileOrder = fileOrder;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
