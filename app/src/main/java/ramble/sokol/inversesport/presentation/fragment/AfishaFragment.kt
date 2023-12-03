package ramble.sokol.inversesport.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ramble.sokol.inversesport.R
import ramble.sokol.inversesport.databinding.FragmentAfishaBinding
import ramble.sokol.inversesport.databinding.FragmentBottomNavBarBinding


class AfishaFragment : Fragment() {

    private var binding: FragmentAfishaBinding? = null

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

    }

}