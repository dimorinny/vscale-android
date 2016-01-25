package com.dimorinny.vscale.usecase

import com.dimorinny.vscale.api.ApiVscale
import com.dimorinny.vscale.db.DataManager
import com.dimorinny.vscale.db.entity.ServerEntity
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Dimorinny on 05.01.16.
 */
class LoadServersUseCase(val dataManager: DataManager, val api: ApiVscale) {

    fun loadServers(): Observable<List<ServerEntity>> {
        return api.getServers()
                .doOnNext {
                    dataManager.clearServers()
                    dataManager.putServers(it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}