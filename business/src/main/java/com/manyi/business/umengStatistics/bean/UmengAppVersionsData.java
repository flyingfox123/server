package com.manyi.business.umengStatistics.bean;

import java.util.Date;

/**
 * Created by zhaoyuxin on 2015/5/19 0019.
 */
/**
 * @Description: APP列表bean
 * @author zhaoyuxin
 * @version: 1.0.0 2015-05-22
 * @reviewer:
 */
public class UmengAppVersionsData {

    public int install;

    public int active_user;

    public String version;

    public int total_install;

    public double total_install_rate;

    public int launch;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getTotal_install() {
        return total_install;
    }

    public void setTotal_install(int total_install) {
        this.total_install = total_install;
    }

    public double getTotal_install_rate() {
        return total_install_rate;
    }

    public void setTotal_install_rate(float total_install_rate) {
        this.total_install_rate = total_install_rate;
    }
}
