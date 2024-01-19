package vn.edu.usth.mobile_app.ui.usermenu.uploadedmodels

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.ActivityRecyclerViewBinding
import vn.edu.usth.mobile_app.model.ModelData
import vn.edu.usth.mobile_app.ui.PopupUpload

class UploadedModelsActivity: AppCompatActivity() {
    private lateinit var _binding: ActivityRecyclerViewBinding
    private  val viewModel: UploadedModelsViewModel by viewModels()
    private val binding get() = _binding
    private lateinit var uploadedModelsAsyncRecyclerAdapter: UploadedModelsAsyncAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFab()

        val toolbar = binding.materialToolbarRVActivity
        toolbar.setNavigationOnClickListener{
            finish()
        }
        toolbar.title = getString(R.string.Uploaded_Models)

        uploadedModelsAsyncRecyclerAdapter = UploadedModelsAsyncAdapter()
        val listObserver = Observer<ArrayList<ModelData>> {
            uploadedModelsAsyncRecyclerAdapter.updateList(it)
        }
        viewModel.uploadedModelsList.observe(this, listObserver)

        val recyclerView = binding.recyclerViewRVActivity
        recyclerView.apply {
            adapter = uploadedModelsAsyncRecyclerAdapter
            layoutManager = LinearLayoutManager(this@UploadedModelsActivity)
            setHasFixedSize(true)
            val dividerItemDecoration = DividerItemDecoration(this@UploadedModelsActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun setupFab() {
        val fab = ExtendedFloatingActionButton(this)
        fab.text = getString(R.string.Upload)
        fab.icon = AppCompatResources.getDrawable(this, R.drawable.baseline_add_24)
        val params = CoordinatorLayout.LayoutParams(
            CoordinatorLayout.LayoutParams.WRAP_CONTENT,
            CoordinatorLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.BOTTOM or Gravity.END
            rightMargin = dpToPx(16)
            bottomMargin = dpToPx(32)
        }

        val popupMenu = PopupMenu(this, fab)
        popupMenu.menuInflater.inflate(R.menu.model_type_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.type_json -> {
                    val intent = Intent(this, PopupUpload::class.java)
                    intent.putExtra("type", "json")
                    true
                }
                R.id.type_python -> {
                    val intent = Intent(this, PopupUpload::class.java)
                    intent.putExtra("type", "python")
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        fab.setOnClickListener {
            popupMenu.show()
        }

        binding.root.addView(fab, params)
    }

    private fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            resources.displayMetrics
        ).toInt()
    }
}