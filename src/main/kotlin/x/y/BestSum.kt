package x.y

fun main() {

    val arr1 = arrayOf(1L, 2L, 4L, 5L, 10L, 25L)
    val memo = HashMap<Long, ArrayList<Long>?>()
    val sum = 100L

    val result = bestSum(sum, arr1, memo)
    println("--------------------------------------")
    println("$result")

}

fun bestSum(sum: Long, arr: Array<Long>, memo: HashMap<Long, ArrayList<Long>?>): ArrayList<Long>? {
    // println("bestSum() called with: sum = $sum, memo = $memo")

    if (memo.containsKey(sum)) {
        return memo[sum]
    }

    if (sum == 0L) {
        return ArrayList()
    }

    if (sum < 0) {
        return null
    }

    var shortestPath: ArrayList<Long>? = null

    arr.forEach { number ->
        val reminder = sum - number
        val bestSum = bestSum(reminder, arr, memo)
        val combination: ArrayList<Long>? = bestSum?.let {
            val newList = ArrayList<Long>()
            newList.addAll(it)
            newList
        }

        if (combination != null) {
            combination.add(number)
            if(shortestPath == null || (shortestPath?.size ?: -1) > combination.size) {
                shortestPath = combination
            }
        }
    }

    memo[sum] = shortestPath
    println("$sum: $shortestPath")
    return shortestPath
}