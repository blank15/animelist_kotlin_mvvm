package id.kampung.animelist.data.source.local

import android.support.annotation.VisibleForTesting
import id.kampung.animelist.data.AppDataSource
import id.kampung.animelist.data.model.DetailModel
import id.kampung.animelist.data.source.local.anime.AnimeDao
import id.kampung.animelist.util.dbhelper.AppExecutors

class AppLocalDataSource private constructor(val appExecutors: AppExecutors,
                         val animeDao: AnimeDao) : AppDataSource {
    override fun getTopAnime(callback: AppDataSource.GetAnimeSessionCallback) {
        appExecutors.diskIO.execute {
            val anime = animeDao.getAllAnime()

            appExecutors.mainThread.execute {
                if (anime.isEmpty()){
                    callback.onError("Data anime tidak ditemukan")
                } else {
                    callback.onDataLoaded(anime)
                }
            }
        }
    }

    override fun saveAnime(detailModel: DetailModel) {
        appExecutors.diskIO.execute {
            animeDao.insertAnime(detailModel)
        }
    }

    override fun getAnimeSession(callback: AppDataSource.GetAnimeSessionCallback) {
        appExecutors.diskIO.execute {
            val anime = animeDao.getAllAnime()

            appExecutors.mainThread.execute {
                if (anime.isEmpty()){
                    callback.onError("Data anime tidak ditemukan")
                } else {
                    callback.onDataLoaded(anime)
                }
            }
        }
    }

    override fun getDetailAnime(idAnime:String,callback: AppDataSource.GetDetailAnimeCallback) {
        appExecutors.diskIO.execute {
            val animes = animeDao.getAllAnime()

            appExecutors.mainThread.execute {
                if (animes.isEmpty()){
                    callback.onError("Data anime tidak ditemukan")
                } else {
                    callback.onDataLoaded(animes.get(idAnime.toInt())!!)
                }
            }
        }
    }

    override fun remoteAnime(isRemote: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    companion object {

        private var INSTANCE: AppLocalDataSource? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, animeDao: AnimeDao): AppLocalDataSource {
            if (INSTANCE == null) {
                synchronized(AppLocalDataSource::javaClass) {
                    INSTANCE = AppLocalDataSource(appExecutors, animeDao)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }
}