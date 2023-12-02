package ramble.sokol.inversesport.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import ramble.sokol.inverseeducation.presentation.manager.FirstEntryManager
import ramble.sokol.inverseeducation.presentation.manager.RetrofitHelper
import ramble.sokol.inverseeducation.presentation.manager.TokenManager
import ramble.sokol.inversesport.R
import ramble.sokol.inversesport.databinding.FragmentSignupBinding
import ramble.sokol.inversesport.model.entity.GetTokenResponse
import ramble.sokol.inversesport.model.entity.UserLoginEntity
import ramble.sokol.inversesport.model.entity.UserSignupEntity
import ramble.sokol.inversesport.model.entity.UserSignupResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupFragment : Fragment() {

    private var binding: FragmentSignupBinding? = null
    private lateinit var tokenManager: TokenManager
    private lateinit var firstEntryManager: FirstEntryManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false)
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
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        val ageRegex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
        binding!!.buttonToLogin.setOnClickListener {
            val fragmentTransition = parentFragmentManager.beginTransaction()
            fragmentTransition.replace(R.id.layout_fragment , LoginFragment())
            fragmentTransition.commit()
        }
        binding!!.buttonSignup.setOnClickListener {
            var d = 0
            if (binding!!.editNameSignup.text.toString() == "") {
                binding!!.editNameSignup.setBackgroundResource(R.drawable.edit_text_background_error)
                d += 1
            }
            if (binding!!.editSurnameSignup.text.toString() == "") {
                d += 1
                binding!!.editSurnameSignup.setBackgroundResource(R.drawable.edit_text_background_error)
            }
            if (binding!!.editEmailSignup.text.toString() == "") {
                d += 1
                binding!!.editEmailSignup.setBackgroundResource(R.drawable.edit_text_background_error)
            }
            if (binding!!.editPasswordSignup.text.toString() == "") {
                d += 1
                binding!!.editPasswordSignup.setBackgroundResource(R.drawable.edit_text_background_error)
            }
            if (binding!!.editAgeSignup.text.toString() == ""){
                d += 1
                binding!!.editAgeSignup  .setBackgroundResource(R.drawable.edit_text_background_error)
            }
            if (!(binding!!.editEmailSignup.text.toString().matches(emailRegex.toRegex()))){
                d += 1
                binding!!.textErrorEmail.visibility = View.VISIBLE
            }
            if (binding!!.editPasswordSignup.text.toString().length < 8){
                d += 1
                binding!!.textErrorPassword.visibility = View.VISIBLE
            }
            if (!(binding!!.editAgeSignup.text.toString().matches(ageRegex))){
                d += 1
                binding!!.textErrorAge.visibility = View.VISIBLE
            }
            if (d == 0) signupRequest()
        }
        binding!!.editEmailSignup.setOnFocusChangeListener { view, b ->
            notErrorEdit()
        }
        binding!!.editEmailSignup.addTextChangedListener {
            notErrorEdit()
        }
        binding!!.editPasswordSignup.setOnFocusChangeListener { view, b ->
            notErrorEdit()
        }
        binding!!.editPasswordSignup.addTextChangedListener {
            notErrorEdit()
        }
        binding!!.editNameSignup.setOnFocusChangeListener { view, b ->
            notErrorEdit()
        }
        binding!!.editNameSignup.addTextChangedListener {
            notErrorEdit()
        }
        binding!!.editSurnameSignup.setOnFocusChangeListener { view, b ->
            notErrorEdit()
        }
        binding!!.editSurnameSignup.addTextChangedListener {
            notErrorEdit()
        }
        binding!!.editAgeSignup.setOnFocusChangeListener { view, b ->
            notErrorEdit()
        }
        binding!!.editAgeSignup.addTextChangedListener {
            notErrorEdit()
        }
    }

    private fun signupRequest(){
        Log.d("MyLog", UserSignupEntity(binding!!.editEmailSignup.text.toString(), binding!!.editPasswordSignup.text.toString(), binding!!.editNameSignup.text.toString(), binding!!.editSurnameSignup.text.toString(), binding!!.editAgeSignup.text.toString(), 1).toString())
        RetrofitHelper().getApi().createAccount(
            UserSignupEntity(binding!!.editEmailSignup.text.toString(), binding!!.editPasswordSignup.text.toString(), binding!!.editNameSignup.text.toString(), binding!!.editSurnameSignup.text.toString(), binding!!.editAgeSignup.text.toString(), 1)
        ).enqueue(object : Callback<UserSignupResponse>{
            override fun onResponse(
                call: Call<UserSignupResponse>,
                response: Response<UserSignupResponse>
            ) {
                Log.d("MyLog", response.toString())
                if (response.isSuccessful){
                    loginRequest()
                    Toast.makeText(
                        activity,
                        "Регистрация прошла успешна",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    binding!!.textErrorEmail2.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<UserSignupResponse>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(
                    activity,
                    "Возникла ошибка, проверьте подключение",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    private fun loginRequest(){
        RetrofitHelper().getApi().getToken(UserLoginEntity(binding!!.editEmailSignup.text.toString(), binding!!.editPasswordSignup.text.toString())).enqueue(object :
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

    private fun notErrorEdit(){
        binding!!.textErrorEmail.visibility = View.GONE
        binding!!.textErrorPassword.visibility = View.GONE
        binding!!.textErrorAge.visibility = View.GONE
        binding!!.textErrorEmail2.visibility = View.GONE
        binding!!.editEmailSignup.setBackgroundResource(R.drawable.edit_text_background)
        binding!!.editPasswordSignup.setBackgroundResource(R.drawable.edit_text_background)
        binding!!.editSurnameSignup.setBackgroundResource(R.drawable.edit_text_background)
        binding!!.editNameSignup.setBackgroundResource(R.drawable.edit_text_background)
        binding!!.editAgeSignup.setBackgroundResource(R.drawable.edit_text_background)
    }

}