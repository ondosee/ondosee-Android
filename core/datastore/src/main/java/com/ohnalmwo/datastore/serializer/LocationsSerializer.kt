package com.ohnalmwo.datastore.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.ohnalmwo.datastore.Locations
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class LocationsSerializer @Inject constructor() : Serializer<Locations> {
    override val defaultValue: Locations = Locations.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Locations =
        try {
            Locations.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", e)
        }

    override suspend fun writeTo(t: Locations, output: OutputStream) {
        t.writeTo(output)
    }
}