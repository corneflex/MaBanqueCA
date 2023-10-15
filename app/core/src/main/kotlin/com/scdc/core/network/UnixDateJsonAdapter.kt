package com.scdc.core.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import java.util.Date

class UnixDateJsonAdapter : JsonAdapter<Date>() {

    @FromJson
    override fun fromJson(reader: JsonReader): Date? {

        val timestamp = reader.nextLong()
        return Date(timestamp * 1000) // Convert seconds to milliseconds
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Date?) {
        value?.let {
            writer.value(it.time / 1000) // Convert milliseconds back to seconds
        }
    }
}