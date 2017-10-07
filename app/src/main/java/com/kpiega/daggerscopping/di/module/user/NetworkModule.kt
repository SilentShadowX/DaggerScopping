package com.kpiega.daggerscopping.di.module.user

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kpiega.daggerscopping.di.module.qualifier.ServerInfo
import com.kpiega.daggerscopping.di.module.app.InfoModule
import com.kpiega.daggerscopping.di.scope.AppScope
import com.kpiega.daggerscopping.di.scope.UserScope
import com.kpiega.daggerscopping.model.User
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
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
    @UserScope
    fun provideCacheFile(context: Application): File {
        val cacheFile = File(context.cacheDir, "http_cache")
        cacheFile.mkdir()
        return cacheFile
    }

    @Provides
    @UserScope
    fun provideCache(file: File) = Cache(file, 10 * 1024 * 1024)

    @Provides
    @UserScope
    fun provideAuthenticationToken(user: User): AuthInterceptor {
        return AuthInterceptor(user.token)
    }

    @Provides
    @UserScope
    fun provideAuthenticationLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @UserScope
    fun provideHttpClient(
            authInterceptor: AuthInterceptor,
            logger: HttpLoggingInterceptor,
            cache: Cache
    ) = OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(authInterceptor)
            .cache(cache)
            .build()


    @Provides
    @UserScope
    fun provideGson() =  GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

    @Provides
    @UserScope
    fun provideRetrofit(
            okHttpClient: OkHttpClient,
            gson: Gson,
            @ServerInfo url: String
    ) = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
}

