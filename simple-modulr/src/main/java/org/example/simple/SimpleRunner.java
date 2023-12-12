package org.example.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author hon_him
 * @since 2023-12-12
 */

@Component
public class SimpleRunner implements ApplicationRunner {

    @Autowired
    private WebClient.Builder webClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String s = webClient.build().get().uri("http://beta/beta/hello")
            .retrieve()
            .bodyToFlux(String.class)
            .subscribeOn(Schedulers.boundedElastic())
            .blockFirst();
    }
}
