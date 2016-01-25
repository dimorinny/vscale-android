package com.dimorinny.vscale

import android.app.Application
import com.dimorinny.vscale.db.DbModule

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
    }

    private fun initAppComponent() {
        graph = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .dbModule(DbModule())
                .build()
    }
}