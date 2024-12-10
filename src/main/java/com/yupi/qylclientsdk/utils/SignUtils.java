package com.yupi.qylinterface.utils;

import cn.hutool.crypto.digest.DigestUtil;

import java.util.Map;

/**
 * 签名工具
 */

public class SignUtils {
    /**
     *签名加密算法
     */
    public static String getSign(String body, String secretkey){
        //使用hutool的签名算法,MD5算法
        String testStr = body.toString()+"."+secretkey;
        String md5Hex1 = DigestUtil.md5Hex(testStr);
        return md5Hex1;
    }
}
