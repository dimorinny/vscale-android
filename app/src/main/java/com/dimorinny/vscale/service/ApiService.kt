package com.dimorinny.vscale.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.dimorinny.vscale.App
import com.dimorinny.vscale.db.entity.ServerEntity
import com.dimorinny.vscale.event.server.LoadServersResponse
import com.dimorinny.vscale.usecase.LoadServersUseCase
import com.squareup.otto.Bus
import rx.Subscriber
import rx.Subscription
import java.util.*
import javax.inject.Inject

/**
 * Created by Dimorinny on 05.01.16.
 */
class ApiService : Service() {

    @Inject
    lateinit var serversUseCase : LoadServersUseCase

    @Inject
    lateinit var bus : Bus

    private var subscriptions : List<Subscription> = ArrayList()

    companion object {
        const val ARG_SERVICE_COMMAND = "arg_service_command"
        const val LOAD_SERVERS_COMMAND = "load_servers_command"
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        App.graph.inject(this)
    }

    override fun onDestroy() {
        for (subscription in subscriptions) {
            subscription.unsubscribe()
        }
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val command = intent?.getStringExtra(ARG_SERVICE_COMMAND)

        if (command == LOAD_SERVERS_COMMAND) {
            subscriptions +=
                    serversUseCase.loadServers().subscribe(ServersSubscriber(startId))
        }

        return super.onStartCommand(intent, flags, startId)
    }

    // Subscribers

    inner class ServersSubscriber(val startId: Int) : Subscriber<List<ServerEntity>>() {
        override fun onCompleted() {}

        override fun onError(e: Throwable) {
            e.printStackTrace()
        }

        override fun onNext(t: List<ServerEntity>) {
            bus.post(LoadServersResponse(true, null))
            stopSelf(startId)
        }
    }
}