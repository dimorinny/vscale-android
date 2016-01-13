package com.dimorinny.vscale.db.entity

import com.dimorinny.vscale.db.tables.ServerTable
import com.google.gson.annotations.SerializedName
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType

/**
 * Created by Dimorinny on 01.01.16.
 */

@StorIOSQLiteType(table = ServerTable.TABLE)
data class ServerEntity (
    @SerializedName("hostname")
    @StorIOSQLiteColumn(name = ServerTable.Companion.COLUMN_HOSTNAME)
    @JvmField
    var hostName: String? = null,

    @SerializedName("locations")
    @StorIOSQLiteColumn(name = ServerTable.Companion.COLUMN_LOCATIONS)
    @JvmField
    var locations: String? = null,

    @SerializedName("locked")
    @StorIOSQLiteColumn(name = ServerTable.Companion.COLUMN_LOCKED)
    @JvmField
    var locked: Boolean? = null,

    @SerializedName("rplan")
    @StorIOSQLiteColumn(name = ServerTable.Companion.COLUMN_RPLAN)
    @JvmField
    var plan: String? = null,

    @SerializedName("name")
    @StorIOSQLiteColumn(name = ServerTable.Companion.COLUMN_NAME)
    @JvmField
    var name: String? = null,

    @SerializedName("active")
    @StorIOSQLiteColumn(name = ServerTable.Companion.COLUMN_ACTIVE)
    @JvmField
    var active: Boolean? = null,

    @SerializedName("status")
    @StorIOSQLiteColumn(name = ServerTable.Companion.COLUMN_STATUS)
    @JvmField
    var status: String? = null,

    @SerializedName("made_from")
    @StorIOSQLiteColumn(name = ServerTable.Companion.COLUMN_MADE_FROM)
    @JvmField
    var madeFrom: String? = null,

    @SerializedName("ctid")
    @StorIOSQLiteColumn(name = ServerTable.Companion.COLUMN_CTID, key = true)
    @JvmField
    var ctId: Int? = null
)

