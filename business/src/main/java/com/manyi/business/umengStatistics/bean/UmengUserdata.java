package com.manyi.business.umengStatistics.bean;

import java.util.Date;

/**
 * @Description: 用户基本数据ean
 * @author zhaoyuxin
 * @version: 1.0.0 2015-05-19
 * @reviewer:
 */
public class UmengUserdata {

    private int installations;

    private int new_users;

    private int active_users;

    private int launches;

    private Date date;

    public int getInstallations() {
        return installations;
    }

    public void setInstallations(int installations) {
        this.installations = installations;
    }

    public int getNew_users() {
        return new_users;
    }

    public void setNew_users(int new_users) {
        this.new_users = new_users;
    }

    public int getActive_users() {
        return active_users;
    }

    public void setActive_users(int active_users) {
        this.active_users = active_users;
    }

    public int getLaunches() {
        return launches;
    }

    public void setLaunches(int launches) {
        this.launches = launches;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
