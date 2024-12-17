package com.qylapi.qylclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

import static com.qylapi.qylclientsdk.utils.SignUtils.getSign;


/**
 * 调用第三方接口的客户端
 */
public class ApiClient {

    private static final String GATEWAY_HOST = "http://8.134.208.162:8090";

    private String accesskey ;
    private String secretkey ;

    public ApiClient(String accesskey, String secretkey) {
        this.accesskey = accesskey;
        this.secretkey = secretkey;
    }



    /**
     * 设置请求头
     */
    private Map<String,String> getHeaders(String body){
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("accesskey",accesskey);
        headerMap.put("secretkey",secretkey);
        headerMap.put("nonce", RandomUtil.randomNumbers(4));//随机数
        headerMap.put("body",body);//请求体
        headerMap.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));//时间戳
        headerMap.put("sign",getSign(body,secretkey));//签名
        return headerMap;
    }



    public String getUsernameByPost(String user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST+"/api/name/user/")
                .body(json)
                .addHeaders(getHeaders(json))
                .execute();
        System.out.println(httpResponse.getStatus());
        String result=httpResponse.body();
        return result;
    }
}
