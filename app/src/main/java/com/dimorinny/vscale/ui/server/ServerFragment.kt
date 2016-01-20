package com.dimorinny.vscale.ui.server

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dimorinny.vscale.App
import com.dimorinny.vscale.R
import com.dimorinny.vscale.db.DataManager
import com.dimorinny.vscale.db.entity.ServerEntity
import com.dimorinny.vscale.dependency.bindView
import com.dimorinny.vscale.event.server.LoadServerResponse
import com.dimorinny.vscale.service.ServiceManager
import com.dimorinny.vscale.util.ImageUtils
import com.dimorinny.vscale.util.StatusUtils
import com.squareup.otto.Bus
import com.squareup.otto.Subscribe
import com.trello.rxlifecycle.components.support.RxFragment
import de.hdodenhof.circleimageview.CircleImageView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by Dimorinny on 12.01.16.
 */
class ServerFragment : RxFragment() {

    companion object {
        val ARG_SERVER_ID = "arg_server_id"
    }

    @Inject
    lateinit var serviceManager: ServiceManager

    @Inject
    lateinit var dataManager : DataManager

    @Inject
    lateinit var bus : Bus

    val serverName : TextView by bindView(R.id.server_name)
    val hostName : TextView by bindView(R.id.server_hostname)
    val location : TextView by bindView(R.id.server_location)
    val status : TextView by bindView(R.id.server_status)
    val image : CircleImageView by bindView(R.id.server_image)

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

    private fun setData(server: ServerEntity) {
        val drawable = ImageUtils.imageByMadeFrom(server.madeFrom)

        image.setImageDrawable(context.getDrawable(drawable))
        hostName.text = server.hostName
        serverName.text = server.name
        location.text = server.locations
        status.text = context.getString(StatusUtils.getStatus(server.status))
    }

    private fun loadDataFromCache() {
        dataManager.getServerObservable(arguments.getInt(ARG_SERVER_ID))
            .take(1)
            .observeOn(AndroidSchedulers.mainThread())
            .compose(bindToLifecycle<ServerEntity>())
            .subscribe(object : Subscriber<ServerEntity>() {
                override fun onError(e: Throwable) { e.printStackTrace() }

                override fun onCompleted() {}

                override fun onNext(t: ServerEntity) {
                    setData(t)
                }
            })
    }

    @Subscribe
    fun answerAvailable(response: LoadServerResponse) {
        loadDataFromCache()
    }

    override fun onDestroy() {
        bus.unregister(this)
        super.onDestroy()
    }
}