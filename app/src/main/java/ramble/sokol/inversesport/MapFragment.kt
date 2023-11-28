package ramble.sokol.inversesport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectTapListener
import ramble.sokol.inversesport.databinding.FragmentMapBinding

class MapFragment : Fragment() {

    private var binding: FragmentMapBinding? = null

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
        val map = binding!!.mapView.mapWindow.map
        map.move(
            CameraPosition(
                Point(55.753164, 37.648106),
                /* zoom = */ 16.0f,
                /* azimuth = */ 150.0f,
                /* tilt = */ 0.0f
            )
        )

        val mapObjectTapListener = MapObjectTapListener { mapObject: MapObject, _ ->
            true
        }
    }

}