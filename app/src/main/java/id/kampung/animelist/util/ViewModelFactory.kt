package id.kampung.animelist.util

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import id.kampung.animelist.data.AppRepository
import id.kampung.animelist.mvvm.detail.MainDetailModel
import id.kampung.animelist.mvvm.main.MainViewModel

class ViewModelFactory private constructor(
        private val mApplication: Application,
        private val appRepository: AppRepository,
        private val context: Context
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(MainViewModel::class.java) ->
                        MainViewModel(mApplication, appRepository,context)
                    isAssignableFrom(MainDetailModel::class.java) ->
                        MainDetailModel(mApplication, appRepository)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance(mApplication: Application,context: Context) =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(mApplication,
                            Injection.provideGitsRepository(mApplication.applicationContext),context)
                            .also { INSTANCE = it }
                }
    }
}