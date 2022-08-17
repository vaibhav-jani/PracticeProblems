package x.y

fun main() {

    /*val arr1 = arrayOf(5L, 3L, 4L, 7L)
    val memo = HashMap<Long, Boolean>()
    val result = ArrayList<Long>()
    val sum = 7L*/

    val arr1 = arrayOf(14L, 7L)
    val memo = HashMap<Long, ArrayList<Long>?>()
    val sum = 350L

    /*val arr1 = arrayOf(1L, 2L, 5L, 25L)
    val memo = HashMap<Long, ArrayList<Long>?>()
    val sum = 100L*/

    val result = howSum(sum, arr1, memo)
    println("$result")

}
fun howSum(sum: Long, arr: Array<Long>, memo: HashMap<Long, ArrayList<Long>?>): ArrayList<Long>? {

    if (memo.containsKey(sum)) {
        return memo[sum]
    }

    if (sum == 0L) {
        return ArrayList()
    }

    if (sum < 0) {
        return null
    }

    arr.forEach { number ->
        val reminder = sum - number
        val howSum = howSum(reminder, arr, memo)
        if (howSum != null) {
            howSum.add(number)
            memo[sum] = howSum
            return howSum
        }
    }

    memo[sum] = null
    return null
}