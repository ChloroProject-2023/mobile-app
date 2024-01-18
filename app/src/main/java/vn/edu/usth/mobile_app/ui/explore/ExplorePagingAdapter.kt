package vn.edu.usth.mobile_app.ui.explore

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.ItemListExploreBinding
import vn.edu.usth.mobile_app.model.ModelData
import vn.edu.usth.mobile_app.ui.ChooseResourceActivity
import vn.edu.usth.mobile_app.ui.GlobalData
import vn.edu.usth.mobile_app.ui.login.LoginActivity
import vn.edu.usth.mobile_app.ui.modeldetails.ModelDetailsActivity
import vn.edu.usth.mobile_app.util.AsyncCell

class ExplorePagingAdapter:
    PagingDataAdapter<ModelData, ExplorePagingAdapter.ViewHolder>(ModelComparator) {

    class ViewHolder(view: ViewGroup): RecyclerView.ViewHolder(view) {}

    class ExploreAsyncCell(context: Context): AsyncCell(context, R.layout.item_list_explore) {
        lateinit var binding: ItemListExploreBinding

        override fun createDataBindingView(view: View): View {
            binding = ItemListExploreBinding.bind(view)
            return binding.root
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val frame = ExploreAsyncRecyclerAdapter.ExploreAsyncCell(parent.context)
        frame.inflate()
        return ViewHolder(frame)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cell = holder.itemView as ExploreAsyncRecyclerAdapter.ExploreAsyncCell

        cell.bindWhenInflated {
            cell.binding.textViewItemExploreAuthorName.text = getItem(position)!!.creatorName
            cell.binding.textViewItemExploreModelName.text = getItem(position)!!.name
            cell.binding.textViewItemExploreUsage.text = getItem(position)!!.usage.toString()
            cell.binding.textViewItemExploreTags!!.text = getItem(position)!!.type
            cell.binding.materialButtonItemExploreDetails.setOnClickListener {
                if (!GlobalData.isLogin) {
                    val intent = Intent(it.context, LoginActivity::class.java)
                    ContextCompat.startActivity(it.context, intent, null)
                } else {
                    // Open model details
                    val intent = Intent(it.context, ModelDetailsActivity::class.java)
                    intent.putExtra("modelID", getItem(position)!!.id)
                    ContextCompat.startActivity(it.context, intent, null)
                }
            }
            cell.binding.materialButtonItemExploreUse.setOnClickListener {
                if (!GlobalData.isLogin) {
                    val intent = Intent(it.context, LoginActivity::class.java)
                    ContextCompat.startActivity(it.context, intent, null)
                } else {
                    // Open choose resource activity
                    val intent = Intent(it.context, ChooseResourceActivity::class.java)
                    intent.putExtra("modelID", getItem(position)!!.id)
                    ContextCompat.startActivity(it.context, intent, null)
                }
            }
        }
    }


    object ModelComparator: DiffUtil.ItemCallback<ModelData>() {
        override fun areItemsTheSame(oldItem: ModelData, newItem: ModelData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ModelData, newItem: ModelData): Boolean {
            return oldItem == newItem
        }
    }
}