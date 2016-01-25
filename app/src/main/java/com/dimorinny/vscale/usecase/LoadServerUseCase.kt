package com.dimorinny.vscale.usecase

import com.dimorinny.vscale.api.ApiVscale
import com.dimorinny.vscale.db.DataManager
import com.dimorinny.vscale.db.entity.ServerEntity
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Dimorinny on 12.01.16.
 */
class LoadServerUseCase(val dataManager: DataManager, val api: ApiVscale) {

    fun loadServer(id: Int): Observable<ServerEntity> {
        return api.getServer()
                .doOnNext {
                    dataManager.putServer(it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}