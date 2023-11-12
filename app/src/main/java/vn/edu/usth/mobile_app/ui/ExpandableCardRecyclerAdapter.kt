package vn.edu.usth.mobile_app.ui

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

class ExpandableCardRecyclerAdapter(
    private var historyList: ArrayList<HistoryData>
): RecyclerView.Adapter<ExpandableCardRecyclerAdapter.ViewHolder>() {

    class ViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem){
        private var expanded: Boolean = false
        init {
            viewItem.setOnClickListener {
                expanded = !expanded
                val expandButton = viewItem.findViewById<MaterialButton>(R.id.expandButton)
                val expandArea = viewItem.findViewById<ConstraintLayout>(R.id.expandableLayout)
                val body = viewItem.findViewById<LinearLayout>(R.id.linearLayout_expandableCard_body)
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
        holder.itemView.findViewById<TextView>(R.id.textView_modelName).text = historyList[position].getModelName()
        holder.itemView.findViewById<TextView>(R.id.textView_runDate).text = historyList[position].getDate()
        holder.itemView.findViewById<TextView>(R.id.textView_dataPath).text = historyList[position].getUploadedData()
        holder.itemView.findViewById<TextView>(R.id.textView_result).text = historyList[position].getResult()
    }

}