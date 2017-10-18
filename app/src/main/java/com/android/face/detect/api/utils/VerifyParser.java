/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.android.face.detect.api.utils;

import android.util.Log;


import com.android.face.detect.api.exception.FaceError;
import com.android.face.detect.api.model.FaceModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VerifyParser implements Parser<FaceModel> {
    @Override
    public FaceModel parse(String json) throws FaceError {
        Log.e("xx","parse:" + json);
        FaceModel faceModel = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray resultArray = jsonObject.optJSONArray("result");
            if (resultArray != null) {
                faceModel = new FaceModel();
                JSONObject faceObject = resultArray.getJSONObject(0);
                faceModel.setUid(faceObject.getString("uid"));
                JSONArray scroeArray = faceObject.optJSONArray("scores");
                if (scroeArray != null) {
                    faceModel.setScore(scroeArray.getDouble(0));
                }
                faceModel.setGroupID(faceObject.getString("group_id"));
                faceModel.setUserInfo(faceObject.getString("user_info"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return faceModel;
    }
}
