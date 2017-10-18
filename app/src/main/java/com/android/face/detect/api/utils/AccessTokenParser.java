/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.android.face.detect.api.utils;


import com.android.face.detect.api.exception.FaceError;
import com.android.face.detect.api.model.AccessToken;

public class AccessTokenParser implements Parser<AccessToken> {
    @Override
    public AccessToken parse(String json) throws FaceError {
//        try {
            AccessToken accessToken = new AccessToken();
            accessToken.setJson(json);
//            JSONObject jsonObject = new JSONObject(json);
            LogUtil.i("ORC", "json token->" + json);

            accessToken.setAccessToken("24.606e78a16b0873eb9f972e7b82556d57.2592000.1506158449.282335-10037036");

//            if (jsonObject != null) {

//                accessToken.setAccessToken(jsonObject.optString("access_token"));
//                accessToken.setExpiresIn(jsonObject.optInt("expires_in"));
                return accessToken;
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//            FaceError error = new FaceError(FaceError.ErrorCode.JSON_PARSE_ERROR, "Json parse error", e);
//            throw error;
//        }
//        return null;
    }
}
