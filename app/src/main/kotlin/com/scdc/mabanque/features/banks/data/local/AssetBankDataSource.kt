package com.scdc.mabanque.features.banks.data.local

import android.content.Context
import com.scdc.core.io.readFileFromAssets
import com.scdc.mabanque.features.banks.data.dto.BankDTO
import com.scdc.mabanque.features.banks.data.mapper.toDomain
import com.scdc.mabanque.features.banks.domain.model.Bank
import com.scdc.mabanque.features.banks.domain.repository.BankDataSource
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

const val BANK_JSON = "banks.json"

class AssetBankDataSource(private val context: Context, private val moshi: Moshi) : BankDataSource {
    override suspend fun fetchBanks(): List<Bank> {
        val type: Type = Types.newParameterizedType(
            MutableList::class.java,
            BankDTO::class.java
        )
        val adapter: JsonAdapter<List<BankDTO>> = moshi.adapter(type)
        val json = readFileFromAssets(context, BANK_JSON)
        println(json)
        return (adapter.fromJson(json)?.map { it.toDomain() }) ?: emptyList()
    }

}

