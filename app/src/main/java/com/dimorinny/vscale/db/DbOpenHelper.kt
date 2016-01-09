package com.dimorinny.vscale.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dimorinny.vscale.db.tables.ServerTable

public class DbOpenHelper(context: Context) :
        SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    public companion object {
        const private val DB_NAME = "sample_db"
        const private val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(ServerTable.getCreateTableQuery())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // no impl
    }
}

