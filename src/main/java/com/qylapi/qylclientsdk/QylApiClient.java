package com.qylapi.qylclientsdk;


import com.qylapi.qylclientsdk.client.ApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("qyl.client")
@Data
@ComponentScan
public class QylApiClient {

    private String accesskey ;
    private String secretkey ;

    @Bean
    public ApiClient apiClient(){
        return new ApiClient(accesskey, secretkey);
    }
}
