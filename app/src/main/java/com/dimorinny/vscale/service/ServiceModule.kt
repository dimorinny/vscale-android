package com.dimorinny.vscale.service

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Dimorinny on 05.01.16.
 */

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideServiceManager(context: Context) : ServiceManager {
        return ServiceManager(context)
    }
}