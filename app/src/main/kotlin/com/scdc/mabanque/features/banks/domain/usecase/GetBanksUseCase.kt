package com.scdc.mabanque.features.banks.domain.usecase

import com.scdc.mabanque.features.banks.domain.model.Resource
import com.scdc.mabanque.features.banks.domain.model.Bank
import kotlinx.coroutines.flow.Flow


fun interface GetBanksUseCase : suspend () -> Flow<Resource<List<Bank>>>