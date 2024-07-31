package com.example.googlemaps

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.googlemaps.databinding.ActivityMainBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val customMarker: BitmapDescriptor by lazy {
        val px = resources.getDimensionPixelSize(R.dimen.location_marker_size)
        val bitmap = Bitmap.createBitmap(px, px, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val shape = ContextCompat.getDrawable(this, R.drawable.custom_marker)
        shape?.setBounds(0,0,bitmap.width, bitmap.height)
        shape?.draw(canvas)
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment

        mapFragment?.getMapAsync {googleMap ->
            addLocation(googleMap)
            centerCamera(googleMap)
            googleMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(this))
            googleMap.setOnInfoWindowClickListener { marker ->
                val location = marker.tag as Location
                val message = "Le diste tap a ${location.name}"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addLocation(googleMap: GoogleMap){
        bestMexicoLocations.forEach{ location ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(location.name)
                    .position(location.latlng)
                    .icon(customMarker)
            )
            marker?.tag =location
        }

    }

    private fun centerCamera(googleMap: GoogleMap){
        googleMap.setOnMapLoadedCallback {
            val bounds = LatLngBounds.builder()
            bestMexicoLocations.forEach{location ->
                bounds.include(location.latlng)
            }
            //movemos la camara a ese limite
            googleMap.moveCamera(CameraUpdateFactory
                .newLatLngBounds(bounds.build(), 100))
        }
    }

}