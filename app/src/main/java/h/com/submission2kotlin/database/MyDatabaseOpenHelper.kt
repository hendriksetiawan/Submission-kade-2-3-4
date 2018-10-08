package h.com.submission2kotlin.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavoriteEvent.db", null, 1){
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
    fun getInstance(ctx: Context): MyDatabaseOpenHelper{
            if (instance == null){
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Favorite.TABLE_FAVORITE, true, Favorite.ID to INTEGER+ PRIMARY_KEY+ AUTOINCREMENT,
                Favorite.EVENT_ID to TEXT + UNIQUE,
                Favorite.EVENT_DATE to TEXT,
                Favorite.TEAM_HOME to TEXT,
                Favorite.TEAM_AWAY to TEXT,
                Favorite.TEAM_HOME_SCORE to TEXT,
                Favorite.TEAM_AWAY_SCORE to TEXT,
                Favorite.TEAM_HOME_ID to TEXT,
                Favorite.TEAM_AWAY_ID to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}
val Context.database: MyDatabaseOpenHelper
get() = MyDatabaseOpenHelper.getInstance(applicationContext)