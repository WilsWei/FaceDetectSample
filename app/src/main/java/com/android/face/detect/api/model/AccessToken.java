/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.android.face.detect.api.model;

public class AccessToken {

    private String accessToken;

    private int expiresIn;

    private String json;

    public String getAccessToken() {
        return "24.606e78a16b0873eb9f972e7b82556d57.2592000.1506158449.282335-10037036";
//        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
