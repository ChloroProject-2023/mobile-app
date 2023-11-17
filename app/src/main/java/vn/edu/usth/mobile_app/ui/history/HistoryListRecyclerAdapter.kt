package vn.edu.usth.mobile_app.ui.history

import android.animation.LayoutTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import vn.edu.usth.mobile_app.model.HistoryData
import vn.edu.usth.mobile_app.R
import java.text.DateFormat

class HistoryListRecyclerAdapter(
    private var historyList: ArrayList<HistoryData>
): RecyclerView.Adapter<HistoryListRecyclerAdapter.ViewHolder>() {

    class ViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem){
        private var expanded: Boolean = false
        var modelName: TextView
        var runDate: TextView
        var dataPath: TextView
        var resultDetails: TextView
        init {
            modelName = viewItem.findViewById(R.id.textView_itemHistory_modelName)
            runDate = viewItem.findViewById(R.id.textView_itemHistory_runDate)
            dataPath = viewItem.findViewById(R.id.textView_itemHistory_dataPath)
            resultDetails = viewItem.findViewById(R.id.textView_itemHistory_resultDetails)

            viewItem.setOnClickListener {
                expanded = !expanded
                val expandButton = viewItem.findViewById<MaterialButton>(R.id.button_itemHistory_expandButton)
                val expandArea = viewItem.findViewById<ConstraintLayout>(R.id.constraintLayout_itemHistory_expandableLayout)
                val body = viewItem.findViewById<LinearLayout>(R.id.linearLayout_itemHistory_cardBody)
                body.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

                TransitionManager.beginDelayedTransition(body, android.transition.AutoTransition())

                if (expanded) {
                    expandArea.visibility = View.VISIBLE
                    expandButton.background = AppCompatResources.getDrawable(
                        viewItem.context,
                        R.drawable.baseline_keyboard_arrow_up_24
                    )
                }
                else {
                    expandArea.visibility = View.GONE
                    expandButton.background = AppCompatResources.getDrawable(
                        viewItem.context,
                        R.drawable.baseline_keyboard_arrow_down_24
                    )
                }
            }

            viewItem.findViewById<MaterialButton>(R.id.button_itemHistory_expandButton).setOnClickListener {
                expanded = !expanded
                val expandButton = viewItem.findViewById<MaterialButton>(R.id.button_itemHistory_expandButton)
                val expandArea = viewItem.findViewById<ConstraintLayout>(R.id.constraintLayout_itemHistory_expandableLayout)
                val body = viewItem.findViewById<LinearLayout>(R.id.linearLayout_itemHistory_cardBody)
                body.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

                TransitionManager.beginDelayedTransition(body, android.transition.AutoTransition())

                if (expanded) {
                    expandArea.visibility = View.VISIBLE
                    expandButton.background = AppCompatResources.getDrawable(
                        viewItem.context,
                        R.drawable.baseline_keyboard_arrow_up_24
                    )
                }
                else {
                    expandArea.visibility = View.GONE
                    expandButton.background = AppCompatResources.getDrawable(
                        viewItem.context,
                        R.drawable.baseline_keyboard_arrow_down_24
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_history, parent, false)
        return ViewHolder(view)
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