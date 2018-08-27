package id.kampung.animelist.util

import android.content.Context
import id.kampung.animelist.data.AppRepository
import id.kampung.animelist.data.source.local.AppDatabase
import id.kampung.animelist.data.source.local.AppLocalDataSource
import id.kampung.animelist.data.source.remote.AppRemoteData
import id.kampung.animelist.util.dbhelper.AppExecutors

object Injection {
    fun provideGitsRepository(context: Context): AppRepository{
        val localDatabase = AppDatabase.getInstance(context)

        return AppRepository.getInstance(AppRemoteData,
                AppLocalDataSource.getInstance(AppExecutors(),localDatabase.animeDao()))
    }
}