package com.example.myapplication

import android.content.Context
import android.database.Cursor
import com.example.myapplication.Database
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class DatabaseTest {

    private lateinit var context: Context
    private lateinit var db: Database

    @Before
    fun setUp() {
        context = org.robolectric.RuntimeEnvironment.application
        db = Database(context, null)
    }

    @Test
    fun testAddUserAndGetUser() {
        db.addUser("nuevo usuario", "nuevo", "nuevo@gmail.com", "nuevo")
        val data:Cursor? = db.getUser("nuevo", "nuevo")
        if (data?.moveToNext() == true) {
            assertEquals("nuevo", data.getString(2))
            assertEquals("nuevo", data.getString(4))
        }
        data?.close()
    }
    @Test
    fun testValidacionUsuarioExistente() {
        db.addUser("nuevo usuario", "nuevo", "nuevo@gmail.com", "nuevo")
        val user1:Cursor? = db.getUserByUsername("nuevo")
        assertEquals(1, user1?.count)
        val user2:Cursor? = db.getUserByUsername("no existe")
        assertEquals(0, user2?.count)
        user1?.close()
        user2?.close()
    }



}