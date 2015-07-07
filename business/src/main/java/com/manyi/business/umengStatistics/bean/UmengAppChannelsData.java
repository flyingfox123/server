package com.manyi.business.umengStatistics.bean;

import java.util.Date;

/**
 * @Description: 渠道列表bean
 * @author zhaoyuxin
 * @version: 1.0.0 2015-05-20
 * @reviewer:
 */
public class UmengAppChannelsData {

    private int launch;

    private int install;

    private int active_user;

    private int total_install;

    private String duration;

    private double total_install_rate;

    private String channel;

    private String id;

    private double install_rate;

    private String date;

    public int getLaunch() {
        return launch;
    }

    public void setLaunch(int launch) {
        this.launch = launch;
    }

    public int getInstall() {
        return install;
    }

    public void setInstall(int install) {
        this.install = install;
    }

    public int getActive_user() {
        return active_user;
    }

    public void setActive_user(int active_user) {
        this.active_user = active_user;
    }

    public int getTotal_install() {
        return total_install;
    }

    public void setTotal_install(int total_install) {
        this.total_install = total_install;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getTotal_install_rate() {
        return total_install_rate;
    }

    public void setTotal_install_rate(double total_install_rate) {
        this.total_install_rate = total_install_rate;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getInstall_rate() {
        return install_rate;
    }

    public void setInstall_rate(double install_rate) {
        this.install_rate = install_rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
