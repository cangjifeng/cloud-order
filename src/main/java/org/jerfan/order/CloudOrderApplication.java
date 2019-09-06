package org.jerfan.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class CloudOrderApplication {

    public static void main(String[] args) {
        // http://localhost:8006/swagger-ui.html#/
        SpringApplication.run(CloudOrderApplication.class, args);
    }

}
