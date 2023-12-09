package org.example.alpha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AlphaApplication {

    private static final Logger log = LoggerFactory.getLogger(AlphaApplication.class);

    public static void main(String[] args) {
        String property = System.getProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads");
        log.info("enable reactor scheduler bounded on virtual threads: {}", property);
        try {
            Class<?> aClass = Class.forName("reactor.core.publisher.MonoIgnoreElements$IgnoreElementsSubscriber");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        SpringApplication.run(AlphaApplication.class, args);
    }

    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("beta", r -> r.path("/beta/**").uri("lb://beta"))
            .build();
    }

    @RequestMapping("/hello")
    public String any() {
        return "hello world";
    }

}
