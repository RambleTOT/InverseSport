package ramble.sokol.inversesport.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import ramble.sokol.inverseeducation.presentation.manager.RetrofitHelper
import ramble.sokol.inverseeducation.presentation.manager.TokenManager
import ramble.sokol.inversesport.R
import ramble.sokol.inversesport.databinding.FragmentCurrentEventBinding
import ramble.sokol.inversesport.model.entity.GetCurrentEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val bundle = this.arguments
        val id = bundle!!.getInt("id")
        binding!!.buttonBack.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment, BottomNavBarFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
        binding!!.buttonSignupEvent.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment, FinishSignupEventFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
        getCurrentEvent(id)
    }

    private fun getCurrentEvent(id: Int){
        RetrofitHelper().getApi().getCurrentEvent(id, "Token ${tokenManager.getToken()}").enqueue(object : Callback<GetCurrentEvent>{
            override fun onResponse(
                call: Call<GetCurrentEvent>,
                response: Response<GetCurrentEvent>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    Picasso.get().load("https://inverse-tracker.store/${body!!.cover}").into(binding!!.imageCurrentEvent)
                    binding!!.titleCurrentEvent.text = body.name
                    binding!!.descriptionCurrentEvent.text = body.description
                    binding!!.placeDateCurrentEvent.text = "${body.date} · ${body.platform!!.address}"
                }
            }

            override fun onFailure(call: Call<GetCurrentEvent>, t: Throwable) {
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