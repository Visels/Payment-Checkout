package com.lemu.pay.checkout.data.api

import com.lemu.pay.checkout.data.local.PreferencesHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

class SelfOkhttpClient(
    val preferencesHelper: PreferencesHelper
) {

    val client: OkHttpClient
        get() {
            val builder:OkHttpClient.Builder = OkHttpClient.Builder()
            builder.addInterceptor(TenantInterceptor(preferencesHelper = preferencesHelper ))

            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logger)
            return builder.build()
        }
}