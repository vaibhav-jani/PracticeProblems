package x.y

fun main(args: Array<String>) {

    val rt = Runtime.getRuntime()

    for (n in 1L..60L) {
        val mb = 1024.0 * 1024.0
        val total = rt.totalMemory() / mb
        val free = rt.freeMemory() / mb
        val max = rt.maxMemory() / mb
        val processors = rt.availableProcessors()
        val t1 = System.currentTimeMillis()
        val fib = fib(n)
        val t2 = System.currentTimeMillis()
        println("Fib: $n: $fib [Took: %.2f] seconds".format((t2 - t1) / 1000.0))
        println("---------------------------------------------------")
        println((" Total: %.2f, " +
                "  Free: %.2f " +
                "  Max: %.2f " +
                "  Processors: %d "
                ).format(total, free, max, processors))
        println("===================================================")
    }
}


// 0 1 2 3 4 5  6  7  8
// 1 1 2 3 5 8 13 21 34
fun fib(n: Long): Long {

    if (n < 2) return 1

    return fib(n - 2) + fib(n - 1)
}