package com.ink1804.dev.undefinedapp.config

import com.ink1804.dev.common.network.CommonNetworkConfig

class CommonNetworkConfigImpl : CommonNetworkConfig {
    override val gptApiBaseUrl: String = "https://api.openai.com/v1/"
    override val gptApiKey: String = "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"

    override val appApiBaseUrl: String = "https://appapi.com"
}