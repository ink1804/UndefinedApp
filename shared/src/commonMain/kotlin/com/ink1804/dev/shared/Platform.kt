package com.ink1804.dev.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform