package com.example.googlemaps

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.googlemaps.databinding.LocationContentBinding
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.model.Marker

class MarkerInfoWindowAdapter(private val context: Context): InfoWindowAdapter {
    override fun getInfoContents(marker: Marker): View? {
        val location = marker.tag as Location
        val binding = LocationContentBinding.inflate(LayoutInflater.from(context))
        binding.apply {
            textViewTitle.text = location.name
            textViewAddress.text=location.address
        }
        return binding.root
    }

    override fun getInfoWindow(p0: Marker) = null

}