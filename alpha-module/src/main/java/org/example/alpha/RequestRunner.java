package org.example.alpha;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

/**
 * @author hon_him
 * @since 2023-12-12
 */

@Component
public class RequestRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        WebClient build = WebClient.builder().build();
        String s = build.get()
            .uri("http://localhost:8082/beta/hello")
            .retrieve()
            .bodyToFlux(String.class)
            .blockFirst(Duration.ofSeconds(2));
        Assert.state("from beta".equals(s), "should be the same");
    }
}
