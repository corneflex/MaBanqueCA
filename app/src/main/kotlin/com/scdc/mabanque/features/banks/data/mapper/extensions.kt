package com.scdc.mabanque.features.banks.data.mapper

import com.scdc.mabanque.features.banks.data.dto.AccountDTO
import com.scdc.mabanque.features.banks.data.dto.BankDTO
import com.scdc.mabanque.features.banks.data.dto.OperationDTO
import com.scdc.mabanque.features.banks.domain.model.Account
import com.scdc.mabanque.features.banks.domain.model.Bank
import com.scdc.mabanque.features.banks.domain.model.Operation

fun OperationDTO.toDomain() = Operation(
    amount = this.amount,
    id = this.id,
    category = this.category,
    date = this.date,
    title = this.title
)

fun AccountDTO.toDomain() = Account(
    balance = this.balance,
    contractNumber = this.contractNumber,
    holder = this.holder,
    id = this.id,
    label = this.label,
    operations = this.operations.map { it.toDomain() },
    order = this.order,
    productCode = this.productCode,
    role = this.role
)

fun BankDTO.toDomain() = Bank(
    isCA = this.isCA,
    accounts = this.accounts.map { it.toDomain() },
    name = this.name
)