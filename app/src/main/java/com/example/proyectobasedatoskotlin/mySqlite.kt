package com.example.proyectobasedatoskotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class mySqlite(context: Context) : SQLiteOpenHelper(




    context,"base.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion = "CREATE TABLE BASE "+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "codigo TEXT, mensaje TEXT)"
        db!!.execSQL(ordenCreacion) // !! aseguramos que no va ser null
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS BASE"
        db!!.execSQL(ordenBorrado) // !! aseguramos que no va ser null
        onCreate(db)
    }

    //REGISTRA LOS DATOS A MI BASE DE DATOS "base.bs"
    fun aniadeDato(codigo: String, mensaje: String){ // String codigo,
        val db = this.writableDatabase
        val datos = ContentValues().apply {
            put("codigo", codigo)
            put("mensaje", mensaje)
        }

        db.insert("BASE", null, datos)

        db.close()
    }


}