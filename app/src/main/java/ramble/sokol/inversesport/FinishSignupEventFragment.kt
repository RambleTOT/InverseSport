package ramble.sokol.inversesport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ramble.sokol.inversesport.databinding.FragmentCurrentEventBinding
import ramble.sokol.inversesport.databinding.FragmentFinishSignupEventBinding

class FinishSignupEventFragment : Fragment() {

    private var binding: FragmentFinishSignupEventBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFinishSignupEventBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}