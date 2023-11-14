package vn.edu.usth.mobile_app.ui

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import vn.edu.usth.mobile_app.R

class ExploreFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_upload, container, false)

        val modelList = listOf("Model 1", "Model 2", "Model 3", "Model 4", "Model 5")
        val modelAdapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item, modelList)
        val autoCompleteModel = view.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView_upload_model)
        autoCompleteModel.setAdapter(modelAdapter)

        val fileChoose = view.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView_upload_filePath)
        val getFile = getFileLauncher(fileChoose)
        fileChoose.setOnClickListener { getFile.launch("application/sed") }

        return view
    }

    private fun getFileLauncher(fileChoose: AutoCompleteTextView) =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri == null) {
                return@registerForActivityResult
            }
            val name = queryName(requireContext().applicationContext.contentResolver, uri)
            fileChoose.setText(name)
        }

    private fun queryName(resolver: ContentResolver, uri: Uri): String? {
        val returnCursor = resolver.query(uri, null, null, null, null)!!
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        val name = returnCursor.getString(nameIndex)
        returnCursor.close()
        return name
    }
}