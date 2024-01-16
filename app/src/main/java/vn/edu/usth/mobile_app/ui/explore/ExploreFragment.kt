package vn.edu.usth.mobile_app.ui.explore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import vn.edu.usth.mobile_app.databinding.FragmentRecyclerViewBinding

class ExploreFragment : Fragment() {
    private var _binding: FragmentRecyclerViewBinding? = null
    private val exploreListAdapter = ExplorePagingAdapter()
    private val binding get() = _binding!!
    private val viewModel: ExploreViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerViewRecyclerView
        recyclerView.apply {
            adapter = exploreListAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            setItemViewCacheSize(10)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            Log.d("ExploreFragment", "collectLatest")
            viewModel.modelList.collectLatest {
                Log.d("ExploreFragment", "submitData")
                exploreListAdapter.submitData(it)
            }
        }
    }
}