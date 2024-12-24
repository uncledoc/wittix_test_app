package com.example.myapplication.screen

import com.example.myapplication.resources.Res
import com.example.myapplication.resources.cur_eur
import com.example.myapplication.resources.cur_ils
import com.example.myapplication.view.DropdownTextFieldViewItem

enum class Currency {
    EUR, ILS
}

fun Currency.toDropDown() = DropdownTextFieldViewItem(
    when (this) {
        Currency.EUR -> Res.drawable.cur_eur
        Currency.ILS -> Res.drawable.cur_ils
    }, name
)

fun Currency.toChar() =
    when (this) {
        Currency.EUR -> "€"
        Currency.ILS -> "₪"
    }

fun DropdownTextFieldViewItem.toCurrency() = when (title) {
    Currency.EUR.name -> Currency.EUR
    Currency.ILS.name -> Currency.ILS
    else -> throw IllegalArgumentException("Unknown item ${toString()}")
}

data class UserBalance(
    val balance: Double,
    val available: Double,
    val currency: Currency
)

data class ExchangeRate(
    val from: Currency,
    val to: Currency,
    val rate: Double
)

data class DashboardScreenState(
    val balances: List<UserBalance> = listOf(
        UserBalance(12203.00, 100.00, Currency.EUR),
        UserBalance(163003.00, 99.00, Currency.ILS)
    ),
    val exchangeRate: List<ExchangeRate> = listOf(
        ExchangeRate(Currency.EUR, Currency.ILS, 3.79),
        ExchangeRate(Currency.ILS, Currency.EUR, 0.26)
    ),
    val currencies: List<DropdownTextFieldViewItem> = Currency.entries.map { it.toDropDown() },

    val fromCurrency: Currency = Currency.EUR,
    val toCurrency: Currency = Currency.ILS,

    val purposeOptions: List<String> = listOf("Purpose", "Purpose 1", "Purpose 2"),

    val isRegular: Boolean = false,
    val link: String = "https://www.wittix.com/",
) {
    val estimatedRate: String
        get() {
            val eur = exchangeRate[0]
            val ils = exchangeRate[1]
            return "1 ${eur.from.name} = ${eur.rate} ${eur.to.name} " +
                    "(1 ${ils.from.name} = ${ils.rate} ${ils.to.name})"
        }

    val balanceCurrent: String
        get() {
            val balance = balances.find { it.currency == fromCurrency }!!
            return balance.currency.toChar() +  balance.balance.toString()
        }

    val balanceAvailable: String
        get() {
            val balance = balances.find { it.currency == fromCurrency }!!
            return balance.currency.toChar() +  balance.available.toString()
        }

    fun rateCurrent(): ExchangeRate? =
        exchangeRate.find { it.from == fromCurrency && it.to == toCurrency }

    fun rateRevert(): ExchangeRate? =
        exchangeRate.find { it.from == toCurrency && it.to == fromCurrency }
}
