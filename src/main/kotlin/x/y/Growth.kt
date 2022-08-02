
fun main() {
    
    println()
    
    val base = 65000 // Monthly pay
    val growth0 = arrayOf(0f, 0.05f, 0.05f, 0.05f, 0.05f) // Growth rate
    val durations0 = arrayOf(12, 12, 12, 12, 12) // Months
    
    print(base, growth0, durations0, "5 Years")
    println()
    
    val growth1 = arrayOf(0f, 0.15f, 0.15f, 0.15f, 0.15f, 0.10f, 0.10f, 0.05f, 0.05f, 0.05f)
    val durations1 = arrayOf(12, 12, 12, 12, 12, 12, 12, 12, 12, 12)

    print(base, growth1, durations1, "10 Years")
    println()

}

fun print(base: Int, growth: Array<Float>, durations: Array<Int>, tag: String) {
    var totalDuration = 0
    for (months in durations) {
        totalDuration += months
    }
    println("$tag ($totalDuration Months)")
    println("---------")
    val results = GrowthRate.calculate(base, growth, durations)
    var total = 0f
    results.forEachIndexed { index, value ->
        total += value
        val monthly = value / durations[index]
        val valueStr = String.format("%.2f", value)
        println("${index + 1} [${durations[index]} * $monthly]: $valueStr")
    }
    println("---------")
    val totalStr = String.format("%.2f", total)
    println("  $totalStr")
}

/**
 * duration in months
 * rates in percent 0 to 1
 */
object GrowthRate {
    
    fun calculate(base: Int, rates: Array<Float>, durations: Array<Int>): Array<Float> {

        val subTotals = FloatArray(durations.size)
        var currentBase = base.toFloat()
        durations.forEachIndexed { index, duration ->
            val rate = rates[index]
            currentBase += (currentBase * rate)
            subTotals[index] = currentBase * duration
        }
        
        return subTotals.toTypedArray()
    }
    
}