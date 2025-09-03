package com.gasolinerajsm.stationservice.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class LocationDto(
    @field:NotNull(message = "{location.latitude.notNull}")
    @field:Min(value = -90, message = "Latitude must be between -90 and 90")
    @field:Max(value = 90, message = "Latitude must be between -90 and 90")
    val latitude: Double,

    @field:NotNull(message = "{location.longitude.notNull}")
    @field:Min(value = -180, message = "{location.longitude.minMax}")
    @field:Max(value = 180, message = "{location.longitude.minMax}")
    val longitude: Double
)
