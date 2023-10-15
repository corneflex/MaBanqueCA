package com.scdc.mabanque.features.banks.domain.model

import java.util.Date


val operationA = Operation(
    id = "2",
    title = "A",
    amount = "-1,99",
    category = "costs",
    date = Date(1644870724)
)

val operationB = Operation(
    id = "2",
    title = "B",
    amount = "-45,99",
    category = "leisure",
    date = Date(1644870724)
)

val operationC = operationA.copy(
    id="3",
    title = "C", date =
    Date(1644870725)
)

val operationD = operationA.copy(
    id="4",
    title = "D", date =
    Date(1644870725)
)

val operationE = operationA.copy(
    id="5",
    title = "E", date =
    Date(1644870725)
)


val multipleOperationA = listOf(operationB, operationA, operationC)
val multipleOperationB = listOf(operationC, operationD)

val accountA = Account(
    balance = 425.84,
    contractNumber = "32216549871",
    holder = "A",
    id = "A",
    label = "A",
    operations = multipleOperationA,
    order = 1,
    productCode = "00004",
    role = 1
)

val accountB = Account(
    balance = 745.34,
    contractNumber = "32216549872",
    holder = "B",
    id = "B",
    label = "B",
    operations = multipleOperationB,
    order = 1,
    productCode = "00004",
    role = 1
)

val caBankA = Bank(
    isCA = 1,
    name = "A",
    accounts = listOf(
        accountA
    )
)

val caBankB = Bank(
    isCA = 1,
    name = "B",
    accounts = listOf(
        accountA
    )
)

val otherBankA = Bank(
    isCA = 0,
    name = "A",
    accounts = listOf(
        accountB
    )
)

val otherBankB = Bank(
    isCA = 0,
    name = "B",
    accounts = listOf(
        accountB
    )
)

