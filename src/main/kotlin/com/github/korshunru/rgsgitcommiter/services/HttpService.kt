package com.github.korshunru.rgsgitcommiter.services

import com.fasterxml.jackson.databind.DeserializationFeature
import com.intellij.openapi.components.Service
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.serialization.jackson.*
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import okhttp3.OkHttpClient

@Service(Service.Level.PROJECT)
class HttpService {

    fun getHttpClient(): HttpClient =
        HttpClient(OkHttp) {
            install(HttpCookies) { storage = AcceptAllCookiesStorage() }
            install(ContentNegotiation) {
                jackson {
                    this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                }
            }

            engine {
                config { configureSSL(this) }
                addInterceptor { it.proceed(it.request()) }
            }
        }

    private fun configureSSL(builder: OkHttpClient.Builder) {
        val sslContext = SSLContext.getInstance("SSL")
        val trustAllCerts = arrayOf<TrustManager>(
            object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            }
        )

        sslContext.init(null, trustAllCerts, SecureRandom())

        builder.sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier { _, _ -> true }
    }
}