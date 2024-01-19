package vn.edu.usth.mobile_app.ui.usermenu.resourcelist

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.coroutines.launch
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.ActivityRecyclerViewBinding
import vn.edu.usth.mobile_app.model.ResourcesData
import java.io.File

class ResourcesListActivity: AppCompatActivity() {
    private lateinit var resourceAsyncRecyclerAdapter: ResourceAsyncRecyclerAdapter
    private lateinit var binding: ActivityRecyclerViewBinding
    private val viewModel: ResourceViewModel by viewModels()

    private val getFileLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri == null) {
            return@registerForActivityResult
        }
        val fileMIMEType = contentResolver.getType(uri)
        Log.d("FILE", fileMIMEType!!)
        val fileType = fileMIMEType.split("/")[0]
        val fileExtension = "csv"
        val inputStream = contentResolver.openInputStream(uri)
        val byteArray = inputStream?.readBytes()
        val file = File.createTempFile(fileType, ".${fileExtension}", cacheDir)
        file.writeBytes(byteArray!!)
        Log.d("FILE", file.name)
        viewModel.viewModelScope.launch {
            val waitingDialog = MaterialAlertDialogBuilder(this@ResourcesListActivity)
                .setTitle(getString(R.string.Upload))
                .setMessage(getString(R.string.Uploading))
                .show()
            val responseCode = viewModel.uploadResource(file)


            if (responseCode == 201) {
                waitingDialog.dismiss()
                viewModel.fetchResourceList()
                MaterialAlertDialogBuilder(this@ResourcesListActivity)
                    .setTitle(getString(R.string.Upload))
                    .setMessage(getString(R.string.UploadSuccessful))
                    .setNegativeButton(getString(R.string.OK)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            } else {
                waitingDialog.dismiss()
                MaterialAlertDialogBuilder(this@ResourcesListActivity)
                    .setTitle(getString(R.string.Upload))
                    .setMessage(getString(R.string.UploadFailed))
                    .setNegativeButton(getString(R.string.OK)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fab = ExtendedFloatingActionButton(this)
        fab.text = getString(R.string.Upload)
        fab.icon = AppCompatResources.getDrawable(this, R.drawable.baseline_add_24)
        fab.setOnClickListener {
            getFileLauncher.launch("text/comma-separated-values")
        }

        val params = CoordinatorLayout.LayoutParams(
            CoordinatorLayout.LayoutParams.WRAP_CONTENT,
            CoordinatorLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.BOTTOM or Gravity.END
            rightMargin = dpToPx(16)
            bottomMargin = dpToPx(32)
        }
        binding.root.addView(fab, params)

        val toolbar = binding.materialToolbarRVActivity
        toolbar.setNavigationOnClickListener{
            finish()
        }
        toolbar.title = getString(R.string.Resources)

        resourceAsyncRecyclerAdapter = ResourceAsyncRecyclerAdapter(viewModel::deleteResource)
        val listObserver = androidx.lifecycle.Observer<ArrayList<ResourcesData>> {
            resourceAsyncRecyclerAdapter.updateList(it)
        }
        viewModel.resourceList.observe(this, listObserver)

        val recyclerView = binding.recyclerViewRVActivity
        recyclerView.adapter = resourceAsyncRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            resources.displayMetrics
        ).toInt()
    }
}