package vn.edu.usth.mobile_app.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class DataStoreUtil
@Inject
constructor(
    private val dataStore: DataStore<Preferences>,
    private val security: SecurityUtil
) {
    private val securityKeyAlias = "data-store"
    private val bytesToStringSeparator = "|"

    fun getSecuredData(key: Preferences.Key<String>): Flow<String> {
        return dataStore.data
            .secureMap<String> { preferences ->
                preferences[key].orEmpty()
            }
    }

    suspend fun setData(key: Preferences.Key<String>, value: String) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }
    fun getData(key: Preferences.Key<String>): Flow<String> {
        return dataStore.data
            .map { preferences ->
                preferences[key].orEmpty()
            }
    }
    suspend fun setSecuredData(key: Preferences.Key<String>, value: String) {
        dataStore.secureEdit(value) { prefs, encryptedValue ->
            prefs[key] = encryptedValue
        }
    }

    suspend fun hasKey(key: Preferences.Key<*>) = dataStore.edit { it.contains(key) }

    suspend fun clearDataStore() {
        dataStore.edit {
            it.clear()
        }
    }

    private val json = Json { encodeDefaults = true }

    private inline fun <reified T> Flow<Preferences>.secureMap(crossinline fetchValue: (value: Preferences) -> String): Flow<T> {
        return map {
            val decryptedValue = security.decryptData(
                securityKeyAlias,
                fetchValue(it).split(bytesToStringSeparator).map { it.toByte() }.toByteArray()
            )
            json.decodeFromString(decryptedValue)
        }
    }

    private suspend inline fun <reified T> DataStore<Preferences>.secureEdit(
        value: T,
        crossinline editStore: (MutablePreferences, String) -> Unit
    ) {
        edit {
            val encryptedValue = security.encryptData(securityKeyAlias, Json.encodeToString(value))
            editStore.invoke(it, encryptedValue.joinToString(bytesToStringSeparator))
        }
    }

    companion object {
        @Volatile private var instance: DataStoreUtil? = null
        fun getInstance(dataStore: DataStore<Preferences>, security: SecurityUtil): DataStoreUtil {
            return instance ?: synchronized(this) {
                instance ?: DataStoreUtil(dataStore, security).also { instance = it }
            }
        }
    }
}