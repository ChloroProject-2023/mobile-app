package vn.edu.usth.mobile_app.ui.history

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import vn.edu.usth.mobile_app.databinding.ActivityRecyclerViewBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var expandableCardRecyclerAdapter: HistoryAsyncRecyclerAdapter
    private lateinit var binding: ActivityRecyclerViewBinding
    private val viewModel: HistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.materialToolbarRVActivity
        toolbar.setTitle("History")
        toolbar.setNavigationOnClickListener{
            finish()
        }

        viewModel.fetchHistoryList()
        val historyList = viewModel.historyList
        expandableCardRecyclerAdapter = HistoryAsyncRecyclerAdapter(historyList)
        val recyclerView = binding.recyclerViewRVActivity
        recyclerView.adapter = expandableCardRecyclerAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}