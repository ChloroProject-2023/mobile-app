package vn.edu.usth.mobile_app

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import vn.edu.usth.mobile_app.ui.LoginActivity


class MainActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.materialToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navLogin -> {
                    val loginActivity = Intent(this, LoginActivity::class.java)
                    startActivity(loginActivity)
                    true
                }
                R.id.navHistory -> {
                    drawerLayout.close()
                    true
                }
                R.id.navSettings -> {
                    drawerLayout.close()
                    true
                }
                else -> false
            }
        }

        val modelList = resources.getStringArray(R.array.model_list)
        val modelAdapter = ArrayAdapter(this, R.layout.simple_dropdown_item, modelList)
        val autoCompleteModel = findViewById<AutoCompleteTextView>(R.id.autoCompleteModel)
        autoCompleteModel.setAdapter(modelAdapter)

        val fileChoose = findViewById<AutoCompleteTextView>(R.id.autoCompleteFilePath)
        val getFile = getFileLauncher(fileChoose)
        fileChoose.setOnClickListener { getFile.launch("application/sed") }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
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