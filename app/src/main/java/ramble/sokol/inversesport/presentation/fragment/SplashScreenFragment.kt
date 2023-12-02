package ramble.sokol.inversesport.presentation.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import ramble.sokol.inverseeducation.presentation.manager.FirstEntryManager
import ramble.sokol.inversesport.R
import ramble.sokol.inversesport.databinding.FragmentSplashScreenBinding


class SplashScreenFragment : Fragment() {

    private var binding: FragmentSplashScreenBinding? = null
    private lateinit var firstEntryManager: FirstEntryManager

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
        firstEntryManager = FirstEntryManager(requireActivity())
        Handler().postDelayed(Runnable {
            if (firstEntryManager.getFirstEntry() == true){
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                val bottomNavBarFragment = BottomNavBarFragment()
                transaction.replace(R.id.layout_fragment, bottomNavBarFragment)
                transaction.disallowAddToBackStack()
                transaction.commit()
            }else {
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                val loginFragment = LoginFragment()
                transaction.replace(R.id.layout_fragment, loginFragment)
                transaction.disallowAddToBackStack()
                transaction.commit()
            }
        }, 3000)
    }

}