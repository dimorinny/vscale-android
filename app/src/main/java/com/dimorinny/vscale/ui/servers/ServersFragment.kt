package com.dimorinny.vscale.ui.servers

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimorinny.vscale.App
import com.dimorinny.vscale.R
import com.dimorinny.vscale.db.DataManager
import com.dimorinny.vscale.db.entity.ServerEntity
import com.dimorinny.vscale.dependency.bindView
import com.dimorinny.vscale.event.server.LoadServersResponse
import com.dimorinny.vscale.rx.RxBus
import com.dimorinny.vscale.service.ServiceManager
import com.dimorinny.vscale.ui.server.ServerActivity
import com.dimorinny.vscale.ui.server.ServerFragment
import com.trello.rxlifecycle.components.support.RxFragment
import rx.Observable
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
    lateinit var dataManager: DataManager

    @Inject
    lateinit var bus: RxBus

    val serversRecyclerView: RecyclerView by bindView(R.id.servers_list)
    lateinit var serversAdapter: ServersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.graph.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_servers, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        observeServersData(Observable.merge(arrayOf(cacheObservable(), networkObservable())))
        serviceManager.loadServers()
    }

    private fun initRecyclerView() {
        serversAdapter = ServersAdapter(activity)
        serversAdapter.itemClickListener = object : ServersAdapter.OnItemClickListener {
            override fun onItemClicked(index: Int, view: View) {
                val id = serversAdapter.servers[index].ctId
                if (id != null) {
                    startDetailActivity(id)
                }
            }
        }

        serversRecyclerView.layoutManager = LinearLayoutManager(activity)
        serversRecyclerView.adapter = serversAdapter
    }

    private fun cacheObservable(): Observable<List<ServerEntity>> {
        return dataManager.getServersObservable().take(1)
    }

    private fun networkObservable(): Observable<List<ServerEntity>> {
        return bus.events(LoadServersResponse::class.java)
                .flatMap {
                    if (it.ok) {
                        cacheObservable().take(1)
                    } else {
                        Observable.error(it.throwable)
                    }
                }
    }

    private fun observeServersData(observable: Observable<List<ServerEntity>>) {
        observable.compose(bindToLifecycle<List<ServerEntity>>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(LoadServersSubscriber())
    }

    private fun startDetailActivity(index: Int) {
        val intent = Intent(activity, ServerActivity::class.java)
        intent.putExtra(ServerFragment.ARG_SERVER_ID, index)
        startActivity(intent)
    }

    private inner class LoadServersSubscriber : Subscriber<List<ServerEntity>>() {
        override fun onCompleted() {}

        override fun onError(e: Throwable) {
            e.printStackTrace()
        }

        override fun onNext(s: List<ServerEntity>) {
            serversAdapter.servers = s
        }
    }
}
