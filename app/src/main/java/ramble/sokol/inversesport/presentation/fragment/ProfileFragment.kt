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
import ramble.sokol.inversesport.databinding.FragmentProfileBinding
import ramble.sokol.inversesport.model.entity.UserAccountResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null
    private lateinit var tokenManager: TokenManager
    private lateinit var firstEntryManager: FirstEntryManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        tokenManager = TokenManager(requireActivity())
        firstEntryManager = FirstEntryManager(requireActivity())
        binding!!.buttonExit.setOnClickListener{
            firstEntryManager.saveFirstEntry(false)
            tokenManager.saveToken("")
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment, LoginFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
        getAccount()
    }

    private fun getAccount(){
        RetrofitHelper().getApi().getMyAccount("Token ${tokenManager.getToken()}").enqueue(object : retrofit2.Callback<UserAccountResponse>{
            override fun onResponse(
                call: Call<UserAccountResponse>,
                response: Response<UserAccountResponse>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    binding!!.nameProfile.text = body!!.firstname
                    binding!!.surnameProfile.text = body!!.lastname
                    binding!!.ageProfile.text = body!!.age
                    binding!!.emailProfile.text = body!!.email
                    binding!!.progressProfile.visibility = View.INVISIBLE
                    binding!!.linearLayoutProfile.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<UserAccountResponse>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(
                    activity,
                    "Возникла ошибка, проверьте подключение",
                    Toast.LENGTH_SHORT
                ).show()
                binding!!.progressProfile.visibility = View.INVISIBLE
                binding!!.linearLayoutEmptyProfile.visibility = View.VISIBLE
            }

        })
    }

}