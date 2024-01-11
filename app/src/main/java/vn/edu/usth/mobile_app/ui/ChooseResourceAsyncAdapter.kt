package vn.edu.usth.mobile_app.ui

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.ItemListChooseResourceBinding
import vn.edu.usth.mobile_app.model.ResourcesData
import vn.edu.usth.mobile_app.util.AsyncCell
import java.text.DateFormat

class ChooseResourceAsyncAdapter: RecyclerView.Adapter<ChooseResourceAsyncAdapter.ViewHolder>() {
    private val resourceList: ArrayList<ResourcesData> = ArrayList()


    class ViewHolder(view: ViewGroup) : RecyclerView.ViewHolder(view){}

    class ResourceAsyncCell(context: Context): AsyncCell(context, R.layout.item_list_choose_resource) {
        lateinit var binding: ItemListChooseResourceBinding

        override fun createDataBindingView(view: View): View {
            binding = ItemListChooseResourceBinding.bind(view)
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
            cell.binding.textViewItemChooseResourcesName.text = resourceList[position].fileName
            cell.binding.textViewItemChooseResourcesType.text = resourceList[position].type
            val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)
            val dateString = dateFormat.format(resourceList[position].date)
            cell.binding.textViewItemChooseResourcesDate.text = dateString

            cell.setOnClickListener {

            }
        }
    }

    fun updateList(newList: ArrayList<ResourcesData>) {
        resourceList.clear()
        resourceList.addAll(newList)
        notifyDataSetChanged()
    }
}