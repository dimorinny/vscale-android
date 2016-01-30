package com.dimorinny.vscale

import android.app.Application
import com.squareup.leakcanary.LeakCanary

/**
 * Created by Dimorinny on 01.01.16.
 */
class App : Application() {

    companion object {
        lateinit var graph: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
        LeakCanary.install(this);
    }

    private fun initAppComponent() {
        graph = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}