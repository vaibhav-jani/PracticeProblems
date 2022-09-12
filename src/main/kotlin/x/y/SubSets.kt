package x.y

fun main() {

    val list = mutableListOf<Int>()
    for (i in 1..4) {
        list.add(i)
    }

    /*var t1 = System.currentTimeMillis()
    val result = subSets2(list)
    var t2 = System.currentTimeMillis()
    println("${t2 - t1}: $result")
    println("=============")*/

    var t1 = System.currentTimeMillis()
    val result2 = mutableListOf<List<Int>>()
    subSets(list, 0, mutableListOf(), result2)
    var t2 = System.currentTimeMillis()
    println("${t2 - t1}: $result2")
    println("=============")

    /*t1 = System.currentTimeMillis()
    val result3 = subSets3(list)
    t2 = System.currentTimeMillis()
    println("${t2 - t1}: $result3")
    println("=============")*/

}

fun subSets(set: List<Int>, index: Int, subset: MutableList<Int>, output: MutableList<List<Int>>) {

    //base condition
    if (index >= set.size) {
        output.add(subset)
        //println("$index: $output")
        //println("-------------")
        return
    }

    // without including current
    //println("checking: $index")
    subSets(set, index + 1, subset, output)

    //println("checking: $index")
    // with including current
    val current = set[index]
    val includeSubset = subset.toMutableList()
    includeSubset.add(current)
    subSets(set, index + 1, includeSubset,  output)
}


fun subSets2(nums: List<Int>): List<List<Int>> {

    val ans = ArrayList<List<Int>>()

    fun dfs(i: Int, cur: ArrayList<Int>) {
        ans.add(ArrayList(cur))
        if (i == nums.size) {
            return
        }
        for (j in i until nums.size) {
            cur.add(nums[j])
            dfs(j + 1, cur)
            cur.removeAt(cur.lastIndex)
        }
    }

    dfs(0, ArrayList())

    return ans
}

fun subSets3(set: List<Int>): List<List<Int>> {

    val subsets = mutableListOf<List<Int>>()

    subsets.add(emptyList())

    set.forEach { item ->
        val iterator = subsets.listIterator()
        while(iterator.hasNext()) {
            val existing =  iterator.next()
            val clone = mutableListOf<Int>()
            clone.addAll(existing)
            clone.add(item)
            iterator.add(clone)
        }
    }

    return subsets
}