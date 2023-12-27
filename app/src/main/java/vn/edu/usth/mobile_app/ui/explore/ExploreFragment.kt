package vn.edu.usth.mobile_app.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import vn.edu.usth.mobile_app.databinding.FragmentRecyclerViewBinding

class ExploreFragment : Fragment() {
    private lateinit var exploreListRecyclerAdapter: ExploreAsyncRecyclerAdapter
    private var _binding: FragmentRecyclerViewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ExploreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exploreListRecyclerAdapter = ExploreAsyncRecyclerAdapter(viewModel.modelList)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerViewRecyclerView
        recyclerView.adapter = exploreListRecyclerAdapter
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        recyclerView.setItemViewCacheSize(10)
        recyclerView.setHasFixedSize(true)

        val dividerItemDecoration = androidx.recyclerview.widget.DividerItemDecoration(context, androidx.recyclerview.widget.DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        return binding.root
    }
}