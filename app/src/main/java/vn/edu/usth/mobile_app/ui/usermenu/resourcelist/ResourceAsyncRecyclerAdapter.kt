package vn.edu.usth.mobile_app.ui.usermenu.resourcelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.ItemListResourcesBinding
import vn.edu.usth.mobile_app.model.ResourcesData
import vn.edu.usth.mobile_app.util.AsyncCell
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.DateFormat

class ResourceAsyncRecyclerAdapter(
    private val deleteResource: (Int) -> Boolean
): RecyclerView.Adapter<ResourceAsyncRecyclerAdapter.ViewHolder>() {
    private val resourceList: ArrayList<ResourcesData> = ArrayList()


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

            // Bind the click listener to binding.materialButtonItemResourcesMore
            cell.binding.materialButtonItemResourcesMore?.setOnClickListener {
                val bottomSheet = BottomSheetDialog(context)
                val view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_dialog, this, false)
                bottomSheet.setContentView(view)
                bottomSheet.show()
                // Set the click listener for the m3button_delete
                view.findViewById<View>(R.id.m3button_delete).setOnClickListener {
                    MaterialAlertDialogBuilder(context)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete this resource?")
                        .setNegativeButton("Cancel") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .setPositiveButton("Delete") { _, _ ->
                            deleteResource(resourceList[position].id)
                            resourceList.removeAt(position)
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, resourceList.size)
                            bottomSheet.dismiss()
                        }
                        .show()
                }
            }
        }
    }

    fun updateList(newList: ArrayList<ResourcesData>) {
        resourceList.clear()
        resourceList.addAll(newList)
        notifyDataSetChanged()
    }
}