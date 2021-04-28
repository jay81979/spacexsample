package com.jay8digitalmedia.spacex.vo

import com.google.gson.annotations.SerializedName
import java.util.*

data class Launch(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("flight_number")
    val flightNumber: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("date_utc")
    val date: Date,
    @field:SerializedName("success")
    val success: Boolean,
    @field:SerializedName("rocket")
    val rocket: String
)