package com.scdc.mabanque.features.banks.data.remote

import com.scdc.mabanque.features.banks.data.dto.BankDTO
import retrofit2.http.GET

interface BankService {
    @GET("/banks.json")
    suspend fun getBanks(): List<BankDTO>
}