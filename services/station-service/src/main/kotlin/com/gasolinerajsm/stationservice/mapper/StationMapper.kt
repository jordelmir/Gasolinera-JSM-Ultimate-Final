package com.gasolinerajsm.stationservice.mapper

import com.gasolinerajsm.stationservice.dto.CreateStationDto
import com.gasolinerajsm.stationservice.dto.LocationDto
import com.gasolinerajsm.stationservice.dto.StationDto
import com.gasolinerajsm.stationservice.dto.UpdateStationDto
import com.gasolinerajsm.stationservice.model.Station
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Point
import org.springframework.stereotype.Component

@Component
class StationMapper {

    private val geometryFactory = GeometryFactory()

    fun toDto(station: Station): StationDto {
        return StationDto(
            id = station.id,
            name = station.name,
            location = LocationDto(
                latitude = station.location.y,
                longitude = station.location.x
            ),
            status = station.status
        )
    }

    fun toEntity(createStationDto: CreateStationDto): Station {
        val point = geometryFactory.createPoint(Coordinate(createStationDto.location.longitude, createStationDto.location.latitude))
        return Station(
            id = java.util.UUID.randomUUID().toString(), // Generate new ID
            name = createStationDto.name,
            location = point,
            status = createStationDto.status
        )
    }

    fun updateEntityFromDto(station: Station, updateStationDto: UpdateStationDto) {
        updateStationDto.name?.let { station.name = it }
        updateStationDto.status?.let { station.status = it }
        updateStationDto.location?.let {
            station.location = geometryFactory.createPoint(Coordinate(it.longitude, it.latitude))
        }
    }
}
