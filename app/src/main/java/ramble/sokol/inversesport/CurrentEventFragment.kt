package ramble.sokol.inversesport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ramble.sokol.inverseeducation.presentation.manager.TokenManager
import ramble.sokol.inversesport.databinding.FragmentAfishaBinding
import ramble.sokol.inversesport.databinding.FragmentCurrentEventBinding
import ramble.sokol.inversesport.presentation.fragment.BottomNavBarFragment
import ramble.sokol.inversesport.presentation.fragment.LoginFragment

class CurrentEventFragment : Fragment() {

    private var binding: FragmentCurrentEventBinding? = null
    private lateinit var tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCurrentEventBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        tokenManager = TokenManager(requireActivity())
        binding!!.buttonBack.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment, BottomNavBarFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }

}