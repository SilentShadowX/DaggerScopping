package com.kpiega.daggerscopping.di.module.user

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kpiega.daggerscopping.di.module.qualifier.ServerInfo
import com.kpiega.daggerscopping.di.module.app.InfoModule
import com.kpiega.daggerscopping.di.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

@Module(includes = arrayOf(
        InfoModule::class
))
class NetworkModule {

    @Provides
    @AppScope
    fun provideCacheFile(context: Application): File {
        val cacheFile = File(context.cacheDir, "http_cache")
        cacheFile.mkdir()
        return cacheFile
    }

    @Provides
    @AppScope
    fun provideCache(file: File) = Cache(file, 10 * 1024 * 1024)

    @Provides
    @AppScope
    fun provideHttpClient(
            logger: HttpLoggingInterceptor,
            cache: Cache
    ) = OkHttpClient.Builder()
            .addInterceptor(logger)
            .cache(cache)
            .build()


    @Provides
    @AppScope
    fun provideGson() =  GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

    @Provides
    @AppScope
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson, @ServerInfo url: String) = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
}

