package id.kampung.animelist.data

import id.kampung.animelist.data.model.DetailModel


interface AppDataSource {
    fun getAnimeSession(callback: GetAnimeSessionCallback)
    fun getDetailAnime(id : String,callback: GetDetailAnimeCallback)
    fun getTopAnime(callback: GetAnimeSessionCallback)

    fun remoteAnime(isRemote: Boolean)
    fun saveAnime (detailModel: DetailModel)
    interface GetAnimeSessionCallback {
        fun onDataLoaded(detailModel: List<DetailModel>?)
        fun onDataNotAvailable()

        fun onError(errorMessage: String?)
    }
    interface GetDetailAnimeCallback {
        fun onDataLoaded(detailModel: DetailModel?)
        fun onDataNotAvailable()

        fun onError(errorMessage: String?)
    }
}
