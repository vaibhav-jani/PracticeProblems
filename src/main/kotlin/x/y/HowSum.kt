package x.y

fun main() {

    /*val arr1 = arrayOf(5L, 3L, 4L, 7L)
    val memo = HashMap<Long, Boolean>()
    val result = ArrayList<Long>()
    val sum = 7L*/

    /*val arr1 = arrayOf(14L, 7L)
    val memo = HashMap<Long, ArrayList<Long>?>()
    val sum = 350L*/

    /*val arr1 = arrayOf(1L, 2L, 5L, 25L)
    val memo = HashMap<Long, ArrayList<Long>?>()
    val sum = 100L*/

    val arr1 = arrayOf(4, 7)
    val sum = 22
    val result = howSumTable(sum, arr1)
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

fun howSumTable(sum: Int, arr: Array<Int>): ArrayList<Int>? {

    val table = Array<ArrayList<Int>?>(sum + 1) {
        null
    }

    table[0] = ArrayList()
    for (index in 0..sum) {
        if (table[index] != null) {
            arr.forEach { number ->
                if (index + number <= sum) {
                    val newList = ArrayList<Int>()
                    table[index]?.let {
                        newList.addAll(it)
                    }
                    table[index + number] = newList
                    table[index + number]?.add(number)
                }
            }
        }
    }

    for (index in 0..sum) {
        println(" $index: ${table[index]} ")
    }

    return table[sum]
}