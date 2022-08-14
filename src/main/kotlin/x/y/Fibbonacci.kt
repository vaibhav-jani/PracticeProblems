package x.y

fun main(args: Array<String>) {

    val memo: HashMap<Int, Long> = HashMap()

    for (n in 1..60) {
        val t1 = System.currentTimeMillis()
        //val fib = fib(n, memo)
        val fib = fibLoop(n - 1)
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
    println((" Total: %.2f, " +
            "  Free: %.2f " +
            "  Max: %.2f " +
            "  Processors: %d "
            ).format(total, free, max, processors))
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
    //memo[n] = result
    return result
}

// 0 1 2 3 4 5  6  7  8
// 1 1 2 3 5 8 13 21 34
fun fibLoop(n: Int): Long {
    var result = 0L
    var n1 = 0L
    var n2 = 1L
    var n3 = 0L
    for (i in 0L..n) {
        n3 = n1 + n2
        n1 = n2
        n2 = n3
        print(" $n2 ")
        result += n3
    }

    //memo[n] = result
    return result
}