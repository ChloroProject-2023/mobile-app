package vn.edu.usth.mobile_app.ui.explore

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import vn.edu.usth.mobile_app.databinding.ItemListExploreBinding
import vn.edu.usth.mobile_app.model.ModelData
import vn.edu.usth.mobile_app.ui.modeldetails.ModelDetailsActivity

class ExploreListRecyclerAdapter(
    private var modelList: ArrayList<ModelData>
): RecyclerView.Adapter<ExploreListRecyclerAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemListExploreBinding): RecyclerView.ViewHolder(binding.root){
        var modelId = -1
        val modelName = binding.textViewItemExploreModelName
        val authorName = binding.textViewItemExploreAuthorName
        val usage = binding.textViewItemExploreUsage
        private val detailsButton = binding.materialButtonItemExploreDetails
        private val useButton = binding.materialButtonItemExploreUse

        init {
            detailsButton.setOnClickListener {
                // Open model details
                val intent = Intent(it.context, ModelDetailsActivity::class.java)
                intent.putExtra("modelID", modelId)
                startActivity(it.context, intent, null)
            }

            useButton.setOnClickListener {
                // Use model
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListExploreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.modelId = modelList[position].id
        holder.modelName.text = modelList[position].name
        holder.authorName.text = modelList[position].creatorName
        holder.usage.text = modelList[position].usage.toString()
    }
}