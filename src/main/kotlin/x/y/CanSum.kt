package x.y

fun main() {

    val arr1 = arrayOf(7L, 14L)
    val memo = HashMap<Long, Boolean>()
    println("${canSum(42001L, arr1, memo)}")

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