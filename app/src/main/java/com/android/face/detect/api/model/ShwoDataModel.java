/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.android.face.detect.api.model;

import android.graphics.Bitmap;

/**
 * Created by siang on 2017/9/8.
 */

public class ShwoDataModel {
    private Bitmap image;
    private String name;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
