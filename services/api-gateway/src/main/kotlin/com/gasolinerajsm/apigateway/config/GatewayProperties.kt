package com.gasolinerajsm.apigateway.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "gateway")
data class GatewayProperties(
    val timeout: Int = 5000
)
