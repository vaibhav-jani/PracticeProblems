package x.y

fun main() {

    val g1 = gridTraveller(40, 40, HashMap())
    println(g1)
}

fun gridTraveller(m: Long, n: Long, memo: HashMap<String, Long>): Long {

    val key = "$m,$n"
    if (memo.containsKey(key)) {
        return memo[key]!!
    }

    if (m == 0L || n == 0L) {
        return 0L
    }

    if (m == 1L || n == 1L) {
        return 1L
    }

    val sum = gridTraveller(m - 1, n, memo) + gridTraveller(m, n - 1, memo)
    memo[key] = sum
    return sum
}