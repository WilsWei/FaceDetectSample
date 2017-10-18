/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.android.face.detect.api.utils;


import com.android.face.detect.api.exception.FaceError;

public interface OnResultListener<T> {
    void onResult(T result);

    void onError(FaceError error);
}
