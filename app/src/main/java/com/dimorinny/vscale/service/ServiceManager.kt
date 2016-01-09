package com.dimorinny.vscale.service

import android.content.Context
import android.content.Intent

/**
 * Created by Dimorinny on 05.01.16.
 */
class ServiceManager(private val context : Context) {
    fun loadServers() {
        val intent = Intent(context, ApiService::class.java)
        intent.putExtra(ApiService.ARG_SERVICE_COMMAND, ApiService.LOAD_SERVERS_COMMAND)
        context.startService(intent)
    }
}