package com.dimorinny.vscale

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Dimorinny on 04.01.16.
 */

@Module
class AppModule(private val application: App) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application
    }
}