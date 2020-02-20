package com.example.maps

import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private lateinit var nMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private var lastMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        nMap = googleMap
        // Habilitamos los botones del zoom.
        nMap.uiSettings.isZoomControlsEnabled = true
        // Habilitamos la brújula, solo aparecerá cuando giremos el mapa.
        nMap.uiSettings.isCompassEnabled = true
        configMap()

        nMap.setOnMapLongClickListener {
            Log.d("onMapLongClickListener", it.toString())
            placeMarkerOnMap(it)
            nMap.animateCamera(CameraUpdateFactory.newLatLng(it))
        }
    }

    private fun configMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        // Añadimos la marca en la ubicación que nos encontramos.
        nMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            // Vamos a la última ubicación conocida,
            // en algunos casos puede ser null.
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude,
                    location.longitude)
                nMap.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f)
                )
            }
        }
    }

    private fun placeMarkerOnMap(location: LatLng) {

        // Creamos un objeto MarkerOptions para configurar la marca.
        val markerOptions = MarkerOptions().position(location)
        // Añadimos la marca al mapa.
        markerOptions.icon(
            BitmapDescriptorFactory.defaultMarker(
                BitmapDescriptorFactory.HUE_ORANGE
            )
        )
        val titleStr = getAddress(location)
        Log.d("titleStr", titleStr)
        markerOptions.title(titleStr)
        if (lastMarker != null)
            lastMarker!!.remove()
        // Añadimos la marca al mapa.
        lastMarker = nMap.addMarker(markerOptions)
    }

    private fun getAddress(latLng: LatLng): String {
        // Instanciamos las variables necesarias.
        val geocoder = Geocoder(this)
        // Se pueden obtener más de una dirección de un mismo punto.
        val addresses: List<Address>?
        val address: Address?
        var addressText = ""
        try {
            // Obtenemos la información del punto concreto.
            addresses = geocoder.getFromLocation(
                latLng.latitude,
                latLng.longitude,
                1
            )
            if (null != addresses && addresses.isNotEmpty()) {
                // Nos quedamos con la primera posición.
                address = addresses[0]
                // Comprobamos que la dirección tenga o no más de una línea.
                if (address.maxAddressLineIndex > 0) {
                    for (i in 0 until address.maxAddressLineIndex) {
                        addressText += if (i == 0) address.getAddressLine(i)
                        else "\n" + address.getAddressLine(i)
                    }
                } else { // Acción más habitual.
                    addressText += address.thoroughfare + ", " +
                            address.subThoroughfare + "\n"
                }
            }
        } catch (e: IOException) {
            Log.e("MapsActivity", e.localizedMessage)
        }
        return addressText
    }
}
