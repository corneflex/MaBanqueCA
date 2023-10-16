package com.scdc.mabanque.features.banks.di

import android.content.Context
import com.scdc.mabanque.BuildConfig
import com.scdc.mabanque.features.banks.data.local.AssetBankDataSource
import com.scdc.mabanque.features.banks.data.remote.BankService
import com.scdc.mabanque.features.banks.data.remote.RemoteBankDataSource
import com.scdc.mabanque.features.banks.data.repository.RetrofitAccountRepository
import com.scdc.mabanque.features.banks.domain.repository.BankDataSource
import com.scdc.mabanque.features.banks.domain.repository.BankRepository
import com.scdc.mabanque.features.banks.domain.usecase.GetBanksUseCase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BanksModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RemoteBankDataSourceType

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AssetBankDataSourceType

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): BankService = retrofit.create(BankService::class.java)

    @Provides
    @Singleton
    @RemoteBankDataSourceType
    fun provideRemoteDataSource(bankService: BankService): BankDataSource {
       return RemoteBankDataSource(bankService)
    }

    @Provides
    @Singleton
    @AssetBankDataSourceType
    fun provideLocalDataSource(moshi: Moshi, @ApplicationContext context: Context): BankDataSource {
        return AssetBankDataSource(context,moshi)
    }
    @Provides
    @Singleton
    fun provideRepository(@RemoteBankDataSourceType remoteDataSource: BankDataSource, @AssetBankDataSourceType localDataSource: BankDataSource): BankRepository {
        return if (BuildConfig.DEBUG) {
            RetrofitAccountRepository(localDataSource)
        } else {
            RetrofitAccountRepository(remoteDataSource)
        }
    }

    @Provides
    @Singleton
    fun provideGetBankUseCase(
        bankRepository: BankRepository
    ): GetBanksUseCase {
        return GetBanksUseCase(bankRepository::getBankAccounts)
    }
}
