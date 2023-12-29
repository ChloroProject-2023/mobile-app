package vn.edu.usth.mobile_app.ui.admin

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import vn.edu.usth.mobile_app.databinding.ActivityRecyclerViewBinding

class UserListActivity: AppCompatActivity() {
    private lateinit var userListAdapter: UserListAsyncAdapter
    private lateinit var binding: ActivityRecyclerViewBinding
    private val viewModel: UserListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.materialToolbarRVActivity
        toolbar.setTitle("User List")
        toolbar.setNavigationOnClickListener {
            finish()
        }

        userListAdapter = UserListAsyncAdapter(viewModel.userList)
        val recyclerView = binding.recyclerViewRVActivity
        recyclerView.adapter = userListAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}