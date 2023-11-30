package ramble.sokol.inversesport.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ramble.sokol.inversesport.R
import ramble.sokol.inversesport.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding!!.buttonToRegistration.setOnClickListener {
            val fragmentTransition = parentFragmentManager.beginTransaction()
            fragmentTransition.replace(R.id.frame_layout, SignupFragment())
            fragmentTransition.commit()
        }
        binding!!.buttonLogin.setOnClickListener {
            loginRequest()
        }
    }

    private fun loginRequest(){

    }

}