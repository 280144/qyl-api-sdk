package com.yupi.qylinterface.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yupi.qylinterface.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

import static com.yupi.qylinterface.utils.SignUtils.getSign;

/**
 * 调用第三方接口的客户端
 */
public class ApiClient {


    private String accesskey ;
    private String secretkey ;

    public ApiClient(String accesskey, String secretkey) {
        this.accesskey = accesskey;
        this.secretkey = secretkey;
    }

    public String getNameByGet(String name) {

        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result=HttpUtil.get("http://localhost:8123/api/name/", paramMap);
        System.out.println(result);
        return result;
    }


    public String getNameByPost(@RequestParam String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result=HttpUtil.post("http://localhost:8123/api/name/", paramMap);
        System.out.println(result);
        return result;
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



    public String getUsernameByPost(@RequestBody User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8123/api/name/user/")
                .body(json)
                .addHeaders(getHeaders(json))
                .execute();
        System.out.println(httpResponse.getStatus());
        String result=httpResponse.body();
        return result;
    }
}
