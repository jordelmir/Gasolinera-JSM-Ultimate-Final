package com.gasolinerajsm.apigateway.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "cors")
data class CorsProperties(
    val allowedOrigins: List<String> = listOf("*"),
    val allowedMethods: List<String> = listOf("*"),
    val allowedHeaders: List<String> = listOf("*"),
    val exposedHeaders: List<String> = listOf(),
    val allowCredentials: Boolean = true,
    val maxAge: Long = 3600
)
