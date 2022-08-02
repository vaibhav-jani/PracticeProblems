package x.y

fun main() {
    val tests = arrayOf("qrsvbspk",
        "",
        "abcabcbb",
        "bbbbb",
        "pwwkew",
        "tmmzuxt",
        "ggububgvfk",
    )
    tests.forEach { input ->
        println()
        println(input)
        println("----------------------------------")
        val result = lengthOfLongestSubstring(input)
        val result2 = lengthOfLongestSubstring2(input)
        println("==================================")
        println("result: ${result}, result2: ${result2}")
        println()
    }
}

fun lengthOfLongestSubstring2(s: String): Int {

    val arr = s.toCharArray()

    val size = arr.size
    val lastIndex = size - 1

    var left = 0
    var right = 0

    val set = HashSet<Char>(size)
    var maxLengthFound = 0

    while (right <= lastIndex) {

        val currentChar: Char = arr[right]
        if (set.contains(currentChar)) {
            while (arr[left] != currentChar) {
                set.remove(arr[left])
                ++left
            }
            ++left
        }

        set.add(currentChar)
        ++right

        if (right - left > maxLengthFound) {
            maxLengthFound = right - left
        }
        println("${s.substring(left, right)}, ${set}")
    }

    return maxLengthFound
}

fun lengthOfLongestSubstring(s: String): Int {

    val chars = s.toCharArray()

    val size = chars.size
    val lastIndex = size - 1

    var left = 0
    var right = 0

    val count: Array<Int> = Array(128) { index ->
        0
    }

    var maxLengthFound = 0

    while (right <= lastIndex) {

        val currentChar = chars[right]
        count[currentChar.code]++

        print("$currentChar: ${count[currentChar.code]} | ")
        print("left: $left, right: $right | ")
        while (count[currentChar.code] > 1) {
            count[chars[left].code]--
            left++
        }

        print("$currentChar: ${count[currentChar.code]} | ")
        print("left: ${left}, right: ${right} | ")

        val currentLength = right - left + 1
        if (currentLength > maxLengthFound) {
            maxLengthFound = currentLength
        }

        right++
        print(s.substring(left, right))
        count.forEachIndexed { index, value ->
            if (value >= 1) {
                print(", ${index.toChar()} = $value")
            }
        }

        println()

    }

    return maxLengthFound
}