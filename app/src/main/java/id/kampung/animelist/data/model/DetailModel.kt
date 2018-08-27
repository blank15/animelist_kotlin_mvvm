package id.kampung.animelist.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "anime")
data class DetailModel(
        @ColumnInfo(name = "mal_id")
        var mal_id : Int? = null,
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        @ColumnInfo(name = "title")
        var title : String,
        @ColumnInfo(name = "image_url")
        var image_url : String? = null,
        @ColumnInfo(name = "type")
        var type : String? = null,
        @ColumnInfo(name = "score")
        var score : Float? = null,
        @ColumnInfo(name = "popularity")
        var popularity : String,
        @ColumnInfo(name = "aired_string")
        var aired_string :String,
        @ColumnInfo(name = "duration")
        var duration:String,
        @ColumnInfo(name = "rank")
        var rank:String,
        @ColumnInfo(name = "favorites")
        var favorites:String,
        @ColumnInfo(name = "synopsis")
        var synopsis:String

): Serializable