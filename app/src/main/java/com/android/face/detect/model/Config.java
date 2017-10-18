package com.android.face.detect.model;

/**
 * Created by siang on 2017/10/17.
 */


public class Config {

    public static String appKey = "xnrDf45IXKZC5LfMYEVGzHXM";
    public static String appSecret = "o94fMEkDLwZY9T4tpBTngp4agvGwhy8L";
    public static String appName =  "thinkpower-face-android";
    public static String licenseFileName = "idl-license.face-android";


    /**
     groupId，标识一组用户（由数字、字母、下划线组成），长度限制128B。

     每个开发者账号只能创建一个人脸库；groupID用于标识人脸库
     每个人脸库下，用户组（group）数量没有限制；
     每个用户组（group）下，可添加最多300000张人脸，如每个uid注册一张人脸，则最多300000个用户uid；


     如闸机场景，可以给一个公司生成一个groupID;用于区分不同公司间的人脸库。
     详情见 http://ai.baidu.com/docs#/Face-API/top

     人脸识别 接口 https://aip.baidubce.com/rest/2.0/face/v2/identify
     人脸注册 接口 https://aip.baidubce.com/rest/2.0/face/v2/faceset/user/add
     */

    public static String groupID = "test1";

}

