package x.y

import java.math.BigInteger

fun main(args: Array<String>) {

    val memo: HashMap<Int, Long> = HashMap()

    for (n in 0..1000) {
        val t1 = System.currentTimeMillis()
        //val fib = fib(n, memo)
        val fib = fibLoop(n)
        val t2 = System.currentTimeMillis()
        println("Fib: $n: $fib [Took: %.2f] seconds".format((t2 - t1) / 1000.0))
        println("---------------------------------------------------")
    }

    printDeviceInfo()
}

fun printDeviceInfo() {
    val rt = Runtime.getRuntime()
    val mb = 1024.0 * 1024.0
    val total = rt.totalMemory() / mb
    val free = rt.freeMemory() / mb
    val max = rt.maxMemory() / mb
    val processors = rt.availableProcessors()
    println(
        (" Total: %.2f, " +
                "  Free: %.2f " +
                "  Max: %.2f " +
                "  Processors: %d "
                ).format(total, free, max, processors)
    )
    println("===================================================")
}


// 0 1 2 3 4 5  6  7  8
// 1 1 2 3 5 8 13 21 34
fun fib(n: Int, memo: HashMap<Int, Long>): Long {

    if (memo.containsKey(n)) {
        return memo[n]!!
    }

    if (n < 2) return 1

    val result = fib(n - 2, memo) + fib(n - 1, memo)
    memo[n] = result
    return result
}

// 0 1 2 3 4 5 6  7  8  9 10
// 0 1 1 2 3 5 8 13 21 34 55
fun fibLoop(n: Int): BigInteger {

    if (n == 0) {
        return BigInteger.valueOf(0L)
    }

    if (n == 1 || n == 2) {
        return BigInteger.valueOf(1L)
    }

    val arr = Array(n + 1) { _ ->
        BigInteger.valueOf(0L)
    }

    var first = 0
    var second = 1
    var third = 2

    arr[first] = BigInteger.valueOf(0L)
    arr[second] = BigInteger.valueOf(1L)

    for (index in 2..n) {
        arr[third] = arr[first] + arr[second] + arr[third]
        first++
        second++
        third++
    }

    return arr[n]
}