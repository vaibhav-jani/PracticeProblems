package x.y

fun main() {

    val t1 = System.currentTimeMillis()
    val g1 = gridTraveller(4000, 4000, HashMap())
    val t2 = System.currentTimeMillis()
    println("[Took: ${t2 - t1}]")
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