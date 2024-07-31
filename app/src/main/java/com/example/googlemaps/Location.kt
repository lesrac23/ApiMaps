package com.example.googlemaps

import com.google.android.gms.maps.model.LatLng

data class Location(
    val name: String,
    val latlng: LatLng,
    val address: String


)

private val beduLocation = Location(
    "Bedu",
    LatLng(19.42717,-99.1609062),
    "Londres 61, Juárez, Cuauhtémoc, 06300 Ciudad de México, CDMX"
)

private val guardiaNacionalLocation = Location(
    "Guardia Nacional",
    LatLng(19.3956568,-99.2596921),
    "Sedesol, Álvaro Obregón, 01219 Ciudad de México, CDMX"
)

private val laLagunillaLocation = Location(
    "La Lagunilla",
    LatLng(19.443549,-99.1401553),
    "La Lagunilla, Cuauhtémoc, 06000 Ciudad de México, CDMX"
)

val bestMexicoLocations = listOf(
    beduLocation,
    guardiaNacionalLocation,
    laLagunillaLocation
)
