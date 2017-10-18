package com.android.face.detect;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.Environment;
import android.util.SparseArray;

import com.android.face.detect.model.FaceInfo;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;

/**
 * Created by siang on 2017/10/12.
 */
class MyFaceDetector extends Detector<FaceInfo> {
    private Detector<Face> mDelegate;
    private SparseArray<FaceInfo> mFaceInfoSparseArray = new SparseArray<>();

    MyFaceDetector(Detector<Face> delegate) {
        mDelegate = delegate;
    }

    public SparseArray<FaceInfo> detect(Frame frame) {
        // *** add your custom frame processing code here

        SparseArray<Face> detectedFaces = mDelegate.detect(frame);

        for(int i=0;i<detectedFaces.size();i++) {          //can't use for-each loops for SparseArrays
            Face face = detectedFaces.valueAt(i);

            Frame.Metadata metadata = frame.getMetadata();
            ByteBuffer byteBuffer = frame.getGrayscaleImageData();
            int width = metadata.getWidth();
            int height = metadata.getHeight();
            int rotation = metadata.getRotation();
            byte[] bytes = byteBuffer.array();

            YuvImage yuvImage = new YuvImage(bytes, ImageFormat.NV21, width,height, null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            yuvImage.compressToJpeg(new Rect(0, 0, width, height), 100, baos); // Where 100 is the quality of the generated jpeg
            byte[] jpegArray = baos.toByteArray();
            Bitmap bitmap = BitmapFactory.decodeByteArray(jpegArray, 0, jpegArray.length);

            Matrix matrix = new Matrix();
            switch (rotation) {
                case Frame.ROTATION_0:
                    matrix.postRotate(0);
                    break;
                case Frame.ROTATION_90:
                    matrix.postRotate(90);
                    break;
                case Frame.ROTATION_180:
                    matrix.postRotate(90);
                    break;
                case Frame.ROTATION_270:
                    matrix.postRotate(270);
                    break;
            }

            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

            if (resizedBitmap != null && (int) face.getPosition().x >= 0 && (int) face.getPosition().y >= 0 && (face.getPosition().x + face.getWidth() <= resizedBitmap.getWidth() && face.getPosition().y + face.getHeight() <= resizedBitmap.getHeight())){
                Bitmap faceBitmap = Bitmap.createBitmap(resizedBitmap, (int) face.getPosition().x, (int) face.getPosition().y, (int) face.getWidth(), (int) face.getHeight());
//                saveImage(faceBitmap, String.valueOf(face.getId()));

                FaceInfo faceInfo = mFaceInfoSparseArray.get(i);

//                int key = detectedFaces.keyAt(i);
                if (mFaceInfoSparseArray.get(i) == null) {
                    faceInfo = new FaceInfo();
                    faceInfo.setFace(face);
                    faceInfo.setFaceImage(faceBitmap);
                    mFaceInfoSparseArray.put(i, faceInfo );
                } else {
                    faceInfo.setFace(face);
                    faceInfo.setFaceImage(faceBitmap);
                    mFaceInfoSparseArray.setValueAt(i, faceInfo );
                }

            }
        }
        return mFaceInfoSparseArray;

    }

    public boolean isOperational() {
        return mDelegate.isOperational();
    }

    public boolean setFocus(int id) {
        return mDelegate.setFocus(id);
    }

    private void saveImage(Bitmap bitmap, String id) {
        String FolderPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "FACE" + File.separator;
        File file = new File(FolderPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(
                    new File(FolderPath + id + "_" + new Date().getTime() + "_face.jpg"));

            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
            bitmap.recycle();
            bitmap = null;

            // 寫入檔案
            fileOutputStream.flush();
            fileOutputStream.close();
            fileOutputStream = null;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

