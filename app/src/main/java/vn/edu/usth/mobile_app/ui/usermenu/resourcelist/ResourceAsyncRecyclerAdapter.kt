package vn.edu.usth.mobile_app.ui.usermenu.resourcelist

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.ItemListResourcesBinding
import vn.edu.usth.mobile_app.model.ResourcesData
import vn.edu.usth.mobile_app.util.AsyncCell
import java.text.DateFormat

class ResourceAsyncRecyclerAdapter(
    private val resourceList: ArrayList<ResourcesData>
): RecyclerView.Adapter<ResourceAsyncRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: ViewGroup) : RecyclerView.ViewHolder(view){}

    class ResourceAsyncCell(context: Context): AsyncCell(context, R.layout.item_list_resources) {
        lateinit var binding: ItemListResourcesBinding

        override fun createDataBindingView(view: View): View {
            binding = ItemListResourcesBinding.bind(view)
            return binding.root
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val frame = ResourceAsyncCell(parent.context)
        frame.inflate()
        return ViewHolder(frame)
    }

    override fun getItemCount(): Int {
        return resourceList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cell = holder.itemView as ResourceAsyncCell
        cell.bindWhenInflated {
            cell.binding.textViewItemResourcesName.text = resourceList[position].fileName
            cell.binding.textViewItemResourcesType.text = resourceList[position].type
            val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)
            val dateString = dateFormat.format(resourceList[position].date)
            cell.binding.textViewItemResourcesDate.text = dateString
        }
    }
}