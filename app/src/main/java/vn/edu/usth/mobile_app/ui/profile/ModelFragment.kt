package vn.edu.usth.mobile_app.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import vn.edu.usth.mobile_app.databinding.FragmentRecyclerViewBinding

class ModelFragment: Fragment() {
    private lateinit var _binding: FragmentRecyclerViewBinding
    private val binding get() = _binding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]

        return binding.root
    }
}