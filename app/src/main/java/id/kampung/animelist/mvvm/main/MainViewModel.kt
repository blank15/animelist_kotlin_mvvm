package id.kampung.animelist.mvvm.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.Intent
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat.startActivity
import id.kampung.animelist.data.AppDataSource
import id.kampung.animelist.data.AppRepository
import id.kampung.animelist.data.model.DetailModel
import id.kampung.animelist.mvvm.detail.MainDetailActivity
import id.kampung.animelist.util.SingleLiveEvent
import android.os.Bundle



class MainViewModel(context : Application,private val appRepository: AppRepository,private val contexts : Context) : AndroidViewModel(context),MainItemClickAction {
    override fun onItemClicked(detailModel: DetailModel) {

        var intent = Intent(contexts, MainDetailActivity::class.java)
        intent.putExtra("idAnime",detailModel.mal_id )
        contexts.startActivity(intent)
    }

    var seassonListLive = MutableLiveData<List<DetailModel>>()
    val snackBarMessageRemote = SingleLiveEvent<String>()
    val snackBarMessage = SingleLiveEvent<Int>()
    var showProgress = SingleLiveEvent<Boolean>()
    var showProgress2 = SingleLiveEvent<Boolean>()
    var data :List<DetailModel> = emptyList()
    var dataUpComing :List<DetailModel> = emptyList()
    var title = String()
    var adapterList :AdapterList = AdapterList(data,this)
    var adapterListUpComing :AdapterList = AdapterList(dataUpComing,this)
    fun start() {
        val isRemote = true

        getSeasson(isRemote)
    }

    fun staratUpComing(){
        val isRemote = true

        getUpComing(isRemote)
    }

    private fun getUpComing(remote: Boolean) {
        showProgress2.value = true

        if (remote) {
            appRepository.remoteDataSource.getTopAnime(object  : AppDataSource.GetAnimeSessionCallback{
                override fun onDataLoaded(detailModel: List<DetailModel>?) {
                    showProgress2.value = false
                    if(detailModel != null){
                        dataUpComing = detailModel
                        seassonListLive.postValue(detailModel)
                        title = detailModel[1].title
                        adapterListUpComing.setList(detailModel)
                        println("ukuran list ${data.size}")

                    }
                }

                override fun onDataNotAvailable() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onError(errorMessage: String?) {
                    showProgress2.value = false
                    if(errorMessage!=null){
                        showSnackbarMessage(errorMessage)
                    }
                }

            })
        }
    }

    private fun getSeasson(remote: Boolean) {
        showProgress.value = true

        if (remote) {
            appRepository.remoteDataSource.getAnimeSession(object  : AppDataSource.GetAnimeSessionCallback{
                override fun onDataLoaded(detailModel: List<DetailModel>?) {
                    showProgress.value = false
                    if(detailModel != null){
                        data = detailModel
                        seassonListLive.postValue(detailModel)
                        title = detailModel[1].title
                        adapterList.setList(detailModel)
                        println("ukuran list ${data.size}")

                    }
                }

                override fun onDataNotAvailable() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onError(errorMessage: String?) {
                    showProgress.value
                    if(errorMessage!=null){
                        showSnackbarMessage(errorMessage)
                    }
                }

            })
        }
    }
    /**
     * Show message response from API
     * @param mMessage
     */
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