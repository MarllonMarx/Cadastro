package senai.cadastro

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context)
    :SQLiteOpenHelper(context,"database.db",null,1) {

        val sql = arrayOf(
            "CREATE TABLE cadastro ( id INTEGER PRIMARY KEY AUTOINCREMENT , username TEXT,email TEXT, telefone TEXT, password TEXT)",
            "INSERT INTO cadastro (username,email,telefone,password) VALUES ('user','teste@gmail.com','000','123')"
        )

    override fun onCreate(db: SQLiteDatabase) {
        sql.forEach {
          db.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE cadastro")
        onCreate(db)

    }

    fun listSelectAll(): Cursor {
        val db  = this.readableDatabase
        val c =  db.rawQuery("SELECT * FROM cadastro",null)
        db.close()
        return c
    }

    fun listauserSelectAll(): ArrayList<User> {

        val db  = this.readableDatabase
        val c =  db.rawQuery("SELECT * FROM cadastro",null)

        val listuser : ArrayList<User> = ArrayList()

        if (c.count >= 0){

            c.moveToFirst()

            do {
                val idIndex = c.getColumnIndex("id")
                val usernameIndex = c.getColumnIndex("username")
                val emailIndex = c.getColumnIndex("email")
                val telefoneIndex = c.getColumnIndex("telefone")
                val paswordIndex = c.getColumnIndex("password")

                val id = c.getInt(idIndex)
                val username = c.getString(usernameIndex)
                val email = c.getString(emailIndex)
                val telefone = c.getString(telefoneIndex)
                val password = c.getString(paswordIndex)

                listuser.add(User(id, username, email, telefone, password))
            }while (c.moveToNext())
        }
        db.close()
        return listuser
    }

    fun listSelectById(id: Int): Cursor{
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM cadastro WHERE id=?", arrayOf(id.toString()))

     db.close()
        return c
    }

    fun listuserSelectById(id: Int): User{
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM cadastro WHERE id=?", arrayOf(id.toString()))
        var user = User()

        if (c.count == 1){

            c.moveToFirst()

            val idIndex = c.getColumnIndex("id")
            val usernameIndex = c.getColumnIndex("username")
            val emailIndex = c.getColumnIndex("email")
            val telefoneIndex = c.getColumnIndex("telefone")
            val paswordIndex = c.getColumnIndex("password")

            val id = c.getInt(idIndex)
            val username = c.getString(usernameIndex)
            val email = c.getString(emailIndex)
            val telefone = c.getString(telefoneIndex)
            val password = c.getString(paswordIndex)

            user = User(id, username, email, telefone, password)
        }
        db.close()
        return  user
    }

    fun Insertuser(username: String, email: String, telefone: String, password: String):Long{

        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put("username",username)
        contentValues.put("email",email)
        contentValues.put("telefone",telefone)
        contentValues.put("password",password)

        val result = db.insert("cadastro",null,contentValues)

        db.close()
        return result
    }

    fun UpdateUser(id: Int, username: String, email: String, telefone: String, password: String):Int{

        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put("username",username)
        contentValues.put("email",email)
        contentValues.put("telefone",telefone)
        contentValues.put("password",password)

        val result = db.update("cadastro",contentValues,"id=?", arrayOf(id.toString()))

        db.close()
        return result
    }

    fun Deleteuser(id: Int): Int {

        val db = this.writableDatabase
        val result = db.delete("cadastro","id=?", arrayOf(id.toString()))

        db.close()
        return result
    }
}