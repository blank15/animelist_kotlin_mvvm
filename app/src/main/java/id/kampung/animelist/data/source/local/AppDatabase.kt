package id.kampung.animelist.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import id.kampung.animelist.data.model.DetailModel
import id.kampung.animelist.data.source.local.anime.AnimeDao

@Database(entities = [(DetailModel::class)], version = 2)
abstract  class AppDatabase : RoomDatabase() {
    abstract fun  animeDao() : AnimeDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase=
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also {
                        INSTANCE = it
                    }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "Anime.db")
                        .build()
    }
}
