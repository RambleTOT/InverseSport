package ramble.sokol.inversesport.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ramble.sokol.inverseeducation.presentation.manager.FirstEntryManager
import ramble.sokol.inverseeducation.presentation.manager.RetrofitHelper
import ramble.sokol.inverseeducation.presentation.manager.TokenManager
import ramble.sokol.inversesport.R
import ramble.sokol.inversesport.databinding.FragmentLoginBinding
import ramble.sokol.inversesport.model.entity.GetTokenResponse
import ramble.sokol.inversesport.model.entity.UserLoginEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    var binding: FragmentLoginBinding? = null
    private lateinit var tokenManager: TokenManager
    private lateinit var firstEntryManager: FirstEntryManager

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
        tokenManager = TokenManager(requireActivity())
        firstEntryManager = FirstEntryManager(requireActivity())
        binding!!.buttonToRegistration.setOnClickListener {
            val fragmentTransition = parentFragmentManager.beginTransaction()
            fragmentTransition.replace(R.id.layout_fragment , SignupFragment())
            fragmentTransition.commit()
        }
        binding!!.buttonLogin.setOnClickListener {
            loginRequest(binding!!.editLoginEmail.text.toString(), binding!!.editLoginPassword.text.toString())
        }
    }

    private fun loginRequest(email: String, password: String){
        RetrofitHelper().getApi().getToken(UserLoginEntity(email, password)).enqueue(object :
            Callback<GetTokenResponse> {
            override fun onResponse(
                call: Call<GetTokenResponse>,
                response: Response<GetTokenResponse>
            ) {
                if (response.isSuccessful) {
                    tokenManager.saveToken(response.body()!!.token.toString())
                    firstEntryManager.saveFirstEntry(true)
                    val transaction = activity!!.supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.layout_fragment, BottomNavBarFragment())
                    transaction.disallowAddToBackStack()
                    transaction.commit()
                } else {
                    //binding!!.textErrorLogin.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<GetTokenResponse>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(
                    activity,
                    "Возникла ошибка, проверьте подключение",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

}