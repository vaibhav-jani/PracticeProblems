
fun main() {

    val memo = HashMap<String, Int>()

    /*val str = "abcdef"
    val arr = arrayOf("ab", "bc", "def", "abc", "abcd", "ef", "abcdef", "c")*/

    /*val str = "purple"
    val arr = arrayOf("purp", "p", "ur", "le", "purpl")*/

    /*val str = "abcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyz abcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyz"
    val arr = arrayOf(" ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")*/

    val str = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef"
    val arr = arrayOf("e", "ee", "eee", "eeee", "eeeee", "eeeeee")

    /*val str = "enterapotentpot"
    val arr = arrayOf("a", "p", "ent", "enter", "ot", "o", "t")*/
    println(countConstruct(str, arr, memo))
}

fun countConstruct(
    str: String,
    arr: Array<String>,
    memo: HashMap<String, Int>
): Int {

    if (memo.contains(str)) {
        return memo[str]!!
    }

    println("countConstruct() called with: str = $str")

    if (str.isEmpty()) {
        return 1
    }

    var count = 0
    arr.forEach { value ->
        if (str.startsWith(value)) {
            val remaining = str.replaceFirst(value, "")
            val countConstruct = countConstruct(remaining, arr, memo)
            count += countConstruct
        }
        println("--------------------------------")
    }
    println("===================================")
    memo[str] = count
    return count
}