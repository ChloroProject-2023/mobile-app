package vn.edu.usth.mobile_app.ui

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import vn.edu.usth.mobile_app.R
import vn.edu.usth.mobile_app.databinding.PopupUploadFileBinding
import java.io.File

class PopupUpload : AppCompatActivity() {
    private lateinit var _binding: PopupUploadFileBinding
    private val viewModel: PopupUploadViewModel by viewModels()
    private val binding get() = _binding

    private val getFileLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri == null) {
            return@registerForActivityResult
        }
        val fileMIMEType = contentResolver.getType(uri)
        Log.d("FILE", fileMIMEType!!)
        val fileType = fileMIMEType.split("/")[0]
        val fileExtension = fileMIMEType.split("/")[1]
        val inputStream = contentResolver.openInputStream(uri)
        val byteArray = inputStream?.readBytes()
        val file = File.createTempFile(fileType, ".${fileExtension}", cacheDir)
        file.writeBytes(byteArray!!)
        Log.d("FILE", file.name)
        viewModel.file = file
        viewModel.uploadModel()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra("type")) {
            viewModel.fileType = intent.getStringExtra("type")!!
        }

        _binding = PopupUploadFileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels
        window.setLayout(width, height)

        if (viewModel.fileType == "json") {
//            binding.textViewPopupUploadGuidelineContent.text =
        }
        else
            binding.textViewPopupUploadGuidelineContent.text = getString(R.string.python_guidelines)

        binding.buttonCancel.setOnClickListener {
            finish()
        }

        val modelName = binding.editTextPopupUploadName
        val modelDescription = binding.editTextPopupUploadDescription
        val modelType = binding.editTextPopupUploadType

        binding.buttonUpload.setOnClickListener {
            if (modelName.text.toString().isEmpty()) {
                modelName.error = "Model name is required"
                return@setOnClickListener
            }
            if (modelDescription.text.toString().isEmpty()) {
                modelDescription.error = "Model description is required"
                return@setOnClickListener
            }
            if (modelType.text.toString().isEmpty()) {
                modelType.error = "Model type is required"
                return@setOnClickListener
            }
            viewModel.modelName = modelName.text.toString()
            viewModel.description = modelDescription.text.toString()
            viewModel.modelType = modelType.text.toString()
            if (viewModel.fileType == "json") {
                getFileLauncher.launch("application/json")
            } else if (viewModel.fileType == "python") {
                getFileLauncher.launch("text/x-python")
            }
        }
    }
}
