package com.dimorinny.vscale.ui.server

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimorinny.vscale.App
import com.dimorinny.vscale.R
import com.dimorinny.vscale.db.DataManager
import com.dimorinny.vscale.db.entity.ServerEntity
import com.dimorinny.vscale.event.server.LoadServerResponse
import com.dimorinny.vscale.service.ServiceManager
import com.squareup.otto.Bus
import com.squareup.otto.Subscribe
import com.trello.rxlifecycle.components.support.RxFragment
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by Dimorinny on 12.01.16.
 */
class ServerFragment : RxFragment() {

    @Inject
    lateinit var serviceManager: ServiceManager

    @Inject
    lateinit var dataManager : DataManager

    @Inject
    lateinit var bus : Bus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.graph.inject(this)
        bus.register(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_server, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadDataFromCache();
        serviceManager.loadServer(123)
    }

    private fun loadDataFromCache() {
        // TODO: hardcode
        dataManager.getServerObservable(10139)
            .take(1)
            .observeOn(AndroidSchedulers.mainThread())
            .compose(bindToLifecycle<ServerEntity>())
            .subscribe(object : Subscriber<ServerEntity>() {
                override fun onError(e: Throwable) { e.printStackTrace() }

                override fun onCompleted() {}

                override fun onNext(t: ServerEntity) {
                }
            })
    }

    @Subscribe
    fun answerAvailable(response: LoadServerResponse) {
        loadDataFromCache()
        Log.v("servers load", "ok")
    }

    override fun onDestroy() {
        bus.unregister(this)
        super.onDestroy()
    }
}