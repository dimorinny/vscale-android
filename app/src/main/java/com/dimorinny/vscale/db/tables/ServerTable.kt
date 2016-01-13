package com.dimorinny.vscale.db.tables

/**
 * Created by Dimorinny on 01.01.16.
 */
public class ServerTable private constructor() {
    public companion object {
        const val TABLE = "Servers"
        const val COLUMN_HOSTNAME = "hostname"
        const val COLUMN_LOCATIONS = "locations"
        const val COLUMN_LOCKED = "locked"
        const val COLUMN_RPLAN = "rplan"
        const val COLUMN_NAME = "name"
        const val COLUMN_ACTIVE = "active"
        const val COLUMN_STATUS = "started"
        const val COLUMN_MADE_FROM = "made_from"
        const val COLUMN_CTID = "ctid"

        fun getCreateTableQuery() : String {
            return "CREATE TABLE $TABLE (" +
                    "$COLUMN_CTID INTEGER NOT NULL PRIMARY KEY," +
                    "$COLUMN_HOSTNAME TEXT NOT NULL," +
                    "$COLUMN_LOCATIONS TEXT NOT NULL," +
                    "$COLUMN_LOCKED INTEGER NOT NULL," +
                    "$COLUMN_RPLAN TEXT NOT NULL," +
                    "$COLUMN_NAME TEXT NOT NULL," +
                    "$COLUMN_ACTIVE INTEGER NOT NULL," +
                    "$COLUMN_STATUS TEXT NOT NULL," +
                    "$COLUMN_MADE_FROM TEXT NOT NULL" +
                    ");";
        }
    }
}