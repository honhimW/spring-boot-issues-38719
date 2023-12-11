package org.example.lb;

import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.discovery.simple.reactive.SimpleReactiveDiscoveryClient;
import org.springframework.cloud.client.discovery.simple.reactive.SimpleReactiveDiscoveryProperties;
import org.springframework.cloud.loadbalancer.core.CachingServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.DiscoveryClientServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.env.MockEnvironment;

import java.util.List;
import java.util.Map;

/**
 * @author hon_him
 * @since 2023-12-11
 */

public class ILoadBalancerAutoConfiguration {

    @Bean
    ServiceInstanceListSupplier cachingServiceInstanceListSupplier() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        SimpleReactiveDiscoveryProperties simpleDiscoveryProperties = new SimpleReactiveDiscoveryProperties();
        simpleDiscoveryProperties.setInstances(Map.of("beta", List.of(new DefaultServiceInstance(
            "1",
            "beta",
            "localhost",
            8081,
            false
        ))));
        SimpleReactiveDiscoveryClient simpleReactiveDiscoveryClient = new SimpleReactiveDiscoveryClient(simpleDiscoveryProperties);
        MockEnvironment _e = new MockEnvironment();
        _e.setProperty("loadbalancer.client.name", "beta");
        DiscoveryClientServiceInstanceListSupplier delegate = new DiscoveryClientServiceInstanceListSupplier(simpleReactiveDiscoveryClient, _e);
        return new CachingServiceInstanceListSupplier(delegate, cacheManager);
    }

}
