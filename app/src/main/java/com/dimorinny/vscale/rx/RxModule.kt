package com.dimorinny.vscale.rx

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Dimorinny on 25.01.16.
 */

@Module
class RxModule {

    @Singleton
    @Provides
    fun provideRxBus(): RxBus {
        return RxBus()
    }
}