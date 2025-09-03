package com.gasolinerajsm.apigateway.config

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GatewayConfig {

    @Bean
    fun customRouteLocator(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route("auth-service") { r -> r.path("/auth/**")
                .uri("lb://auth-service") }
            .route("station-service") { r -> r.path("/stations/**")
                .uri("lb://station-service") }
            .route("coupon-service") { r -> r.path("/coupons/**")
                .uri("lb://coupon-service") }
            .route("ad-engine") { r -> r.path("/ads/**")
                .uri("lb://ad-engine") }
            .route("raffle-service") { r -> r.path("/raffles/**")
                .uri("lb://raffle-service") }
            .route("redemption-service") { r -> r.path("/redemptions/**")
                .uri("lb://redemption-service") }
            .build()
    }
}