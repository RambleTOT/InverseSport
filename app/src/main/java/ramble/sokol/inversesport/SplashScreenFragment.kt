package ramble.sokol.inversesport

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import ramble.sokol.inversesport.databinding.FragmentSplashScreenBinding


class SplashScreenFragment : Fragment() {

    private var binding: FragmentSplashScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val anim = AnimationUtils.loadAnimation(requireActivity(), R.anim.splash_screen_animation)
        binding!!.imageSplashScreen.animation = anim
        Handler().postDelayed(Runnable {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val bottomNavBarFragment = BottomNavBarFragment()
            transaction.replace(R.id.layout_fragment, bottomNavBarFragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }, 3000)
    }

}