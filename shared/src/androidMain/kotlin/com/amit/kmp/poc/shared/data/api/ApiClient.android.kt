package com.amit.kmp.poc.shared.data.api

import io.ktor.client.HttpClientConfig
import testbench.client.TestBenchClient
import testbench.plugins.network.KtorNetworkClientPlugin

actual fun config(httpClientConfig: HttpClientConfig<*>) {

    val networkPlugin = KtorNetworkClientPlugin()


    TestBenchClient(
        plugins = listOf(networkPlugin)
    )

    networkPlugin.install(httpClientConfig)

}