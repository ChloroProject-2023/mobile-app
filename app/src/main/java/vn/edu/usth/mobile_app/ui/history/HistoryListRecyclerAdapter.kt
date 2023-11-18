package vn.edu.usth.mobile_app.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.edu.usth.mobile_app.model.HistoryData
import vn.edu.usth.mobile_app.databinding.ItemListHistoryBinding
import java.text.DateFormat

class HistoryListRecyclerAdapter(
    private var historyList: ArrayList<HistoryData>
): RecyclerView.Adapter<HistoryListRecyclerAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemListHistoryBinding) : RecyclerView.ViewHolder(binding.root){
        val modelName = binding.textViewItemHistoryModelName
        val runDate = binding.textViewItemHistoryRunDate
        init {
            binding.root.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemListHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.modelName.text = historyList[position].getModelName()
        val dateString = DateFormat.getDateInstance(DateFormat.SHORT).format(historyList[position].getDate())
        holder.runDate.text = dateString
    }

}