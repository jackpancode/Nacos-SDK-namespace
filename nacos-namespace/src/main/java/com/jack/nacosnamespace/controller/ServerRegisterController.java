package com.jack.nacosnamespace.controller;


import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/discovery")
public class ServerRegisterController {


    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String customNamespaceId;

    @Value("${spring.application.name}")
    private String serviceName;

    private String serverIp = "127.0.0.1";
    private int serverPort = 8848;
    private String serverAddr = serverIp + ":" + serverPort;


    @RequestMapping(value = "/set", method = GET)
    @ResponseBody
    public String set() {

        MultiValueMap<String, Object> hashMap = new LinkedMultiValueMap<>();
        hashMap.add("customNamespaceId", customNamespaceId);
        hashMap.add("namespaceName", "ceshi");
        hashMap.add("namespaceDesc", customNamespaceId);

        String url = "http://localhost:8848/nacos/v1/console/namespaces";

        String result = restTemplate.postForObject(url, hashMap, String.class);
        System.out.println(result);

        Properties properties = new Properties();
        properties.setProperty("serverAddr", "127.0.0.1:8848");
        properties.setProperty("namespace", customNamespaceId);

//        Instance instance = new Instance();
//        instance.setIp(serverIp);
//        instance.setServiceName(serviceName);
//        instance.setPort(serverPort);


        try {
            NamingService namingService = NamingFactory.createNamingService(properties);
            namingService.registerInstance(serviceName,serverIp,serverPort); // 注册中心的地址
            System.out.println("注册信息为:" + namingService.getAllInstances(serviceName));
            return "OK";
        } catch (NacosException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }


}
