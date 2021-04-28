package com.jay8digitalmedia.spacex.vo

import com.google.gson.annotations.SerializedName

data class Rocket(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("wikipedia")
    val wikipedia: String
)