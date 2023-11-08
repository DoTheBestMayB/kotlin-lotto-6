package lotto.domain

import lotto.data.*


class Analyzer(private val calculator: Calculator) {

    fun getStats(tickets: List<Lotto>, winningLotto: WinningLotto): Stats {
        val countOfWin = IntArray(GRADE.entries.size)
        for (ticket in tickets) {
            countOfWin[ticket.checkGrade(winningLotto).rank().index]++
        }
        val totalProfit = calculator.calculateTotalProfit(countOfWin.toList())
        return Stats(
            info = WinningInfo.from(countOfWin.toList()),
            profitRate = calculator.calculateProfitRate(totalProfit, tickets.size)
        )
    }
}