package vn.edu.usth.mobile_app.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.*
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.statement.bodyAsText
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import vn.edu.usth.mobile_app.model.*
import vn.edu.usth.mobile_app.model.remote.*
import vn.edu.usth.mobile_app.ui.GlobalData
import java.io.File
import java.util.Base64
import java.util.concurrent.TimeUnit

object KtorClient {
    private val client = HttpClient(OkHttp) {
        defaultRequest {
            url {
                host = "146.190.100.81"
                port = 8081
            }
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }

        engine {
            config {
                connectTimeout(100, TimeUnit.SECONDS)
            }
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }

        install(Logging) {
            logger = Logger.SIMPLE
        }
    }

    /**
     * Login to the server
     * @return true if response code = 200, false otherwise
     */
    suspend fun login(username: String, password: String): Boolean {
        val base64 = Base64.getEncoder().encode("$username:$password".toByteArray((Charsets.UTF_8)))

        val response = client.post {
            url {
                port = 8080
                encodedPath = "jwt"
            }
            headers {
                append(HttpHeaders.Authorization, "Basic ${String(base64)}")
                append(HttpHeaders.Accept, "*/*")
            }
        }
        val responseCode = response.status.value
        if (responseCode == 200) {
            GlobalData.token = response.body<String>()
            val userdata = getProfile(username)
            GlobalData.isLogin = true
            GlobalData.userId = userdata.id
            GlobalData.isAdmin = userdata.role == UserRoles.ADMIN
            return true
        }
        return false
    }

    /**
     * Signup then login to the server
     * @return true if response code = 200, false otherwise
     */
    suspend fun signup(
        username: String,
        password: String,
        firstname: String,
        lastname: String,
    ): Boolean {
        val response = client.post {
            url {
                encodedPath = "users/signup"
            }
            setBody(
                RemoteUser(
                    username,
                    password,
                    RemoteUser.RemoteUserDetails(firstname, lastname, "")
                )
            )
        }
        val responseCode = response.status.value
        if (responseCode == 200) {
            return login(username, password)
        }
        return false
    }

    /**
     * Get profile of a user
     */
    suspend fun getProfile(username: String): UserData {
        val response =  client.get {
            url {
                encodedPath = "users/profile"
                parameter("username", username)
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }
        return response.body<UserData>()
    }

    suspend fun createInference(
        userId: Int,
        modelId: Int,
        resourceId: Int): String {
        val response = client.post {
            url {
                encodedPath = "inferences/create"
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
            setBody(
                RemoteHistory(
                    userId = userId,
                    modelId = modelId,
                    resourceId = resourceId
                )
            )
        }
        return response.bodyAsText()
    }

    // Model
    suspend fun getModel(modelId: Int): ModelData {
        val response = client.get {
            url {
                encodedPath = "models/$modelId"
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }
        return response.body<RemoteModel>().toModelData()
    }

    suspend fun getModelPage(page: Int, pageSize: Int = 10): List<ModelData> {
        val response = client.get {
            url {
                encodedPath = "models/paging"
                parameter("pageNo", page.toString())
                parameter("pageSize", pageSize.toString())
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }
        val remoteModelList = response.body<List<RemoteModel>>()
        Log.d("KtorClient.getModelPage", page.toString())
        Log.d("KtorClient.getModelPage", remoteModelList.toString())
        return remoteModelList.map { it.toModelData() }
    }

    suspend fun getModelsCount(): Int {
        val response = client.get {
            url {
                encodedPath = "models/count"
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }
        return response.body<Int>()
    }

    suspend fun deleteModel(modelId: Int): Boolean {
        val response = client.delete {
            url {
                encodedPath = "models/delete/$modelId"
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }
        return response.status.value == 200
    }

    suspend fun getModelsByUser(userId: Int): List<ModelData> {
        val response = client.get {
            url {
                encodedPath = "models/models-by-user/$userId"
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }
        val remoteModelList = response.body<List<RemoteModel>>()
        return remoteModelList.map { it.toModelData() }
    }

    suspend fun searchModels(query: String): List<ModelData> {
        val response = client.get {
            url {
                encodedPath = "models/search/$query"
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }
        val remoteModelList = response.body<List<RemoteModel>>()
        return remoteModelList.map { it.toModelData() }
    }

    suspend fun top10MostUsedModels(): List<ModelData> {
        val response = client.get {
            url {
                encodedPath = "models/top10-most-used"
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }
        val remoteModelList = response.body<List<RemoteModel>>()
        return remoteModelList.map { it.toModelData() }
    }

    suspend fun top10RecentlyUsed(): List<ModelData> {
        val response = client.get {
            url {
                encodedPath = "models/top10-recently-used"
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }
        val remoteModelList = response.body<List<RemoteModel>>()
        return remoteModelList.map { it.toModelData() }
    }

    suspend fun getUserReviews(userId: Int): List<ReviewData> {
        val response = client.get {
            url {
                encodedPath = "users/ratings-by-user_id/$userId"
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }
        val remoteReviewList = response.body<List<RemoteReview>>()
        return remoteReviewList.map { it.toReviewData() }
    }

    suspend fun getModelReviews(modelId: Int): List<ReviewData> {
        val response = client.get {
            url {
                encodedPath = "models/ratings-by-model_id/$modelId"
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }
        val remoteReviewList = response.body<List<RemoteReview>>()
        return remoteReviewList.map { it.toReviewData() }
    }

    // User
    suspend fun getUserById(userId: Int): UserData {
        val response = client.get {
            url {
                encodedPath = "users/$userId"
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }
        return response.body<UserData>()
    }

    suspend fun getUserByUsername(username: String): UserData {
        val response = client.get {
            url {
                encodedPath = "users/profile"
                parameter("username", username)
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }
        return response.body<UserData>()
    }

    suspend fun deleteUserById(userId: Int): Boolean {
        val response = client.delete {
            url {
                encodedPath = "users/delete/$userId"
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }
        return response.status.value == 200
    }

    suspend fun deleteUserByUsername(username: String): Boolean {
        val response = client.delete {
            url {
                encodedPath = "users/delete"
                parameter("username", username)
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }
        return response.status.value == 200
    }

    suspend fun getUserCount(): Int {
        val response = client.get {
            url {
                encodedPath = "users/count"
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }
        return response.body<Int>()
    }

    // Resource
    suspend fun getUserResources(userId: Int): List<ResourcesData> {
        val response = client.get {
            url {
                encodedPath = "resources/user_id/$userId"
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }

        return response.body<List<RemoteResources>>().map { it.toResourceData() }
    }

    suspend fun getResourceById(resourceId: Int): ResourcesData {
        val response = client.get {
            url {
                encodedPath = "resources/id/$resourceId"
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }
        return response.body<RemoteResources>().toResourceData()
    }

    suspend fun uploadResource(userId: Int,  file: File): String {
        val response = client.post {
            url {
                encodedPath = "resources/create"
                parameter("user_id", userId)
                parameter("type", "datafiles")
            }
            headers {
                append(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
                append(HttpHeaders.ContentType, ContentType.MultiPart.FormData)
            }
            setBody(
                MultiPartFormDataContent(formData {
                    append("file", file.readBytes(), Headers.build {
                        append(HttpHeaders.ContentDisposition, "filename=${file.name}")
                    })
                })
            )
        }
        return response.bodyAsText()
    }

    suspend fun deleteResource(resourceId: Int): Boolean {
        val response = client.delete {
            url {
                encodedPath = "resources/delete/$resourceId"
            }
            header(HttpHeaders.Authorization, "Bearer ${GlobalData.token}")
        }
        return response.status.value == 200
    }
}