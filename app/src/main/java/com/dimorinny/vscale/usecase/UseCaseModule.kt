package com.dimorinny.vscale.usecase

import com.dimorinny.vscale.api.ApiVscale
import com.dimorinny.vscale.db.DataManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Dimorinny on 05.01.16.
 */

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideLoadServersUseCase(dataManager: DataManager, api: ApiVscale) : LoadServersUseCase {
        return LoadServersUseCase(dataManager, api)
    }
}