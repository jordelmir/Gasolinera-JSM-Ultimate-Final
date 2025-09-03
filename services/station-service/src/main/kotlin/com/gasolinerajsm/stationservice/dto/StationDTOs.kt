package com.gasolinerajsm.stationservice.dto

import com.gasolinerajsm.stationservice.model.StationStatus
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class StationDto(
    val id: String,
    val name: String,
    val location: LocationDto,
    val status: StationStatus
)

data class CreateStationDto(
    @field:NotBlank(message = "{station.name.notBlank}")
    @field:Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    val name: String,

    @field:NotNull(message = "{station.location.notNull}")
    @field:Valid
    val location: LocationDto,

    val status: StationStatus = StationStatus.ACTIVA
)

data class UpdateStationDto(
    @field:Size(min = 3, max = 100, message = "{station.name.size}")
    val name: String?,

    @field:Valid
    val location: LocationDto?,

    val status: StationStatus?
)
