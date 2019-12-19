package com.rebiekong.aliyun.tools;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliBeanConfigure {

    @Value("${com.aliyun.rebie.ak}")
    private String ak;
    @Value("${com.aliyun.rebie.aks}")
    private String aks;

    @Bean
    public IAcsClient client() {
        //必填固定值，必须为“cn-hangzhou”
        String regionId = "cn-hangzhou";
        String accessKeyId = ak;
        String accessKeySecret = aks;
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        return new DefaultAcsClient(profile);
    }
}
