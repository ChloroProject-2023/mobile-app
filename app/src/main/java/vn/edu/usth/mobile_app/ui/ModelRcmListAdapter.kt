package vn.edu.usth.mobile_app.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import vn.edu.usth.mobile_app.databinding.ItemListRcmModelBinding
import vn.edu.usth.mobile_app.model.ModelData

class ModelRcmListAdapter(
    private val modelList: List<ModelData>
): RecyclerView.Adapter<ModelRcmListAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemListRcmModelBinding) : RecyclerView.ViewHolder(binding.root){
        var modelId: Int = -1
        val modelName = binding.textViewItemRcmModelModelName
        val modelCreator = binding.textViewItemRcmModelAuthor
        val modelDescription = binding.textViewItemRcmModelModelDescription
        val modelUsage = binding.textViewItemRcmModelUsage
        init {
            binding.root.setOnClickListener {
                // Open model details
                val intent = Intent(it.context, ModelDetailsActivity::class.java)
                intent.putExtra("modelID", modelId)
                startActivity(it.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemListRcmModelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.modelId = modelList[position].id
        holder.modelName.text = modelList[position].name
        holder.modelCreator.text = modelList[position].creatorName
        holder.modelDescription.text = modelList[position].description
        holder.modelUsage.text = modelList[position].usage.toString()
    }
}