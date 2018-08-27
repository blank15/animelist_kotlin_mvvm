package id.kampung.animelist.data

import id.kampung.animelist.data.model.DetailModel
import id.kampung.animelist.data.source.local.AppLocalDataSource

class AppRepository(val remoteDataSource: AppDataSource,val localDataSource: AppLocalDataSource) : AppDataSource {
    override fun getDetailAnime(idAnime: String, callback: AppDataSource.GetDetailAnimeCallback) {
        remoteDataSource.getDetailAnime(idAnime,object : AppDataSource.GetDetailAnimeCallback{
            override fun onDataLoaded(detailModel: DetailModel?) {
                localDataSource.saveAnime(detailModel!!)
            }

            override fun onDataNotAvailable() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(errorMessage: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        })
    }


    override fun saveAnime(detailModel: DetailModel) {
        localDataSource.saveAnime(detailModel)
    }



    var isRemote = false

    override fun remoteAnime(isRemote: Boolean) {
       this.isRemote = isRemote
    }
    override fun getTopAnime(callback: AppDataSource.GetAnimeSessionCallback) {
        if(isRemote){
            getRemoteAnimeSession(callback)
        }
    }
    override fun getAnimeSession(callback: AppDataSource.GetAnimeSessionCallback) {
        if(isRemote){
            getRemoteAnimeSession(callback)
        }
    }

    private fun getRemoteAnimeSession(callback: AppDataSource.GetAnimeSessionCallback) {
        remoteDataSource.getAnimeSession(object : AppDataSource.GetAnimeSessionCallback{
            override fun onDataLoaded(detailModel: List<DetailModel>?) {
                if (detailModel!!.isNotEmpty()) {
                    var j = 0

                    for (i in 0 until detailModel.size) {
                        j = i

                        localDataSource.saveAnime(DetailModel(detailModel[i].mal_id,detailModel[i].id,detailModel[i].title,
                                detailModel[i].image_url,detailModel[i].type,detailModel[i].score,detailModel[i].popularity,
                                detailModel[i].aired_string,detailModel[i].duration,detailModel[i].rank,
                                detailModel[i].favorites,detailModel[i].synopsis))

                        if(j == detailModel.size - 1){
                            localDataSource.getAnimeSession(object : AppDataSource.GetAnimeSessionCallback {
                                override fun onDataLoaded(detailModel: List<DetailModel>?) {
                                    callback.onDataLoaded(detailModel)
                                }

                                override fun onDataNotAvailable() {
                                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                }

                                override fun onError(errorMessage: String?) {
                                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                }

                            })
                        }
                    }
                }
            }

            override fun onDataNotAvailable() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(errorMessage: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }


    companion object {

        private var INSTANCE: AppRepository? = null

        /**
         * Returns the single instance of this class, creating it if necessary.

         * @param gitsRemoteDataSourcethe backend data source
         * *
         * @return the [GitsRepository] instance
         */
        @JvmStatic
        fun getInstance(gitsRemoteDataSource: AppDataSource,appLocalDataSource: AppLocalDataSource) =
                INSTANCE ?: synchronized(AppRepository::class.java) {
                    INSTANCE ?: AppRepository(gitsRemoteDataSource,appLocalDataSource)
                            .also { INSTANCE = it }
                }

        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}