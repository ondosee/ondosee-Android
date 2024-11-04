package com.ohnalmwo.network.mapper.todomain

import com.ohnalmwo.model.TimeZoneInfo
import com.ohnalmwo.model.Weather
import com.ohnalmwo.model.WeatherDetail
import com.ohnalmwo.network.dto.TimeZoneInfoResponse
import com.ohnalmwo.network.dto.WeatherDetailResponse
import com.ohnalmwo.network.dto.WeatherResponse

fun WeatherResponse.toDomain(): Weather = Weather(
    weathers = this.weathers.map { it.toDomain() }
)

fun WeatherDetailResponse.toDomain(): WeatherDetail = WeatherDetail(
    significant = this.significant,
    timeZone = this.timeZone.map { it.toDomain() }
)

fun TimeZoneInfoResponse.toDomain(): TimeZoneInfo = TimeZoneInfo(
    time = this.time,
    value = this.value
)