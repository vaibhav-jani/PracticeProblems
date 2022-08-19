
fun main() {

    val memo = HashMap<String, ArrayList<ArrayList<String>>>()

    /*val str = "abcdef"
    val arr = arrayOf("bc", "def","a", "b", "c", "d", "ab", "abc", "cd", "ef")*/

    val str = "purple"
    val arr = arrayOf("purp", "p", "ur", "le", "purpl")
    //val arr = arrayOf("ab", "abc", "cd", "def", "abcd", "ef", "c")

    /*val str = "purple"
    val arr = arrayOf("purp", "p", "ur", "le", "purpl")*/

    /*val str = "abcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyz abcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyz"
    val arr = arrayOf(" ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")*/

    /*val str = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef"
    val arr = arrayOf("e", "ee", "eee", "eeee", "eeeee", "eeeeee")*/

    /*val str = "enterapotentpot"
    val arr = arrayOf("a", "p", "ent", "enter", "ot", "o", "t")*/
    println(allConstruct(str, arr, memo))
}

fun allConstruct(
    str: String,
    arr: Array<String>,
    memo: HashMap<String, ArrayList<ArrayList<String>>>
): ArrayList<ArrayList<String>> {

    if (memo.contains(str)) {
        return memo[str]!!
    }

    println("countConstruct() called with: str = $str")

    if (str.isEmpty()) {
        val list = ArrayList<ArrayList<String>>()
        list.add(ArrayList())
        return list
    }

    val arrayList = ArrayList<ArrayList<String>>()
    arr.forEach { word ->
        if (str.startsWith(word)) {
            val remaining = str.replaceFirst(word, "")
            val suffixWays = allConstruct(remaining, arr, memo)
            val suffixWaysClone: ArrayList<ArrayList<String>> = suffixWays.let {
                val newList = ArrayList<ArrayList<String>>()
                newList.addAll(it)
                newList
            }
            val updatedSuffixWays = ArrayList<ArrayList<String>>()
            if (suffixWaysClone.isNotEmpty()) {
                suffixWaysClone.forEach { suffixWay ->
                    val suffixWayClone: ArrayList<String> = suffixWay.let {
                        val newList = ArrayList<String>()
                        newList.addAll(suffixWay)
                        newList
                    }
                    suffixWayClone.add(0, word)
                    updatedSuffixWays.add(suffixWayClone)
                }
                println("combination: $suffixWays")
            }
            arrayList.addAll(updatedSuffixWays)
        }

        println("--------------------------------")
    }
    println("===================================")
    memo[str] = arrayList
    return arrayList
}

