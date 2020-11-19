package com.jack.nacosnamespace;

import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NacosNamespaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosNamespaceApplication.class, args);

	}

}
