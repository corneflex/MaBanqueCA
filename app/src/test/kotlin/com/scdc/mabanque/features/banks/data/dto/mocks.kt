package com.scdc.mabanque.features.banks.data.dto

import java.util.Date

val bankDTO = BankDTO(
    name = "A",
    isCA = 1,
    accounts = listOf(
        AccountDTO(
            balance = 100.0,
            contractNumber = "1",
            holder = "A",
            id = "A",
            label = "A",
            order = 0,
            role = 0,
            productCode = "0",
            operations = listOf(OperationDTO(
                amount = "100,20",
                category = "A",
                date = Date(),
                id="A",
                title = "A"
            ))
        )
    )
)