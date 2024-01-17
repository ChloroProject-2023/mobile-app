package vn.edu.usth.mobile_app.ui.admin

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.ItemListUserBinding
import vn.edu.usth.mobile_app.model.UserData
import vn.edu.usth.mobile_app.util.AsyncCell
import java.text.DateFormat

class UserListAsyncAdapter(
    private val userList: MutableList<UserData>
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

            cell.binding.imageViewUserListMoreVert.setOnClickListener {
                val bottomSheet = BottomSheetDialog(context)
                val view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_dialog, this, false)
                bottomSheet.setContentView(view)
                bottomSheet.show()

                view.findViewById<View>(R.id.m3button_delete).setOnClickListener {
                    MaterialAlertDialogBuilder(context)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete this user?")
                        .setNegativeButton("Cancel") { dialog, _ ->
                            dialog.dismiss()

                        }
                        .setPositiveButton("Delete") { _, _ ->
                            userList.removeAt(position)
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, userList.size)
                            Log.d("UserListAsyncAdapter", "Delete user ${userList[position].username}")
                            bottomSheet.dismiss()
                        }
                        .show()
                }
            }
        }
    }

}