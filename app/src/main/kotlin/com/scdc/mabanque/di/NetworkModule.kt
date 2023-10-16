package com.scdc.mabanque.di

import android.content.Context
import com.scdc.core.network.UnixDateJsonAdapter
import com.scdc.core.network.isNetworkConnected
import com.scdc.mabanque.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        val cacheSize = (5 * 1024 * 1024).toLong()
        return Cache(context.cacheDir, cacheSize)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context, cache: Cache): OkHttpClient =
        OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (isNetworkConnected(context))
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }
            .addInterceptor(MockResponseInterceptor())
            .build()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory()) // This is for Kotlin classes
            .add(UnixDateJsonAdapter())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }
}