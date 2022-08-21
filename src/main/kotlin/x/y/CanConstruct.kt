package x.y

import java.util.*
import kotlin.collections.HashMap

fun main() {

    //val memo = HashMap<String, Boolean>()

    /*val str = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef"
    val arr = arrayOf("e", "ee", "eee", "eeee", "eeeee", "eeeeee")
    println(canConstruct(str, arr, memo))*/

    /*val str = "abcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyz abcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyz"
    val arr = arrayOf(" ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")
    println(canConstruct(str, arr, memo))*/

    /*val str = "abcdef"
    val arr = arrayOf("ab", "bc", "def", "abc", "ef")*/
    val str = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef"
    val arr = arrayOf("e", "ee", "eee", "eeee", "eeeee", "eeeeee")
    println(canConstructTable(str, arr))

}

fun canConstruct(
    str: String,
    arr: Array<String>,
    memo: HashMap<String, Boolean>
): Boolean {

    println("canConstruct() called with: str = $str")
    if (memo.containsKey(str)) {
        return memo[str]!!
    }
    if (str.isEmpty()) {
        return true
    }

    arr.forEach { value ->
        if (str.startsWith(value)) {
            val canConstruct = canConstruct(str.replaceFirst(value, ""), arr, memo)
            if (canConstruct) {
                memo[str] = true
                return true
            }
        }
    }

    memo[str] = false
    return false
}

fun canConstructBruteForce(
    str: String,
    arr: Array<String>
): Boolean {
    println("canConstruct() called with: str = $str")
    if (str.isEmpty()) {
        return true
    }

    arr.forEach { value ->
        if (str.startsWith(value)) {
            if (canConstructBruteForce(str.replaceFirst(value, ""), arr)) {
                return true
            }
        }
    }
    return false
}


fun canConstructStack(
    str: String,
    arr: Array<String>
): Boolean {

    println("canConstruct() called with: str = $str")
    if (str.isEmpty()) {
        return true
    }

    val stack = Stack<String>()
    arr.forEach { value ->
        if (str.startsWith(value)) {
            stack.push(str.replaceFirst(value, ""))
        }
    }

    println("stack = $stack")
    while (!stack.isEmpty()) {
        return canConstructStack(stack.pop(), arr)
    }

    return false
}

fun canConstructTable(
    str: String,
    arr: Array<String>
): Boolean {

    val table = Array(str.length + 1) {
        false
    }

    table[0] = true
    for (index in 0..str.length) {
        if (table[index]) {
            arr.forEach { word ->
                if (str.substring(index, str.length).startsWith(word)) {
                    val wordLength = word.length
                    if (index + wordLength <= str.length) {
                        table[index + wordLength] = true
                    }
                    println("$index ${word} : ${table[index]}")
                    println("-----------------------")
                }
            }
        }
    }

    for (index in 0..str.length) {
        println("$index : ${table[index]}")
    }

    return table[str.length]
}