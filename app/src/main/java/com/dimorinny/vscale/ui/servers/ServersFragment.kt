package com.dimorinny.vscale.ui.servers

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimorinny.vscale.App
import com.dimorinny.vscale.R
import com.dimorinny.vscale.db.DataManager
import com.dimorinny.vscale.db.entity.ServerEntity
import com.dimorinny.vscale.dependency.bindView
import com.dimorinny.vscale.event.server.LoadServersResponse
import com.dimorinny.vscale.service.ServiceManager
import com.squareup.otto.Bus
import com.squareup.otto.Subscribe
import com.trello.rxlifecycle.components.support.RxFragment
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by Dimorinny on 31.12.15.
 */

public class ServersFragment : RxFragment() {

    @Inject
    lateinit var serviceManager: ServiceManager

    @Inject
    lateinit var dataManager : DataManager

    @Inject
    lateinit var bus : Bus

    val serversRecyclerView : RecyclerView by bindView(R.id.servers_list)
    val serversAdapter : ServersAdapter = ServersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.graph.inject(this)
        bus.register(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_servers, container, false);
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView();
        loadDataFromCache();
        serviceManager.loadServers()
    }

    private fun initRecyclerView() {
        serversRecyclerView.layoutManager = LinearLayoutManager(activity)
        serversRecyclerView.adapter = serversAdapter
    }

    private fun loadDataFromCache() {
        dataManager.getServersObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle<List<ServerEntity>>())
                .subscribe(object : Subscriber<List<ServerEntity>>() {
                    override fun onError(e: Throwable) { e.printStackTrace() }

                    override fun onCompleted() {}

                    override fun onNext(t: List<ServerEntity>) {
                        serversAdapter.servers = t
                    }
                })
    }

    // Callbacks

    @Subscribe
    fun answerAvailable(response: LoadServersResponse) {
        // TODO: fix
        Log.v("servers load", "ok")
    }
}