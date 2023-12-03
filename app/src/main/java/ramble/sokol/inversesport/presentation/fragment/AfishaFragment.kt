package ramble.sokol.inversesport.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ramble.sokol.inverseeducation.presentation.manager.RetrofitHelper
import ramble.sokol.inverseeducation.presentation.manager.TokenManager
import ramble.sokol.inversesport.R
import ramble.sokol.inversesport.databinding.FragmentAfishaBinding
import ramble.sokol.inversesport.model.entity.GetAllEvents
import ramble.sokol.inversesport.presentation.adapter.AllEventsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AfishaFragment : Fragment() {

    private var binding: FragmentAfishaBinding? = null
    private lateinit var tokenManager: TokenManager
    private lateinit var allEventsAdapter: AllEventsAdapter
    private lateinit var eventsList: List<GetAllEvents>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAfishaBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        tokenManager = TokenManager(requireActivity())
        getAllEvents()
    }

    private fun getAllEvents(){
        RetrofitHelper().getApi().getAllEvents("Token ${tokenManager.getToken()}").enqueue(object : Callback<List<GetAllEvents>>{
            override fun onResponse(
                call: Call<List<GetAllEvents>>,
                response: Response<List<GetAllEvents>>
            ) {
                if (response.isSuccessful){
                    eventsList = response.body()!!
                    binding!!.recyclerEvents.apply {
                        allEventsAdapter = AllEventsAdapter(eventsList)
                        allEventsAdapter.onItemClick = {
                            val transaction = requireActivity().supportFragmentManager.beginTransaction()
                            val currentEventFragment = CurrentEventFragment()
                            val bundle = Bundle()
                            bundle.putInt("id", it.id!!)
                            currentEventFragment.arguments = bundle
                            transaction.replace(R.id.layout_fragment, currentEventFragment)
                            transaction.disallowAddToBackStack()
                            transaction.commit()
                        }
                        adapter = allEventsAdapter
                        layoutManager = LinearLayoutManager(requireActivity());

                    }
                    binding!!.progressProfile.visibility = View.GONE
                    binding!!.linearLayoutAfisha.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<List<GetAllEvents>>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(
                    activity,
                    "Возникла ошибка, проверьте подключение",
                    Toast.LENGTH_SHORT
                ).show()
                binding!!.progressProfile.visibility = View.GONE
                binding!!.linearLayoutEmptyAfisha.visibility = View.VISIBLE
            }

        })
    }

}