package vn.edu.usth.mobile_app.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import vn.edu.usth.mobile_app.databinding.FragmentInfoBinding

class InfoFragment: Fragment() {
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]
        viewModel.userData.observe(viewLifecycleOwner) {
            binding.textViewUserInfoFirstName.text = it.firstname
            binding.textViewUserInfoLastName.text = it.lastname
            binding.textViewUserInfoEmail.text = it.email

            // Convert timestamp in milliseconds to date, hour
            val dateInstance = java.text.DateFormat.getDateTimeInstance()
            val date = dateInstance.format(it.createdAt)
            binding.textViewUserInfoCreateAt.text = date
            binding.textViewUserInfoRole.text = it.role.toString()
        }
        return binding.root
    }
}