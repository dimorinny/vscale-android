package com.dimorinny.vscale

import com.dimorinny.vscale.api.ApiModule
import com.dimorinny.vscale.db.DbModule
import com.dimorinny.vscale.service.ApiService
import com.dimorinny.vscale.service.ServiceModule
import com.dimorinny.vscale.ui.servers.ServersFragment
import com.dimorinny.vscale.usecase.UseCaseModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Dimorinny on 04.01.16.
 */

@Singleton
@Component (modules = arrayOf(
        AppModule::class,
        DbModule::class,
        ServiceModule::class,
        UseCaseModule::class,
        ApiModule::class))
interface AppComponent {
    fun inject(application: App)
    fun inject(fragment: ServersFragment)
    fun inject(service: ApiService)
}