package org.example.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author hon_him
 * @since 2023-12-12
 */

@Import(LoadBalancerConfig.class)
@SpringBootApplication
public class SimpleApplication {

    public static void main(String[] args) throws Exception {
        Class.forName("reactor.core.Exceptions");
        SpringApplication.run(SimpleApplication.class, args);
    }

    @LoadBalanced
    @Bean
    WebClient.Builder webBuilder() {
        return WebClient.builder();
    }

}
