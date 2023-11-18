package vn.edu.usth.mobile_app.ui.history

import android.animation.LayoutTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import vn.edu.usth.mobile_app.model.HistoryData
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.ItemListHistoryBinding
import java.text.DateFormat

class HistoryListRecyclerAdapter(
    private var historyList: ArrayList<HistoryData>
): RecyclerView.Adapter<HistoryListRecyclerAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemListHistoryBinding) : RecyclerView.ViewHolder(binding.root){
        private var expanded: Boolean = false
        val modelName = binding.textViewItemHistoryModelName
        val runDate = binding.textViewItemHistoryRunDate
        val dataPath = binding.textViewItemHistoryDataPath
        val resultDetails = binding.textViewItemHistoryResultDetails
        init {
            binding.root.setOnClickListener {
                expanded = !expanded
                val expandButton = binding.buttonItemHistoryExpandButton
                val expandArea = binding.constraintLayoutItemHistoryExpandableLayout
                val body = binding.linearLayoutItemHistoryCardBody
                body.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

                TransitionManager.beginDelayedTransition(body, android.transition.AutoTransition())

                if (expanded) {
                    expandArea.visibility = View.VISIBLE
                    expandButton.background = AppCompatResources.getDrawable(
                        binding.root.context,
                        R.drawable.baseline_keyboard_arrow_up_24
                    )
                }
                else {
                    expandArea.visibility = View.GONE
                    expandButton.background = AppCompatResources.getDrawable(
                        binding.root.context,
                        R.drawable.baseline_keyboard_arrow_down_24
                    )
                }
            }

            binding.buttonItemHistoryExpandButton.setOnClickListener {
                expanded = !expanded
                val expandButton = binding.buttonItemHistoryExpandButton
                val expandArea = binding.constraintLayoutItemHistoryExpandableLayout
                val body = binding.linearLayoutItemHistoryCardBody
                body.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

                TransitionManager.beginDelayedTransition(body, android.transition.AutoTransition())

                if (expanded) {
                    expandArea.visibility = View.VISIBLE
                    expandButton.background = AppCompatResources.getDrawable(
                        binding.root.context,
                        R.drawable.baseline_keyboard_arrow_up_24
                    )
                }
                else {
                    expandArea.visibility = View.GONE
                    expandButton.background = AppCompatResources.getDrawable(
                        binding.root.context,
                        R.drawable.baseline_keyboard_arrow_down_24
                    )
                }
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

        // Format Date to String dd/MM/yyyy
        val dateString = DateFormat.getDateInstance(DateFormat.SHORT).format(historyList[position].getDate())
        holder.runDate.text = dateString
        holder.dataPath.text = historyList[position].getUploadedData()
        holder.resultDetails.text = historyList[position].getResult()
    }

}