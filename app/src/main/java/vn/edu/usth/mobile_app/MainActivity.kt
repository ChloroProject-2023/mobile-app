package vn.edu.usth.mobile_app

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val modelList = resources.getStringArray(R.array.model_list)
        val modelAdapter = ArrayAdapter(this, R.layout.simple_dropdown_item, modelList)
        val autoCompleteModel: AutoCompleteTextView = findViewById(R.id.autoCompleteModel)
        autoCompleteModel.setAdapter(modelAdapter)

        val fileChoose: AutoCompleteTextView = findViewById(R.id.autoCompleteFilePath)
        val getFile = getFileLauncher(fileChoose)
        fileChoose.setOnClickListener { getFile.launch("*/*") }
    }

    private fun getFileLauncher(fileChoose: AutoCompleteTextView) =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri == null) {
                return@registerForActivityResult
            }
            val name = queryName(applicationContext.contentResolver, uri)
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