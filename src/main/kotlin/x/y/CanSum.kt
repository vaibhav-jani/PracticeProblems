package x.y

fun main() {

    //val arr1 = arrayOf(7L, 14L)
    //val memo = HashMap<Long, Boolean>()
    //println("${canSum(42000L, arr1, memo)}")
    val arr1 = arrayOf(9, 7, 14)
    println("${canSumTable(50, arr1)}")

}

fun canSum(sum: Long, arr: Array<Long>, memo: HashMap<Long, Boolean>): Boolean {

    println("canSum() called with: sum = $sum, $memo")

    if (memo.containsKey(sum)) {
        return memo[sum]!!
    }

    if (sum == 0L) {
        return true
    }

    if (sum < 0) {
        return false
    }

    arr.forEach { number ->
        val reminder = sum - number
        val canSum = canSum(reminder, arr, memo)
        memo[sum] = canSum
        if (canSum) {
            return true
        }
    }

    return false
}

fun canSumTable(
    sum: Int,
    arr: Array<Int>
): Boolean {

    val table = Array(sum + 1) {
        false
    }

    table[0] = true

    for (index in 0..sum) {
        if (table[index]) {
            arr.forEach { number ->
                if (index + number <= sum) {
                    table[index + number] = true
                }
            }
        }
    }

    for (index in 0..sum) {
        println(" $index: ${table[index]} ")
    }

    return table[sum]
}