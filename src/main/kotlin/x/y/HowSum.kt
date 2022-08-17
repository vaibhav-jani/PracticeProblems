package x.y

fun main() {

    /*val arr1 = arrayOf(5L, 3L, 4L, 7L)
    val memo = HashMap<Long, Boolean>()
    val result = ArrayList<Long>()
    val sum = 7L*/

    val arr1 = arrayOf(14L, 7L)
    val memo = HashMap<Long, Boolean>()
    val result = ArrayList<Long>()
    val sum = 300L

    println("${howSum(sum, arr1, memo, result)}")
    println("canSum() called with: sum = $sum, $result")

}

fun howSum(sum: Long, arr: Array<Long>, memo: HashMap<Long, Boolean>, result: ArrayList<Long>): Boolean {

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
        val howSum = howSum(reminder, arr, memo, result)
        memo[sum] = howSum
        result.add(number)
        if (howSum) {
            return true
        } else {
            result.clear()
        }
    }

    return false
}