package vn.edu.usth.mobile_app.ui.admin

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.ItemListUserBinding
import vn.edu.usth.mobile_app.model.UserData
import vn.edu.usth.mobile_app.util.AsyncCell
import java.text.DateFormat

class UserListAsyncAdapter(
    private val userList: List<UserData>
): RecyclerView.Adapter<UserListAsyncAdapter.ViewHolder>()  {

    class ViewHolder(view: ViewGroup) : RecyclerView.ViewHolder(view){}

    class UserListAsyncCell(context: Context): AsyncCell(context, R.layout.item_list_user) {
        lateinit var binding: ItemListUserBinding

        override fun createDataBindingView(view: View): View {
            binding = ItemListUserBinding.bind(view)
            return binding.root
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val frame = UserListAsyncCell(parent.context)
        frame.inflate()
        return ViewHolder(frame)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cell = holder.itemView as UserListAsyncCell

        cell.bindWhenInflated {
            cell.binding.textViewUserListUsername.text = userList[position].username
            cell.binding.textViewUserListEmail.text = userList[position].email
            cell.binding.textViewUserListRoleValue.text = userList[position].role.toString()
            val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)
            val dateString = dateFormat.format(userList[position].createdAt)
            cell.binding.textViewUserListCreatedday.text = dateString
        }
    }

}