package com.dimorinny.vscale.api

import android.content.Context
import com.dimorinny.vscale.db.entity.ServerEntity
import com.squareup.okhttp.OkHttpClient
import retrofit.RestAdapter
import retrofit.android.AndroidLog
import retrofit.client.OkClient
import rx.Observable

/**
 * Created by Dimorinny on 07.01.16.
 */
class ApiVscale(private val context: Context) {

    companion object {
        private val BASE_URL = "http://192.168.1.9:8000"
    }

    private val restAdapter: RestAdapter = initRestAdapter()
    private val restInterface : VscaleInterface = restAdapter.create(VscaleInterface::class.java)

    private fun initRestAdapter() : RestAdapter {
        val okHttp = OkHttpClient()

        return RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setClient(OkClient(okHttp))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(AndroidLog("RETROFIT"))
                .build()
    }

    fun getServers(): Observable<List<ServerEntity>> {
        return restInterface.getServers()
    }

    fun getServer(): Observable<ServerEntity> {
        return restInterface.getServer()
    }
}