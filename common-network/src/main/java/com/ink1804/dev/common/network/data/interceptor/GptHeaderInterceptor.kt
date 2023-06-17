package com.ink1804.dev.common.network.data.interceptor

import javax.inject.Inject
import okhttp3.Interceptor.Chain
import okhttp3.Response

internal class GptHeaderInterceptor @Inject constructor() : HeaderInterceptor {
    override fun intercept(chain: Chain): Response {
        val builder = chain.request().newBuilder()
        //todo extract to buildConfig
        builder.addHeader("Authorization", "Bearer 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")
        return chain.proceed(builder.build())
    }
}