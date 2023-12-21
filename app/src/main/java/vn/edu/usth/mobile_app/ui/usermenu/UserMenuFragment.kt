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

class UserMenuFragment: Fragment() {
    private var _binding: FragmentUserMenuBinding? = null
    private val activityViewModel: MainViewModel by activityViewModels()
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

        historyCard.setOnClickListener {
            val intent = Intent(requireContext(), HistoryActivity::class.java)
            intent.putExtra("userId", activityViewModel.userId)
            startActivity(intent)
        }
        return binding.root
    }
}