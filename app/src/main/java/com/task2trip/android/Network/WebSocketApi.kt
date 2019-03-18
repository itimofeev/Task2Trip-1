package com.task2trip.android.Network

import android.os.Handler
import com.task2trip.android.Common.ServerConstants
import okhttp3.OkHttpClient
import okhttp3.Request

class WebSocketApi {
    private val WEBSOCKET_ADDRESS = ServerConstants.PROTOCOL_SOCKET
        .plus(ServerConstants.API_ADDRESS_DOMAIN)
        .plus("/websocket/")
        .plus(ServerConstants.API_VERSION)
        .plus("/")

    init {
        val request = Request.Builder().url(WEBSOCKET_ADDRESS).build()
        val listener = EchoWebSocketListener()
        val okHttpClient = OkHttpClient()
        val webSocket = okHttpClient.newWebSocket(request, listener)
        okHttpClient.dispatcher().executorService().shutdown()

        val pingHandler = Handler()
        val pingRunnable = object : Runnable {
            override fun run() {
                val ping = "{\"type\":\"ping\",\"message\":\"hello\"}"
                //output("Tx: $ping")
                webSocket.send(ping)
                pingHandler.postDelayed(this, 10000)
            }
        }
        pingHandler.postDelayed(pingRunnable, 10000)
    }
}