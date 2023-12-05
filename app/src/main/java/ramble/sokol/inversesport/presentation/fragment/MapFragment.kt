package ramble.sokol.inversesport.presentation.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.health.connect.datatypes.ExerciseRoute
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.runtime.image.ImageProvider
import ramble.sokol.inversesport.R
import ramble.sokol.inversesport.databinding.FragmentMapBinding
import java.util.concurrent.TimeUnit

class MapFragment : Fragment() {

    private var binding: FragmentMapBinding? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var latitude: Double = 50.0
    private var longitude: Double = 50.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMapBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        checkLocationPermission()
    }

    private fun checkLocationPermission() {
        val task = fusedLocationProviderClient.lastLocation

        if (ActivityCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
            return
        }
        task.addOnSuccessListener {
            if (it != null){
                latitude = it.latitude
                longitude = it.longitude
                createMap()
        }
    }
    }

    private fun createMap(){
        val map = binding!!.mapView.mapWindow.map
        Log.d("MyLog", latitude.toString())
        map.move(
            CameraPosition(
                Point(latitude, longitude),
                /* zoom = */ 16.0f,
                /* azimuth = */ 150.0f,
                /* tilt = */ 0.0f
            )
        )

        map.mapObjects.addPlacemark(
            Point(latitude, longitude),

            )

        map.mapObjects.addPlacemark().setIcon(ImageProvider.fromResource(requireActivity(), R.color.back_edit_text))

        val mapObjectTapListener = MapObjectTapListener { mapObject: MapObject, _ ->
            true
        }
    }

}