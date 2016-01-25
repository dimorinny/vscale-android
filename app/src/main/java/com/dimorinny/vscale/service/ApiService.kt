package com.dimorinny.vscale.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.dimorinny.vscale.App
import com.dimorinny.vscale.db.entity.ServerEntity
import com.dimorinny.vscale.event.server.LoadServerResponse
import com.dimorinny.vscale.event.server.LoadServersResponse
import com.dimorinny.vscale.rx.RxBus
import com.dimorinny.vscale.usecase.LoadServerUseCase
import com.dimorinny.vscale.usecase.LoadServersUseCase
import rx.Subscriber
import rx.Subscription
import java.util.*
import javax.inject.Inject

/**
 * Created by Dimorinny on 05.01.16.
 */
class ApiService : Service() {

    @Inject
    lateinit var serversUseCase: LoadServersUseCase

    @Inject
    lateinit var serverUseCase: LoadServerUseCase

    @Inject
    lateinit var bus: RxBus

    private var subscriptions: List<Subscription> = ArrayList()

    companion object {
        // Args
        const val ARG_SERVICE_COMMAND = "arg_service_command"
        const val ARG_SERVER_ID = "arg_server_id"

        // Commands
        const val LOAD_SERVERS_COMMAND = "load_servers_command"
        const val LOAD_SERVER_COMMAND = "load_server_command"
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
        if (intent == null) {
            return START_NOT_STICKY
        }

        val command = intent.getStringExtra(ARG_SERVICE_COMMAND)

        when (command) {
            LOAD_SERVERS_COMMAND -> {
                subscriptions +=
                        serversUseCase.loadServers().subscribe(ServersSubscriber(startId))
            }

            LOAD_SERVER_COMMAND -> {
                val id = intent.getIntExtra(ARG_SERVER_ID, 0)
                subscriptions +=
                        serverUseCase.loadServer(id).subscribe(ServerSubscriber(startId))
            }
        }

        return START_NOT_STICKY
    }

    // Subscribers

    inner class ServersSubscriber(val startId: Int) : Subscriber<List<ServerEntity>>() {
        override fun onCompleted() {}

        override fun onError(e: Throwable) {
            bus.post(LoadServersResponse(false, e))
            stopSelf(startId)
        }

        override fun onNext(t: List<ServerEntity>) {
            bus.post(LoadServersResponse(true, null))
            stopSelf(startId)
        }
    }

    inner class ServerSubscriber(val startId: Int) : Subscriber<ServerEntity>() {
        override fun onCompleted() {}

        override fun onError(e: Throwable) {
            bus.post(LoadServerResponse(false, e))
            stopSelf(startId)
        }

        override fun onNext(t: ServerEntity) {
            bus.post(LoadServerResponse(true, null))
            stopSelf(startId)
        }
    }
}