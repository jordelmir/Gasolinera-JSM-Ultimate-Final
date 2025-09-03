package com.gasolinerajsm.stationservice.service

import com.gasolinerajsm.stationservice.dto.CreateStationDto
import com.gasolinerajsm.stationservice.dto.StationDto
import com.gasolinerajsm.stationservice.dto.UpdateStationDto
import com.gasolinerajsm.stationservice.exception.StationNotFoundException
import com.gasolinerajsm.stationservice.mapper.StationMapper
import com.gasolinerajsm.stationservice.repository.StationRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StationService(
    private val stationRepository: StationRepository,
    private val stationMapper: StationMapper
) {

    fun findById(id: String): StationDto {
        return stationRepository.findById(id)
            .map(stationMapper::toDto)
            .orElseThrow { StationNotFoundException(id) }
    }

    fun findAll(): List<StationDto> {
        return stationRepository.findAll().map(stationMapper::toDto)
    }

    fun findNearbyStations(longitude: Double, latitude: Double, distanceMeters: Int): List<StationDto> {
        return stationRepository.findStationsNearby(longitude, latitude, distanceMeters).map(stationMapper::toDto)
    }

    @Transactional
    fun create(stationDto: CreateStationDto): StationDto {
        val newStation = stationMapper.toEntity(stationDto)
        val savedStation = stationRepository.save(newStation)
        return stationMapper.toDto(savedStation)
    }

    @Transactional
    fun update(id: String, stationDto: UpdateStationDto): StationDto {
        val existingStation = stationRepository.findById(id)
            .orElseThrow { StationNotFoundException(id) }

        stationMapper.updateEntityFromDto(existingStation, stationDto)

        val updatedStation = stationRepository.save(existingStation)
        return stationMapper.toDto(updatedStation)
    }

    @Transactional
    fun deleteById(id: String) {
        if (!stationRepository.existsById(id)) {
            throw StationNotFoundException(id)
        }
        try {
            stationRepository.deleteById(id)
        } catch (ex: EmptyResultDataAccessException) {
            // This catch block might be redundant now due to the existsById check, but kept for safety.
            throw StationNotFoundException(id)
        }
    }
}
