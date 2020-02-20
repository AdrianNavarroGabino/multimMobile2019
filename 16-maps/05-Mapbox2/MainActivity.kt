package com.example.mapbox

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnMapReadyCallback, PermissionsListener {

    private lateinit var mapboxMap: MapboxMap

    private var permissionsManager: PermissionsManager =
        PermissionsManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, resources.getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_main);

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)


    }

    override fun onMapReady(mapboxMap: MapboxMap) {
        this.mapboxMap = mapboxMap
        mapboxMap.setStyle(Style.MAPBOX_STREETS) {
            // Activamos la localización.
            enableLocationComponent(it)
        }
    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?)
    {
        Toast.makeText(
            this,
            "Se necesita el permiso de localización para el funcionamiento.",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            enableLocationComponent(mapboxMap.style!!)
        } else {
            Toast.makeText(
                this,
                "No está concedido el permiso!",
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        permissionsManager.onRequestPermissionsResult(
            requestCode, permissions, grantResults)
    }

    private fun enableLocationComponent(loadedMapStyle: Style) =
        // Comprobamos si el permiso está concedido.
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            // Creamos y personalizamos las opciones de LocationComponent.
            val customLocationComponentOptions =
                LocationComponentOptions.builder(this)
                    .trackingGesturesManagement(true).build()
            // Preparamos la activación para mostrar la localización.
            val locationComponentActivationOptions =
                LocationComponentActivationOptions.builder(this, loadedMapStyle)
                    .locationComponentOptions(customLocationComponentOptions)
                    .build()
            // Establecemos la instancia de LocationComponent y aplicamos
            // los ajustes.
            mapboxMap.locationComponent.apply {
                // Activate the LocationComponent with options
                activateLocationComponent(locationComponentActivationOptions)
                // Hacemos visible LocationComponent.
                isLocationComponentEnabled = true
                // Establecemos el modo de cámara
                cameraMode = CameraMode.TRACKING
                // Se establece el modo de renderizado, o seguimiento.
                renderMode = RenderMode.COMPASS
            }
            val lat = mapboxMap.locationComponent.lastKnownLocation!!.latitude
            val long = mapboxMap.locationComponent.lastKnownLocation!!.longitude
            val position = CameraPosition.Builder()
                .target(LatLng(lat, long)) // Establecemos la nueva posición.
                .zoom(12.0) // Asignamos el zoom. Entre 0 y 22, de lejos a cerca.
                .bearing(180.0) // Rotación de la cámara.
                .tilt(30.0) // Ajusta la inclinación de la cámara.
                .build() // Creamos la nueva posición de la cámara.

            mapboxMap.easeCamera(
                CameraUpdateFactory.newCameraPosition(position),
                3000
            )
        } else { // Permiso no concedido.
            permissionsManager = PermissionsManager(this)
            permissionsManager.requestLocationPermissions(this)
        }
}
