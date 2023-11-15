package vn.edu.usth.mobile_app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.model.ModelData

class ExploreListRecyclerAdapter(
    private var modelList: ArrayList<ModelData>
): RecyclerView.Adapter<ExploreListRecyclerAdapter.ViewHolder>() {

    class ViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem){
        var modelName: TextView
        var authorName: TextView
        var usage: TextView

        init {
            modelName = viewItem.findViewById(R.id.textView_itemExplore_modelName)
            authorName = viewItem.findViewById(R.id.textView_itemExplore_authorName)
            usage = viewItem.findViewById(R.id.textView_itemExplore_usage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_explore, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.modelName.text = modelList[position].getName()
        holder.authorName.text = modelList[position].getCreatorName()
        holder.usage.text = modelList[position].getUsage().toString()
    }
}