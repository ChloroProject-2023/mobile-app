package vn.edu.usth.mobile_app

import androidx.datastore.preferences.core.stringPreferencesKey

class Constants {

    object DataStore {
        val BASE_URL = stringPreferencesKey("base_url")
        val TOKEN = stringPreferencesKey("token")
    }
}