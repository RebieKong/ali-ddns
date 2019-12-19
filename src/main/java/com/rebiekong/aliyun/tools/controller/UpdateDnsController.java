package com.rebiekong.aliyun.tools.controller;

import com.aliyuncs.exceptions.ClientException;
import com.rebiekong.aliyun.tools.config.KeyConfig;
import com.rebiekong.aliyun.tools.service.DnsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UpdateDnsController {

    private Logger logger = LoggerFactory.getLogger(UpdateDnsController.class);

    @Autowired
    private DnsService dnsService;

    @Autowired
    private KeyConfig keyConfig;

    @GetMapping("/update/ddns")
    @ResponseBody
    public String update(@RequestParam("key") String key, HttpServletRequest request) {
        String ddns = keyConfig.findDnsByKey(key);
        if (ddns != null) {
            try {
                dnsService.updateDDns(keyConfig.getDomain(), ddns + ".ddns", request.getRemoteAddr());
                return "success";
            } catch (ClientException e) {
                logger.error(e.getErrMsg());
            }
        }
        return "fail";
    }
}
