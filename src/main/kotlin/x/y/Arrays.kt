package x.y

fun main() {

    val a = Array(20) { i ->
        Array(20) { j ->
            IntArray(20) { k ->
                (i + 1) * (j + 1) * (k + 1)
            }
        }
    }

    for (array2 in a) {
        for (array1 in array2) {
            for (i in 0 .. array2.size - array1.size) {
                print(" %4s ".format(""))
            }
            for (value in array1) {
                print(" %4d ".format(value))
            }
            println()
        }
        println()
    }
}