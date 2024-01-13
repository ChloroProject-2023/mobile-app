package vn.edu.usth.mobile_app.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vn.edu.usth.mobile_app.databinding.FragmentModelBinding

class ModelFragment: Fragment() {
    private var _binding: FragmentModelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModelBinding.inflate(inflater, container, false)
        return binding.root
    }
}