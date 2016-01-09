package com.dimorinny.vscale.db

import com.dimorinny.vscale.db.entity.ServerEntity
import com.dimorinny.vscale.db.tables.ServerTable
import com.pushtorefresh.storio.sqlite.StorIOSQLite
import com.pushtorefresh.storio.sqlite.queries.DeleteQuery
import com.pushtorefresh.storio.sqlite.queries.Query
import rx.Observable

/**
 * Created by Dimorinny on 09.01.16.
 */
class DataManager(private val storIO: StorIOSQLite) {

    fun getServersObservable() : Observable<List<ServerEntity>> {
        return storIO.get()
                .listOfObjects(ServerEntity::class.java)
                .withQuery(Query.builder()
                        .table(ServerTable.TABLE)
                        .build())
                .prepare()
                .createObservable()
    }

    fun clearServers() {
        storIO.delete()
                .byQuery(DeleteQuery.builder().table(ServerTable.TABLE).build())
                .prepare()
                .executeAsBlocking()
    }

    fun putServers(servers: List<ServerEntity>) {
        storIO.put()
                .objects(servers)
                .prepare()
                .executeAsBlocking()
    }
}