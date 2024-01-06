package vn.edu.usth.mobile_app.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import vn.edu.usth.mobile_app.model.*
import vn.edu.usth.mobile_app.model.remote.RemoteReview
import vn.edu.usth.mobile_app.model.remote.RemoteUser
import vn.edu.usth.mobile_app.model.remote.toReviewData
import vn.edu.usth.mobile_app.ui.GlobalData
import java.util.Base64

object KtorClient {
    private val client = HttpClient(OkHttp) {
        defaultRequest {
            url {
                host = "146.190.100.81"
                port = 8081
            }
            header(HttpHeaders.ContentType, ContentType.Application.Json)
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
}