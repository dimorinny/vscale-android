package com.dimorinny.vscale.db

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.dimorinny.vscale.db.entity.ServerEntity
import com.dimorinny.vscale.db.entity.ServerEntityStorIOSQLiteDeleteResolver
import com.dimorinny.vscale.db.entity.ServerEntityStorIOSQLiteGetResolver
import com.dimorinny.vscale.db.entity.ServerEntityStorIOSQLitePutResolver
import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping
import com.pushtorefresh.storio.sqlite.StorIOSQLite
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Dimorinny on 04.01.16.
 */

@Module
class DbModule {

    @Provides
    @Singleton
    fun provideStoreIOSQLite(sqLiteOpenHelper: SQLiteOpenHelper) : StorIOSQLite {
        return DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(sqLiteOpenHelper)
                .addTypeMapping(ServerEntity::class.java, SQLiteTypeMapping.builder<ServerEntity>()
                        .putResolver(ServerEntityStorIOSQLitePutResolver())
                        .getResolver(ServerEntityStorIOSQLiteGetResolver())
                        .deleteResolver(ServerEntityStorIOSQLiteDeleteResolver())
                        .build())
                .build();
    }

    @Provides
    @Singleton
    fun provideSQLiteOpenHelper(context : Context) : SQLiteOpenHelper {
        return DbOpenHelper(context)
    }

    @Provides
    @Singleton
    fun provideDbManager(storIO: StorIOSQLite) : DataManager {
        return DataManager(storIO)
    }
}