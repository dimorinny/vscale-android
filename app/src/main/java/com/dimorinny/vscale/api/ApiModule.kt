package com.dimorinny.vscale.api

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Dimorinny on 07.01.16.
 */

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideVscaleApi(context: Context) : ApiVscale{
        return ApiVscale(context)
    }
}