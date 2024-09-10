package com.lemu.pay.checkout.data.api

import android.text.TextUtils
import android.util.Log
import com.lemu.pay.checkout.data.local.PreferencesHelper
import okhttp3.Interceptor
import okhttp3.Response


class TenantInterceptor (val preferencesHelper: PreferencesHelper) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader(TENANT_HEADER, TENANT)
        request.addHeader(CONTENT_TYPE, JSON_CONTENT)
        if (!TextUtils.isEmpty(preferencesHelper.token)) {
            request.addHeader(AUTHORIZATION_HEADER, AUTHORIZATION_PREFIX + preferencesHelper.token.toString())
        }
        val result = request.build()
        Log.d("INTERCEPTOR HEADERS",result.headers.toString())
        return chain.proceed(result)
    }

    companion object{
        const val TENANT_HEADER = "Lemu-Platform-TenantId"
        const val TENANT = "lemu"
        const val AUTHORIZATION_HEADER = "Authorization"
        const val AUTHORIZATION_PREFIX = "Basic "
        const val CONTENT_TYPE = "Content-Type"
        const val JSON_CONTENT = "application/json"
    }
}