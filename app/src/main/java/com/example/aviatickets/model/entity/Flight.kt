package com.example.aviatickets.model.entity

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder

/**
 * think about json deserialization considering its snake_case format
 */
data class Flight(
    val departureLocation: Location,
    val arrivalLocation: Location,
    val departureTimeInfo: String,
    val arrivalTimeInfo: String,
    val flightNumber: String,
    val airline: Airline,
    val duration: Int
)