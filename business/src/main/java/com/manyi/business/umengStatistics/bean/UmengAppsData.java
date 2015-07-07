package com.manyi.business.umengStatistics.bean;

/**
 * @Description: APP列表bean
 * @author zhaoyuxin
 * @version: 1.0.0 2015-05-22
 * @reviewer:
 */
public class UmengAppsData {

    private String name;

    private String category;

    private String created_at;

    private String updated_at;

    private String platform;

    private String appkey;

    private int popular;

    private String use_game_sdk;

    public String getUse_game_sdk() {
        return use_game_sdk;
    }

    public void setUse_game_sdk(String use_game_sdk) {
        this.use_game_sdk = use_game_sdk;
    }

    public int getPopular() {
        return popular;
    }

    public void setPopular(int popular) {
        this.popular = popular;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

}
