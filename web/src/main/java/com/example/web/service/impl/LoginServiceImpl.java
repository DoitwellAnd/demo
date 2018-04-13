package com.example.web.service.impl;

import com.example.web.config.SystemConfig;
import com.example.web.dto.UserInfoDto;
import com.example.web.service.ILoginService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginServiceImpl implements ILoginService{

    @Autowired
    SystemConfig systemConfig;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Override
    @HystrixCommand(
            fallbackMethod = "fallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
            })
    public boolean login(String username,String password) {
        String param = String.format("?username=%s&password=%s&flag=%b",username,password,true);
        ResponseEntity<UserInfoDto> responseEntity = restTemplate.getForEntity(systemConfig.getLoginUrl()+param,UserInfoDto.class);
        System.out.println("param="+param);
        UserInfoDto userInfoDto = responseEntity.getBody();
        if(null != userInfoDto && userInfoDto.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public boolean fallback(String name,String password) {
        return false;
    }
}
