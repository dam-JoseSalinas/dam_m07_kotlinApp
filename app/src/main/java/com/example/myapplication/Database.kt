package com.example.myapplication

import android.content.ClipDescription
import android.content.ContentValues
import android.content.Context
import android.content.res.Resources
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class Database(context: Context?, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "database", factory, 1) {
    val ct: Context? = context
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table if not exists usuarios(id integer primary key autoincrement, fullname text, username text, email text, password text)")
        db.execSQL("create table if not exists productos(id integer primary key autoincrement, image int, short_description text, long_description text)")

        val desc_long = ct?.getString(R.string.product_long_descripction)
        val desc_short = ct?.getString(R.string.product_short_description)
        //P1
        val values = ContentValues()
        values.put("image", R.drawable.productos_pc)
        values.put("short_description", desc_short)
        values.put("long_description", desc_long)
        db.insert("productos", null, values)
        //P2
        val values2 = ContentValues()
        values2.put("image", R.drawable.producto_rpi)
        values2.put("short_description", desc_short)
        values2.put("long_description", desc_long)
        db.insert("productos", null, values2)
        //P3
        val values3 = ContentValues()
        values3.put("image", R.drawable.producto_grafica)
        values3.put("short_description", desc_short)
        values3.put("long_description", desc_long)
        db.insert("productos", null, values3)
        //P4
        val values4 = ContentValues()
        values4.put("image", R.drawable.producto_placa)
        values4.put("short_description", desc_short)
        values4.put("long_description", desc_long)
        db.insert("productos", null, values4)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    fun addUser(fullname: String, username: String, email: String,  password: String) {
        val values = ContentValues()
        values.put("fullname", fullname)
        values.put("username", username)
        values.put("email", email)
        values.put("password", password)

        val db = this.writableDatabase
        db.insert("usuarios", null, values)
        db.close()
    }

    fun getUser(username: String, password: String): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("select * from usuarios where username=? and password=?", arrayOf(username, password))
    }
    fun getUserByUsername(username: String): Cursor? {
        val values = ContentValues()
        values.put("username", username)
        val db = this.readableDatabase
        return db.rawQuery("select * from usuarios where username=?", arrayOf(username))
    }

    fun addProducto(image: String, description: String) {
        val values = ContentValues()
        values.put("image", image)
        values.put("description", description)

        val db = this.writableDatabase
        db.insert("productos", null, values)
        db.close()
    }

    fun getProductos(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("select * from productos", null)
    }
}