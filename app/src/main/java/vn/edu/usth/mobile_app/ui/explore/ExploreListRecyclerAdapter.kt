package vn.edu.usth.mobile_app.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.edu.usth.mobile_app.databinding.ItemListExploreBinding
import vn.edu.usth.mobile_app.model.ModelData

class ExploreListRecyclerAdapter(
    private var modelList: ArrayList<ModelData>
): RecyclerView.Adapter<ExploreListRecyclerAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemListExploreBinding): RecyclerView.ViewHolder(binding.root){
        var modelName = binding.textViewItemExploreModelName
        var authorName = binding.textViewItemExploreAuthorName
        var usage = binding.textViewItemExploreUsage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListExploreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
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