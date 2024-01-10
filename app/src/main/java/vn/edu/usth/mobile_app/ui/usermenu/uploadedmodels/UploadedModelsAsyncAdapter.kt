package vn.edu.usth.mobile_app.ui.usermenu.uploadedmodels

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.ItemListRcmModelBinding
import vn.edu.usth.mobile_app.model.ModelData
import vn.edu.usth.mobile_app.util.AsyncCell

class UploadedModelsAsyncAdapter: RecyclerView.Adapter<UploadedModelsAsyncAdapter.ViewHolder>() {
    private val uploadedModelsList = ArrayList<ModelData>()

    class ViewHolder(viewGroup: ViewGroup): RecyclerView.ViewHolder(viewGroup) {}

    class UploadedModelsAsyncCell(context: Context): AsyncCell(context, R.layout.item_list_rcm_model) {
        lateinit var binding: ItemListRcmModelBinding

        override fun createDataBindingView(view: android.view.View): android.view.View {
            binding = ItemListRcmModelBinding.bind(view)
            return binding.root
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val frame = UploadedModelsAsyncCell(parent.context)
        frame.inflate()
        return ViewHolder(frame)
    }

    override fun getItemCount(): Int {
        return uploadedModelsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cell = holder.itemView as UploadedModelsAsyncCell
        cell.bindWhenInflated {
            cell.binding.textViewItemRcmModelModelName.text = uploadedModelsList[position].name
            cell.binding.textViewItemRcmModelModelDescription.text = uploadedModelsList[position].description
            cell.binding.textViewItemRcmModelAuthor.text = uploadedModelsList[position].creatorName
            cell.binding.textViewItemRcmModelUsage.text = uploadedModelsList[position].usage.toString()
        }
    }

    fun updateList(newList: ArrayList<ModelData>) {
        uploadedModelsList.clear()
        uploadedModelsList.addAll(newList)
        notifyDataSetChanged()
    }

}