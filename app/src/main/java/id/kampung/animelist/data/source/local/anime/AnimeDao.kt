package id.kampung.animelist.data.source.local.anime

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import id.kampung.animelist.data.model.DetailModel

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime")
    fun getAllAnime(): List<DetailModel>

    @Query("SELECT * FROM anime WHERE id = :id")
    fun getAnimeById(id: Int): DetailModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnime(anime: DetailModel)

}