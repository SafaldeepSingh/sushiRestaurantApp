package com.safaldeepsingh.practicalexam.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


private const val SQL_CREATE_TABLE =
    "CREATE TABLE ${SushiDbContract.CartTable.TABLE_NAME} ("+
            "${SushiDbContract.CartTable.PRODUCT_ID} INTEGER, "+
            "${SushiDbContract.CartTable.QUANTITY} INTEGER  "+
            ")"
private const val DROP_TABLE = "DROP TABLE IF EXISTS ${SushiDbContract.CartTable.TABLE_NAME}"
class SushiDbHelper(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        const val DATABASE_NAME = "sushi.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        super.onDowngrade(db, oldVersion, newVersion)
    }
}