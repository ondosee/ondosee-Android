package com.ohnalmwo.network.mapper.todomain

import com.ohnalmwo.model.Location
import com.ohnalmwo.model.LocationInfo
import com.ohnalmwo.model.PageInfo
import com.ohnalmwo.network.dto.LocationResponse
import com.ohnalmwo.network.dto.LocationInfoResponse
import com.ohnalmwo.network.dto.PageInfoResponse

fun LocationResponse.toDomain(): Location = Location(
    page = this.page.toDomain(),
    locations = this.locations.map { it.toDomain() }
)

fun PageInfoResponse.toDomain(): PageInfo = PageInfo(
    total = this.total,
    current = this.current
)

fun LocationInfoResponse.toDomain(): LocationInfo = LocationInfo(
    title = this.title,
    x = this.x,
    y = this.y
)