package vn.edu.usth.mobile_app.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.net.URL

class KtorClient {
    private val client = HttpClient(OkHttp) {
        defaultRequest {
            url { "http://146.190.100.81:8081/" }
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

    suspend fun login(username: String, password: String): HttpResponse {
        return client.post {
            url(URL("http://146.190.100.81:8080/jwt"))
            method = HttpMethod.Post
            setBody(
                Json {
                    "username" to username
                    "password" to password
                })
        }
    }
}