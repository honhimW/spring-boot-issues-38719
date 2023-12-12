package org.example.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author hon_him
 * @since 2023-12-12
 */

@LoadBalancerClients(defaultConfiguration = LoadBalancerConfig.class)
public class LoadBalancerConfig {

    private Logger log = LoggerFactory.getLogger("mock.load.balancer");

    /**
     * Error will occur
     */
    @Bean
    public ReactorLoadBalancer<ServiceInstance> loadbalancer(
        ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider,
        Environment environment) {
        String clientName = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME, "");
        return new RandomLoadBalancer(serviceInstanceListSupplierProvider, clientName) {
            @Override
            public Mono<Response<ServiceInstance>> choose(Request request) {
                return super.choose(request)
                    .subscribeOn(Schedulers.boundedElastic());
            }
        };
    }

    /**
     * Error will not happen
     */
//    @Bean
//    public ReactorLoadBalancer<ServiceInstance> loadbalancer(
//        ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider,
//        Environment environment) {
//        String clientName = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME, "");
//        log.info("mock load balancer: {}", clientName);
//        return new RandomLoadBalancer(serviceInstanceListSupplierProvider, clientName) {
//            @Override
//            public Mono<Response<ServiceInstance>> choose(Request request) {
//                DefaultServiceInstance serviceInstance = new DefaultServiceInstance(
//                    "1",
//                    "beta",
//                    "localhost",
//                    8081,
//                    false
//                );
//                return Mono.fromSupplier(() -> serviceInstance)
//                    .doOnNext(defaultServiceInstance -> log.info("choose load balancer: {}:, instance: {}", clientName, defaultServiceInstance.getInstanceId()))
//                    .map(DefaultResponse::new)
//                    .map(defaultResponse -> (Response<ServiceInstance>) defaultResponse)
//                    .subscribeOn(Schedulers.boundedElastic());
//            }
//        };
//    }

}
