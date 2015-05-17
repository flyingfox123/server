package com.manyi.common.fileupload.bean;

import org.apache.commons.fileupload.FileItem;

import java.util.List;

/**
 * Created by zhangyufeng on 2015/4/13 0013.
 */
public class FileBean {
    private String paperType;
    private String fileName;
    private String fileUrl;
    private String fileState="0";
    private int fileOrder;
    private FileItem item;

    public FileItem getItem() {
        return item;
    }

    public void setItem(FileItem item) {
        this.item = item;
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

    public int getFileOrder() {
        return fileOrder;
    }

    public void setFileOrder(int fileOrder) {
        this.fileOrder = fileOrder;
    }


}
