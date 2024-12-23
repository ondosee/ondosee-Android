package com.ohnalmwo.datastore.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.ohnalmwo.datastore.Tutorial
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class MainSerializer @Inject constructor() : Serializer<Tutorial> {
    override val defaultValue: Tutorial = Tutorial.newBuilder()
        .setOpenDialog(true)
        .build()

    override suspend fun readFrom(input: InputStream): Tutorial =
        try {
            Tutorial.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", e)
        }

    override suspend fun writeTo(t: Tutorial, output: OutputStream) {
        t.writeTo(output)
    }
}