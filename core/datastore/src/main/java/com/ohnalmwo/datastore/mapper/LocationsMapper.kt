package com.ohnalmwo.datastore.mapper

import com.ohnalmwo.datastore.Location
import com.ohnalmwo.model.LocationInfo

fun Location.toDomain(): LocationInfo = LocationInfo(
    title = this.title,
    x = this.x,
    y = this.y
)

fun LocationInfo.toData(): Location = Location.newBuilder()
    .setTitle(this.title)
    .setX(this.x)
    .setY(this.y)
    .build()