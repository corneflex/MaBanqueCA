package com.scdc.mabanque.features.banks.ui


import com.scdc.mabanque.features.banks.domain.model.Account
import com.scdc.mabanque.features.banks.domain.model.Bank
import com.scdc.mabanque.features.banks.domain.model.Operation
import java.util.Date

fun toDoubleAmount(amount: String): Double {
    return amount.replace(',', '.').toDouble()
}


val caBank = Bank(
    name = "CA Languedoc",
    isCA = 1,
    accounts = listOf(
        Account(
            id = "151515151151",
            holder = "Corinne Martin",
            role = 1,
            contractNumber = "32216549871",
            label = "Compte de dépôt",
            productCode = "00004",
            balance = 2031.84,
            order = 1,
            operations = listOf(
                Operation(
                    id = "2",
                    title = "Prélèvement Netflix",
                    amount = toDoubleAmount("-15,99").toString(),
                    category = "leisure",
                    date = Date(1644870724L * 1000)
                ),
                Operation(
                    id = "4",
                    title = "CB Amazon",
                    amount = toDoubleAmount("-95,99").toString(),
                    category = "online",
                    date = Date(1644611558L * 1000)
                )
            )
        ),
    )
)

val caBankEst = Bank(
    name = "CA Centre-Est",
    isCA = 1,
    accounts = listOf(
        Account(
            id = "3982938",
            holder = "Corinne Martin",
            role = 1,
            contractNumber = "32216549871",
            label = "Compte de dépôt",
            productCode = "00004",
            balance = 425.84,
            order = 1,
            operations = listOf(
                Operation(
                    id = "2",
                    title = "Tenue de compte",
                    amount = toDoubleAmount("-1,99").toString(),
                    category = "costs",
                    date = Date(1644870724L * 1000)
                ),
                Operation(
                    id = "2",
                    title = "Prélèvement Orange",
                    amount = toDoubleAmount("-45,99").toString(),
                    category = "leisure",
                    date = Date(1644870724L * 1000)
                )
            )
        )
    )
)

val operation = Operation(
    id = "3",
    title = "Tenue de compte",
    amount = toDoubleAmount("-1,99").toString(),
    category = "costs",
    date = Date(1641760369L * 1000)
)

val boursorama = Bank(
    name = "Boursorama",
    isCA = 0,
    accounts = listOf(
        Account(
            id = "09878900000",
            holder = "Corinne Martin",
            role = 1,
            contractNumber = "32216549871",
            label = "Compte de dépôt",
            productCode = "00004",
            balance = 45.84,
            order = 1,
            operations = listOf(
                Operation(
                    id = "2",
                    title = "Tenue de compte",
                    amount = toDoubleAmount("-1,99").toString(),
                    category = "costs",
                    date = Date(1588690878L * 1000)
                ),
                Operation(
                    id = "3",
                    title = "Tenue de compte",
                    amount = toDoubleAmount("-1,99").toString(),
                    category = "costs",
                    date = Date(1641760369L * 1000)
                )
            )
        )
    )
)

val jmAccount = Account(
    id = "3982997777",
    holder = "Jean Martin",
    role = 1,
    contractNumber = "32216549871",
    label = "Compte Chèques",
    productCode = "00004",
    balance = 675.04,
    order = 1,
    operations = listOf(
        Operation(
            id = "2",
            title = "Prêt immo",
            amount = toDoubleAmount("-1331,44").toString(),
            category = "costs",
            date = Date(1644179569L * 1000)
        ),
        Operation(
            id = "2",
            title = "CB La Vie Claire",
            amount = toDoubleAmount("-53,20").toString(),
            category = "food",
            date = Date(1644784369L * 1000)
        ),
        Operation(
            id = "3",
            title = "Prélèvement Spotify",
            amount = toDoubleAmount("-10,00").toString(),
            category = "leisure",
            date = Date(1644611558L * 1000)
        ),
        Operation(
            id = "4",
            title = "CB Billets SNCF",
            amount = toDoubleAmount("-53,00").toString(),
            category = "trip",
            date = Date(1644870724L * 1000)
        )
    )
)

val bankPop = Bank(
    name = "Banque Pop",
    isCA = 0,
    accounts = listOf(jmAccount)
)


object PreviewData {
    val sampleCaBank = caBank
    val sampleBanksCa = listOf(caBank, caBankEst)
    val sampleOtherBank = listOf(bankPop, boursorama)
    val sampleAccount = jmAccount
    val sampleOperation = operation
}
