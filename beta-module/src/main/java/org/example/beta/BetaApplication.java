package org.example.beta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hon_him
 * @since 2023-12-09
 */

@RestController
@SpringBootApplication
public class BetaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BetaApplication.class, args);
    }

    @RequestMapping("/hello")
    public String fromBeta() {
        return "from beta";
    }

}
