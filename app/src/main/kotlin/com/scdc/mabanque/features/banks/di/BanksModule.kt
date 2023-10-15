package com.scdc.mabanque.features.banks.di

import android.content.Context
import com.scdc.core.network.isNetworkConnected
import com.scdc.mabanque.features.banks.data.remote.BankService
import com.scdc.mabanque.features.banks.data.repository.RetrofitAccountRepository
import com.scdc.mabanque.features.banks.domain.repository.BankRepository
import com.scdc.mabanque.features.banks.domain.usecase.GetBanksUseCase
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
object BanksModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): BankService = retrofit.create(BankService::class.java)

    @Provides
    @Singleton
    fun provideRepository(accountService: BankService): BankRepository {
        return RetrofitAccountRepository(accountService)
    }

    @Provides
    @Singleton
    fun provideGetBankUseCase(
        bankRepository: BankRepository
    ): GetBanksUseCase {
        return GetBanksUseCase(bankRepository::getBankAccounts)
    }
}
