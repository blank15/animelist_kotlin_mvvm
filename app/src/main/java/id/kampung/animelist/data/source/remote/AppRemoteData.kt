package id.kampung.animelist.data.source.remote

import id.kampung.animelist.data.AppDataSource
import id.kampung.animelist.data.model.DetailModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object AppRemoteData : AppDataSource {
    override fun getTopAnime(callback: AppDataSource.GetAnimeSessionCallback) {
        apiService.getUpComing()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ results ->
                    run {
                        if (results.top!!.isNotEmpty()) {
                            callback.onDataLoaded(results.top)
                        } else {
                            callback.onDataNotAvailable()
                        }
                    }
                }, { error ->
                    callback.onError(error.message)
                })
    }

    override fun saveAnime(detailModel: DetailModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDetailAnime(idAnime : String,callback: AppDataSource.GetDetailAnimeCallback) {
        apiService.getDetailAnime(idAnime)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ results ->
                    run {
                        if (results != null) {
                            callback.onDataLoaded(results)
                        } else {
                            callback.onDataNotAvailable()
                        }
                    }
                }, { error ->
                    callback.onError(error.message)
                })
    }

    override fun remoteAnime(isRemote: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var apiService = AppApiService.create()
    override fun getAnimeSession(callback: AppDataSource.GetAnimeSessionCallback) {
        apiService.getSeasonMoview()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ results ->
                    run {
                        if (results.season!!.isNotEmpty()) {
                            callback.onDataLoaded(results.season)
                        } else {
                            callback.onDataNotAvailable()
                        }
                    }
                }, { error ->
                    callback.onError(error.message)
                })


    }



}