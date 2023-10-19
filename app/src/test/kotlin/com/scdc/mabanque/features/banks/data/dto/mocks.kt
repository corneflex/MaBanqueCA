package com.scdc.mabanque.features.banks.data.dto

import com.scdc.mabanque.features.banks.domain.model.Account
import com.scdc.mabanque.features.banks.domain.model.Bank
import com.scdc.mabanque.features.banks.domain.model.Operation
import java.util.Date

val bankDTO = Bank(
    name = "A",
    isCA = 1,
    accounts = listOf(
        Account(
            balance = 100.0,
            contractNumber = "1",
            holder = "A",
            id = "A",
            label = "A",
            order = 0,
            role = 0,
            productCode = "0",
            operations = listOf(
                Operation(
                amount = "100,20",
                category = "A",
                date = Date(),
                id="A",
                title = "A"
            )
            )
        )
    )
)