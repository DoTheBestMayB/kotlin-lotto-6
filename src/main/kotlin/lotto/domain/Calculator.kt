package lotto.domain

import lotto.data.GRADE
import lotto.data.Lotto
import kotlin.math.roundToInt

class Calculator {

    fun getQuotientAndRemainder(dividend: UInt, divisor: UInt): Pair<Int, Int> {
        val quotient = (dividend / divisor).toInt()
        val remainder = (dividend % divisor).toInt()

        return quotient to remainder
    }

    fun calculateTotalProfit(countOfWin: List<Int>) =
        countOfWin.foldIndexed(0) { rank: Int, sum: Long, count: Int ->
            if (rank == GRADE.LOSE.rank()) {
                return@foldIndexed sum
            }
            sum + GRADE.fromRank(rank).price().toLong() * count
        }

    fun calculateProfitRate(totalProfit: Long, sizeOfTicket: Int): Float {
        return ((totalProfit.toFloat() / (Lotto.PRICE.toFloat() * sizeOfTicket) * ROUND_FACTOR)).roundToInt() / ROUND_FACTOR
    }

    companion object {
        private const val ROUND_FACTOR = 1_000f
    }
}