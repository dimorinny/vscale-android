package com.dimorinny.vscale.api

import com.dimorinny.vscale.db.entity.ServerEntity
import retrofit.http.GET
import rx.Observable

/**
 * Created by Dimorinny on 07.01.16.
 */
interface VscaleInterface {
    @GET("/")
    fun getServers(): Observable<List<ServerEntity>>
}