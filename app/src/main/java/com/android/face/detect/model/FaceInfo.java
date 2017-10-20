package com.android.face.detect.model;

import android.graphics.Bitmap;

import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;

/**
 * Created by siang on 2017/10/17.
 */

public class FaceInfo {

    private Face face;
    private Bitmap faceImage;
    private String name;
    private boolean callAPILock = false;

    public FaceInfo() {
        callAPILock = false;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public Bitmap getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(Bitmap faceImage) {
        this.faceImage = faceImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCallAPILock() {
        return callAPILock;
    }

    public void setCallAPILock(boolean callAPILock) {
        this.callAPILock = callAPILock;
    }
}
