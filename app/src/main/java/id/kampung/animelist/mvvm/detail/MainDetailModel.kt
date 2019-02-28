package id.kampung.animelist.mvvm.detail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.support.annotation.StringRes
import id.kampung.animelist.data.AppDataSource
import id.kampung.animelist.data.AppRepository
import id.kampung.animelist.data.model.DetailModel
import id.kampung.animelist.util.SingleLiveEvent

class MainDetailModel(context : Application, private val appRepository: AppRepository) : AndroidViewModel(context) {

    val snackBarMessageRemote = SingleLiveEvent<String>()
    val snackBarMessage = SingleLiveEvent<Int>()
    var titleAnime = SingleLiveEvent<String>()
    var popularitas = SingleLiveEvent<String>()
    var tanggalRilis = SingleLiveEvent<String>()
    var favorite = SingleLiveEvent<String>()
    var rank = SingleLiveEvent<String>()
    var durasi = SingleLiveEvent<String>()
    var imagUrl = SingleLiveEvent<String>()
    var deskripsi = SingleLiveEvent<String>()
    fun start( idAnime :String) {
        getDetail(idAnime)
    }

    private fun getDetail(idAnime: String) {
        appRepository.remoteDataSource.getDetailAnime(idAnime,object : AppDataSource.GetDetailAnimeCallback{
            override fun onDataLoaded(detailModel: DetailModel?) {

                    if(detailModel!= null){
                        titleAnime.value = detailModel.title
                        popularitas.value = detailModel.popularity
                        tanggalRilis.value = detailModel.aired_string
                        favorite.value = detailModel.favorites
                        rank.value = detailModel.rank
                        durasi.value = detailModel.duration
                        imagUrl.value = detailModel.image_url
                        deskripsi.value = detailModel.synopsis
                        println("url nya $imagUrl")
                    }

            }

            override fun onDataNotAvailable() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(errorMessage: String?) {
                if(errorMessage!=null){
                    showSnackbarMessage(errorMessage)
                }
            }


        })
    }
    fun showSnackbarMessage(mMessage: String) {
        snackBarMessageRemote.value = mMessage
    }

    /**
     * Show message response from Local resource
     * @param mMessage
     */
    private fun showSnackbarMessage(@StringRes mMessage: Int) {
        snackBarMessage.value = mMessage
    }
}