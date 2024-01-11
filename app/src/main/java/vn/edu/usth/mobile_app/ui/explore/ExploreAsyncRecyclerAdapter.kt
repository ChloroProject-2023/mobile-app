package vn.edu.usth.mobile_app.ui.explore

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.ItemListExploreBinding
import vn.edu.usth.mobile_app.model.ModelData
import vn.edu.usth.mobile_app.ui.ChooseResourceActivity
import vn.edu.usth.mobile_app.ui.modeldetails.ModelDetailsActivity
import vn.edu.usth.mobile_app.util.AsyncCell

class ExploreAsyncRecyclerAdapter(
    private val modelList: List<ModelData>
): RecyclerView.Adapter<ExploreAsyncRecyclerAdapter.ViewHolder>() {
    class ViewHolder(view: ViewGroup): RecyclerView.ViewHolder(view) {}

    class ExploreAsyncCell(context: Context): AsyncCell(context, R.layout.item_list_explore) {
        lateinit var binding: ItemListExploreBinding

        override fun createDataBindingView(view: View): View {
            binding = ItemListExploreBinding.bind(view)
            return binding.root
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val frame = ExploreAsyncCell(parent.context)
        frame.inflate()
        return ViewHolder(frame)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cell = holder.itemView as ExploreAsyncCell

        cell.bindWhenInflated {
            cell.binding.textViewItemExploreAuthorName.text = modelList[position].creatorName
            cell.binding.textViewItemExploreModelName.text = modelList[position].name
            cell.binding.textViewItemExploreUsage.text = modelList[position].usage.toString()
            cell.binding.materialButtonItemExploreDetails.setOnClickListener {
                // Open model details
                 val intent = Intent(it.context, ModelDetailsActivity::class.java)
                 intent.putExtra("modelID", modelList[position].id)
                 startActivity(it.context, intent, null)
            }
            cell.binding.materialButtonItemExploreUse.setOnClickListener {
                // Open choose resource activity
                val intent = Intent(it.context, ChooseResourceActivity::class.java)
                intent.putExtra("modelID", modelList[position].id)
                startActivity(it.context, intent, null)
            }
        }
    }
}