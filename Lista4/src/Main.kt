import java.time.LocalDate
import java.time.Month
import java.util.*
import kotlin.random.*

//enum typy kosztow z zad
enum class CostType(val costType: String) {
    REFUELING("Tankowanie"),
    SERVICE("Serwis"),
    PARKING("Parking"),
    INSURANCE("Ubezpieczenie"),
    TICKET("Mandat")
}

//paragon z zad
data class Cost(
    val type: CostType,
    val date: LocalDate,
    val amount: Int
)

// data prvoder z zad
object DataProvider {
    val generalCosts = List(20) {
        Cost(
            CostType.values()[Random.nextInt(CostType.values().size)],
            LocalDate.of(
                2025,
                Random.nextInt(1, 13),
                Random.nextInt(1, 28)
            ),
            Random.nextInt(5000)
        )
    }
}

// Zadanie 1 funkcja przyjmującą listę kosztów List<Cost>, która zwróci mapę kosztów pogrupowaną według miesiąców i posortowaną rosnąco
fun groupedCostMap(costs: List<Cost>): Map<Month, List<Cost>> {
    return costs
        .groupBy { it.date.month }
        .toSortedMap()
}

// Zadanie 2
fun printCostsByMonth(costs: List<Cost>) {
    costs
        .groupBy { it.date.month }
        .toSortedMap()
        .forEach { (month, monthCosts) ->
            println(month.name)
            monthCosts
                .sortedBy { it.date.dayOfMonth }
                .forEach { cost ->
                    println("${cost.date.dayOfMonth.toString().padStart(2, '0')} ${cost.type.costType} ${cost.amount} zł")
                }
            println()
        }
}

// Zadanie 3
sealed class MonthlyCostStatus {
    object NoCosts : MonthlyCostStatus()
    data class WithinLimit(val total: Int) : MonthlyCostStatus()
    data class OverLimit(val total: Int, val exceededBy: Int) : MonthlyCostStatus()
}

fun classifyMonthlyCosts(costs: List<Cost>, month: Month, limit: Int): MonthlyCostStatus {
    val monthlyCosts = costs.filter { it.date.month == month }

    return if (monthlyCosts.isEmpty()) {
        MonthlyCostStatus.NoCosts
    } else {
        val total = monthlyCosts.sumOf { it.amount }
        when {
            total <= limit -> MonthlyCostStatus.WithinLimit(total)
            else -> MonthlyCostStatus.OverLimit(total, total - limit)
        }
    }
}

// Zadanie 4
interface CostFormatter {
    fun format(cost: Cost): String
}

object PlCostFormatter : CostFormatter {
    override fun format(cost: Cost): String {
        return "${cost.date.dayOfMonth.toString().padStart(2, '0')} ${cost.type.costType} ${cost.amount} zł"
    }
}

fun formatCosts(costs: List<Cost>, formatter: CostFormatter): String {
    return costs
        .sortedBy { it.date }
        .joinToString("\n") { formatter.format(it) }
}

fun main(){
    //Zad 1
    println(groupedCostMap(DataProvider.generalCosts))

//Zad 2
    printCostsByMonth(DataProvider.generalCosts)

//Zad 3
    val costs = listOf(
        Cost(CostType.REFUELING, LocalDate.of(2025, 1, 10), 300),
        Cost(CostType.PARKING, LocalDate.of(2025, 1, 12), 50),
        Cost(CostType.SERVICE, LocalDate.of(2025, 2, 4), 1200)
    )

    println(classifyMonthlyCosts(costs, Month.JANUARY, 400))      // WithinLimit(total=350)
    println(classifyMonthlyCosts(costs, Month.FEBRUARY, 1000))    // OverLimit(total=1200, exceededBy=200)
    println(classifyMonthlyCosts(costs, Month.MARCH, 500))        // NoCosts

// Zad 4
    val costs4 = listOf(
        Cost(CostType.PARKING, LocalDate.of(2025, 1, 15), 30),
        Cost(CostType.SERVICE, LocalDate.of(2025, 1, 5), 900)
    )
    println(formatCosts(costs4, PlCostFormatter))

}