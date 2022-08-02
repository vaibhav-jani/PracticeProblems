package x.y

import java.util.*

fun main() {

    val arr= intArrayOf(5, 9, 10, 11, 6, 2, 7)
    val result = oddEvenJumps2(arr)
    println("$result")
}

fun oddEvenJumps2(arr: IntArray): Int {

    val size = arr.size
    val lastIndex = arr.size - 1

    val nextSmallestGreaterMap = HashMap<Int, Int>()
    val nextGreatestSmallerMap = HashMap<Int, Int>()

    nextSmallestGreaterMap[lastIndex] = lastIndex
    nextSmallestGreaterMap[lastIndex] = lastIndex

    val treeMap = TreeMap<Int, Int>()
    for (i in lastIndex downTo 0) {
        nextSmallestGreaterMap[i] = findSmallestGreaterIndex(i, arr, treeMap)
        nextGreatestSmallerMap[i] = findLargestSmallerIndex(i, arr, treeMap)
        treeMap[arr[i]] = i
    }

    var goodIndexCount = 0
    for (i in lastIndex downTo 0) {
        var jump = 1
        println("Checking index: $i")
        println("---------------------------------")
        var jumpIndex = i
        while (jumpIndex >= 0) {
            val currentIndex = jumpIndex
            jumpIndex = if (jump%2 == 0) {
                nextGreatestSmallerMap[currentIndex]!!
            } else {
                nextSmallestGreaterMap[currentIndex]!!
            }
            println("jump: $jump, from: $currentIndex -> $jumpIndex")
            if (jumpIndex != -1) {
                ++jump
            }
            if (jumpIndex == lastIndex) {
                ++goodIndexCount
                println("Good Index")
                break
            }
        }
        println("====================================")
    }

    return goodIndexCount
}

fun findSmallestGreaterIndex(
    current: Int,
    arr: IntArray,
    treeMap:TreeMap<Int, Int>
): Int {
    if (current == arr.size - 1) {
        return current
    }
    val value = arr[current]
    val entry = treeMap.ceilingEntry(value)
    return if (entry != null) {
        entry.value
    } else {
        -1
    }
}


fun findLargestSmallerIndex(
    current: Int,
    arr: IntArray,
    treeMap:TreeMap<Int, Int>
): Int {
    if (current == arr.size - 1) {
        return current
    }
    val value = arr[current]
    val entry = treeMap.floorEntry(value)
    return if (entry != null) {
        entry.value
    } else {
        -1
    }
}

fun oddEvenJumps3(arr: IntArray): Int {
    val n = arr.size
    val d = Array(n) {BooleanArray(2)}
    val mp = TreeMap<Int, Int>()
    d[n - 1][0] = true
    d[n - 1][1] = true

    mp[arr[n - 1]] = n - 1

    for (i in n - 2 downTo 0) {
        val odd = mp.ceilingEntry(arr[i])

        if (odd != null) {
            d[i][0] = d[odd.value][1]
        }

        val even = mp.floorEntry(arr[i])

        if (even != null) {
            d[i][1] = d[even.value][0]
        }

        mp[arr[i]] = i
    }

    return d.filter { it[0] }.size
}

fun oddEvenJumps4(arr: IntArray): Int {

    val size = arr.size
    val lastIndex = arr.size - 1

    // Assign an array to save if index can jump higher
    val higher: Array<Boolean> = Array(size) { index ->
        false
    }

    // Assign an array to save if index can jump lower
    val lower: Array<Boolean> = Array(size) { index ->
        false
    }

    // last index can always jump to itself
    higher[lastIndex] = true
    lower[lastIndex] = true


    var goodIndexes = 0

    val treeMap = TreeMap<Int, Int>()
    for (i in lastIndex downTo 0) {

        val currentValue = arr[i]

        var higherEntry = treeMap.ceilingEntry(currentValue)
        val lowerEntry = treeMap.floorEntry(currentValue)

        if (higherEntry != null) {
            higher[i] = lower[higherEntry.value]
        }

        if (lowerEntry != null) {
            lower[i] = higher[lowerEntry.value]
        }

        if (higher[i]) {
            ++goodIndexes
        }
        treeMap[arr[i]] = i
    }

    return goodIndexes
}





/*

You are given an integer array arr. From some starting index, you can make a series of jumps. The (1st, 3rd, 5th, ...) jumps in the series are called odd-numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even-numbered jumps. Note that the jumps are numbered, not the indices.

You may jump forward from index i to index j (with i < j) in the following way:

    During odd-numbered jumps (i.e., jumps 1, 3, 5, ...), you jump to the index j such that arr[i] <= arr[j] and arr[j] is the smallest possible value. If there are multiple such indices j, you can only jump to the smallest such index j.
    During even-numbered jumps (i.e., jumps 2, 4, 6, ...), you jump to the index j such that arr[i] >= arr[j] and arr[j] is the largest possible value. If there are multiple such indices j, you can only jump to the smallest such index j.
    It may be the case that for some index i, there are no legal jumps.

A starting index is good if, starting from that index, you can reach the end of the array (index arr.length - 1) by jumping some number of times (possibly 0 or more than once).

Return the number of good starting indices.



Example 1:

Input: arr = [10,13,12,14,15]
Output: 2
Explanation:
From starting index i = 0, we can make our 1st jump to i = 2 (since arr[2] is the smallest among arr[1], arr[2], arr[3], arr[4] that is greater or equal to arr[0]), then we cannot jump any more.
From starting index i = 1 and i = 2, we can make our 1st jump to i = 3, then we cannot jump any more.
From starting index i = 3, we can make our 1st jump to i = 4, so we have reached the end.
From starting index i = 4, we have reached the end already.
In total, there are 2 different starting indices i = 3 and i = 4, where we can reach the end with some number of
jumps.

Example 2:

Input: arr = [2,3,1,1,4]
Output: 3
Explanation:
From starting index i = 0, we make jumps to i = 1, i = 2, i = 3:
During our 1st jump (odd-numbered), we first jump to i = 1 because arr[1] is the smallest value in [arr[1], arr[2], arr[3], arr[4]] that is greater than or equal to arr[0].
During our 2nd jump (even-numbered), we jump from i = 1 to i = 2 because arr[2] is the largest value in [arr[2], arr[3], arr[4]] that is less than or equal to arr[1]. arr[3] is also the largest value, but 2 is a smaller index, so we can only jump to i = 2 and not i = 3
During our 3rd jump (odd-numbered), we jump from i = 2 to i = 3 because arr[3] is the smallest value in [arr[3], arr[4]] that is greater than or equal to arr[2].
We can't jump from i = 3 to i = 4, so the starting index i = 0 is not good.
In a similar manner, we can deduce that:
From starting index i = 1, we jump to i = 4, so we reach the end.
From starting index i = 2, we jump to i = 3, and then we can't jump anymore.
From starting index i = 3, we jump to i = 4, so we reach the end.
From starting index i = 4, we are already at the end.
In total, there are 3 different starting indices i = 1, i = 3, and i = 4, where we can reach the end with some
number of jumps.

Example 3:

Input: arr = [5,1,3,4,2]
Output: 3
Explanation: We can reach the end from starting indices 1, 2, and 4.



Constraints:

    1 <= arr.length <= 2 * 104
    0 <= arr[i] < 105



 */