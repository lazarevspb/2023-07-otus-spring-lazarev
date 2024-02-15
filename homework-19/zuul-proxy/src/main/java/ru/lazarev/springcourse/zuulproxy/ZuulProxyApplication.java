package ru.lazarev.springcourse.zuulproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulProxyApplication {
	//http://localhost:8082/store/#!/books
	public static void main(String[] args) {
		SpringApplication.run(ZuulProxyApplication.class, args);
	}

}
