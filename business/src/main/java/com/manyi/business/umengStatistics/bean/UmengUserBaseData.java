package com.manyi.business.umengStatistics.bean;

/**
 * @Description: 用户基本数据ean
 * @author zhaoyuxin
 * @version: 1.0.0 2015-05-20
 * @reviewer:
 */
public class UmengUserBaseData {

    private int yesterday_new_users;

    private int today_new_users;

    private int yesterday_active_users;

    private int today_active_users;

    private int yesterday_launches;

    private int today_launches;

    private int installations;

    public int getYesterday_new_users() {
        return yesterday_new_users;
    }

    public void setYesterday_new_users(int yesterday_new_users) {
        this.yesterday_new_users = yesterday_new_users;
    }

    public int getToday_new_users() {
        return today_new_users;
    }

    public void setToday_new_users(int today_new_users) {
        this.today_new_users = today_new_users;
    }

    public int getYesterday_active_users() {
        return yesterday_active_users;
    }

    public void setYesterday_active_users(int yesterday_active_users) {
        this.yesterday_active_users = yesterday_active_users;
    }

    public int getToday_active_users() {
        return today_active_users;
    }

    public void setToday_active_users(int today_active_users) {
        this.today_active_users = today_active_users;
    }

    public int getYesterday_launches() {
        return yesterday_launches;
    }

    public void setYesterday_launches(int yesterday_launches) {
        this.yesterday_launches = yesterday_launches;
    }

    public int getToday_launches() {
        return today_launches;
    }

    public void setToday_launches(int today_launches) {
        this.today_launches = today_launches;
    }

    public int getInstallations() {
        return installations;
    }

    public void setInstallations(int installations) {
        this.installations = installations;
    }
}
