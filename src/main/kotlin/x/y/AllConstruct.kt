fun main() {

    val memo = HashMap<String, ArrayList<ArrayList<String>>>()

    val str = "abcdef"
    val arr = arrayOf("bc", "def","a", "b", "c", "d", "ab", "abc", "cd", "ef")

    /*val str = "purple"
    val arr = arrayOf("purp", "p", "ur", "le", "purpl")*/
    //val arr = arrayOf("ab", "abc", "cd", "def", "abcd", "ef", "c")

    /*val str = "purple"
    val arr = arrayOf("purp", "p", "ur", "le", "purpl")*/

    /*val str = "abcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyz abcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyzabcdefghijklmopqrstuvwxyz"
    val arr = arrayOf(" ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")*/

    /*val str = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef"
    val arr = arrayOf("e", "ee", "eee", "eeee", "eeeee", "eeeeee")*/

    /*val str = "enterapotentpot"
    val arr = arrayOf("a", "p", "ent", "enter", "ot", "o", "t")*/
    println(allConstructTable(str, arr))
}

fun allConstruct(
    str: String,
    arr: Array<String>,
    memo: HashMap<String, ArrayList<ArrayList<String>>>
): ArrayList<ArrayList<String>> {

    if (memo.contains(str)) {
        return memo[str]!!
    }

    println("allConstruct() called with: str = $str")

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
            }
            arrayList.addAll(updatedSuffixWays)
        }

    }
    memo[str] = arrayList
    return arrayList
}

fun allConstructTable(
    target: String,
    words: Array<String>
): ArrayList<ArrayList<String>> {

    val table = Array(target.length + 1) {
        ArrayList<ArrayList<String>>()
    }

    val first = table[0]
    first.add(ArrayList())
    table[0] = first

    for (index in 0..target.length) {
        val current = table[index]
        if (current.isNotEmpty()) {
            words.forEach { word ->
                if (target.substring(index, target.length).startsWith(word)) {
                    val wordLength = word.length
                    if (index + wordLength <= target.length) {
                        // Cloning to avoid references
                        val newList = ArrayList<ArrayList<String>>()
                        current.forEach { combination ->
                            // Cloning to avoid references
                            val newCombination = ArrayList<String>()
                            newCombination.addAll(combination)
                            newCombination.add(word)
                            newList.add(newCombination)
                        }
                        table[index + wordLength].addAll(newList)
                    }
                }
            }
        }
    }

    for (index in 0..target.length) {
        println(" $index: ${table[index]}")
    }
    return table[target.length]
}