package x.y

fun main(args: Array<String>) {

    loops()

    stringAndArrays()

    arrays()
}

fun loops() {

    for (i in 1 .. 10 step 2) {
        println(i)
    }

    println("-------------------")

    for (i in 1 .. 10 step 2) {
        println(i)
    }

    println("-------------------")

    for (i in (1 .. 10).reversed() step 2) {
        println(i)
    }

    println("-------------------")

    for (i in (1 .. 10).reversed().filter { it % 4 == 0 } ) {
        println(i)
    }

    println("-------------------")

    for (i in 10 downTo 1 step 2) {
        println(i)
    }

    println("-------------------")
}

fun stringAndArrays() {
    val splits = "abd".split("") // ['', 'a', 'b', 'c', '']
    splits.forEachIndexed { index, split ->
        println("$index: $split")
    }
    println("-------------------")
    val strToArray = "abc.def"
        .replace(".", "") //"abcdef"
        .substring(0, 3) // "abc"
        .toCharArray() // ['a', 'b', 'c']

    strToArray.forEachIndexed { index, char ->
        println("$index: $char")
    }

    println("-------------------")
    val filter = strToArray.filter {
        it.code > 'a'.code
    }

    filter.forEach { value ->
        println(value)
    }

    println("-------------------")
    val strArray = arrayOf("", "", "")
    val intArray = arrayOf(1, 2, 3)
    val mixedArray = arrayOf(1.4, "", 1, 5L, 3f)

    val value = mixedArray[3]
    val exp = ((value as? Long) ?: 0) + 4
}

fun arrays() {

    val a = Array(20) { i ->
        Array(20) { j ->
            Array(20) { k ->
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