package x.y

fun main() {

    val t1 = System.currentTimeMillis()
    //val g1 = gridTraveller(4000, 4000, HashMap())
    val g1 = gridTravellerTable(18, 18)
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

fun gridTravellerTable(m: Int, n: Int): Long {

    val arr = Array(m + 1 ) { i ->
        Array(n + 1) {
            0L
        }
    }

    arr[1][1] = 1
    for (i in 0..m) {
        for (j in 0..n) {
            val current = arr[i][j]
            if (j+1 <= n) {
                arr[i][j+1] += current
            }
            if (i+1 <= m) {
                arr[i+1][j] += current
            }
        }
    }

    /*for (i in 0..m) {
        for (j in 0..n) {
            val current = arr[i][j]
            print(" %12d ".format(current))
        }
        println()
    }*/

    return arr[m][n]
}