package x.y

import java.util.TreeMap

fun main() {

    val treeMap = TreeMap<Int, Int>()

    treeMap[1] = 1
    treeMap[1] = 100
    treeMap[20] = 200
    treeMap[20] = 202
    treeMap[21] = 210
    treeMap[30] = 3000
    treeMap[30] = 3100


    println(treeMap)
    for (index in -10..50) {
        println("key: $index, ceil: ${treeMap.ceilingEntry(index)}, floor: ${treeMap.floorEntry(index)}")
    }

}