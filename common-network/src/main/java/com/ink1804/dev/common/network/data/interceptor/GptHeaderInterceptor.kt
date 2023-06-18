package com.ink1804.dev.common.network.data.interceptor

import com.ink1804.dev.common.network.CommonNetworkConfig
import javax.inject.Inject
import okhttp3.Interceptor.Chain
import okhttp3.Response

internal class GptHeaderInterceptor @Inject constructor(
    private val commonNetworkConfig: CommonNetworkConfig
) : HeaderInterceptor {
    override fun intercept(chain: Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader(AUTH_HEADER, "$AUTH_HEADER_PREFIX ${commonNetworkConfig.gptApiKey}")
        return chain.proceed(builder.build())
    }

    companion object {
        private const val AUTH_HEADER = "Authorization"
        private const val AUTH_HEADER_PREFIX = "Beaver"
    }
}