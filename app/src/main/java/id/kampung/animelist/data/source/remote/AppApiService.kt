package id.kampung.animelist.data.source.remote

import io.reactivex.Observable
import id.kampung.animelist.BuildConfig
import id.kampung.animelist.data.model.DetailModel
import id.kampung.animelist.data.model.SeasonModel
import id.kampung.animelist.data.model.TopModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface AppApiService {



    @GET("season/2018/winter")
    fun getSeasonMoview() : Observable<SeasonModel>

    @GET("/anime/{id}")
    fun getDetailAnime(@Path("id") id: String) :Observable<DetailModel>

    @GET("/top/anime/1/upcoming")
    fun getUpComing() : Observable<TopModel>

    companion object Factory {

        fun create(): AppApiService {
            val mLoggingInterceptor = HttpLoggingInterceptor()
            mLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val mClient = OkHttpClient.Builder()
                    .addInterceptor(mLoggingInterceptor)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build()

            val mRetrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(mClient) //Todo comment if app release
                    .build()

            return mRetrofit.create(AppApiService::class.java)
        }
    }
}