package vn.edu.usth.mobile_app.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import vn.edu.usth.mobile_app.databinding.FragmentRecyclerViewBinding
import vn.edu.usth.mobile_app.model.ResourcesData
import vn.edu.usth.mobile_app.ui.usermenu.resourcelist.ResourceAsyncRecyclerAdapter

class ResourceFragment: Fragment() {
    private lateinit var _binding: FragmentRecyclerViewBinding
    private val binding get() = _binding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var adapter: ResourceAsyncRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]
        adapter = ResourceAsyncRecyclerAdapter(viewModel::deleteResource)
        val listObserver = androidx.lifecycle.Observer<ArrayList<ResourcesData>> {
            adapter.updateList(it)
        }
        viewModel.resourceList.observe(viewLifecycleOwner, listObserver)

        val recyclerView = binding.recyclerViewRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
        return binding.root
    }
}