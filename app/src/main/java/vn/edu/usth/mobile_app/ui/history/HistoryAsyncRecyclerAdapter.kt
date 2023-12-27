package vn.edu.usth.mobile_app.ui.history

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.ItemListHistoryBinding
import vn.edu.usth.mobile_app.model.HistoryData
import vn.edu.usth.mobile_app.util.AsyncCell
import java.text.DateFormat

class HistoryAsyncRecyclerAdapter(
    private var historyList: ArrayList<HistoryData>
): RecyclerView.Adapter<HistoryAsyncRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: ViewGroup) : RecyclerView.ViewHolder(view){}

    class HistoryAsyncCell(context: Context): AsyncCell(context, R.layout.item_list_history) {
        lateinit var binding: ItemListHistoryBinding

        override fun createDataBindingView(view: View): View {
            binding = ItemListHistoryBinding.bind(view)
            return binding.root
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val frame = HistoryAsyncCell(parent.context)
        frame.inflate()
        return ViewHolder(frame)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cell = holder.itemView as HistoryAsyncCell

        cell.bindWhenInflated {
            cell.binding.textViewItemHistoryModelName.text = historyList[position].modelName
            val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)
            val dateString = dateFormat.format(historyList[position].date)
            cell.binding.textViewItemHistoryRunDate.text = dateString
        }
    }
}