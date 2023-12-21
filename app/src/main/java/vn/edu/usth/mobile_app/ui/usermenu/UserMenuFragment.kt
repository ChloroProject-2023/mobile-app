package vn.edu.usth.mobile_app.ui.usermenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vn.edu.usth.mobile_app.databinding.FragmentUserMenuBinding

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

        historyCard.setOnClickListener {

        }
        return binding.root
    }
}