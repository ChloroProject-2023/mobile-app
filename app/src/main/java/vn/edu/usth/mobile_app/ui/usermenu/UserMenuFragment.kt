package vn.edu.usth.mobile_app.ui.usermenu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import vn.edu.usth.mobile_app.MainViewModel
import vn.edu.usth.mobile_app.databinding.FragmentUserMenuBinding
import vn.edu.usth.mobile_app.ui.history.HistoryActivity
import vn.edu.usth.mobile_app.ui.usermenu.resourcelist.ResourcesListActivity

class UserMenuFragment: Fragment() {
    private var _binding: FragmentUserMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserMenuBinding.inflate(inflater, container, false)

        val historyCard = binding.cardViewUserMenuHistory
        val uploadedModelsCard = binding.cardViewUserMenuUploadedModels
        val usedModelsCard = binding.cardViewUserMenuUsedModels
        val resourcesCard = binding.cardViewUserMenuResources
        val logoutButton = binding.buttonUserMenuLogout

        historyCard.setOnClickListener {
            val intent = Intent(requireContext(), HistoryActivity::class.java)
            startActivity(intent)
        }

        resourcesCard.setOnClickListener {
            val intent = Intent(requireContext(), ResourcesListActivity::class.java)
            startActivity(intent)
        }

        logoutButton.setOnClickListener {
            val viewModel: MainViewModel by activityViewModels()
            viewModel.logout()

            // Restart the activity
            val intent = requireActivity().intent
            requireActivity().finish()
            startActivity(intent)
        }
        return binding.root
    }
}