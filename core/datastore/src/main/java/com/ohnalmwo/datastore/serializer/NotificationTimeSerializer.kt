package com.ohnalmwo.datastore.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.ohnalmwo.datastore.NotificationTime
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class NotificationTimeSerializer  @Inject constructor() : Serializer<NotificationTime> {
    override val defaultValue: NotificationTime = NotificationTime.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): NotificationTime =
        try {
            NotificationTime.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", e)
        }

    override suspend fun writeTo(t: NotificationTime, output: OutputStream) {
        t.writeTo(output)
    }
}