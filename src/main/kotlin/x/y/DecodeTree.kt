package x.y

import java.util.*

fun main() {

    val nodeH = DNode("H", "he")
    val nodeJ = DNode("J", " ")
    val nodeE = DNode("E", "o")
    val nodeF = DNode("F", "there")
    val nodeB = DNode("B", "l")
    val nodeD = DNode("D", "", listOf(nodeE, nodeF))
    val nodeG = DNode("G", "", listOf(nodeH, nodeJ))
    val nodeC = DNode("C", "", listOf(nodeD))
    val nodeA = DNode("A", "", listOf(nodeG))
    val root = DNode("", "", listOf(nodeA, nodeB, nodeC))

    bfsDTree(root)
    println(decodeTree(root, "AGHBBCDEAGJCDF"))
}

fun decodeTree(
    root: DNode,
    input: String
): String {
    val codes = input.toCharArray()
    val builder = StringBuilder()
    codes.forEach { code ->
        builder.append(decode(code.toString(), root))
    }
    return builder.toString()
}

fun decode(code: String, root: DNode?): String {
    val queue: Queue<DNode> = LinkedList()
    if (root != null) {
        queue.add(root)
    }
    while(queue.isNotEmpty()) {
        val first = queue.remove()
        if (first.code == code) {
            return first.decode
        }
        queue.addAll(first.children)
    }

    return ""
}

data class DNode(
    val code: String = "",
    val decode: String = "",
    val children: List<DNode> = emptyList()
)

fun bfsDTree(root: DNode?) {
    val queue: Queue<DNode> = LinkedList()
    if (root != null) {
        queue.add(root)
    }
    while(queue.isNotEmpty()) {
        val first = queue.remove()
        println("${first.code}")
        queue.addAll(first.children)
    }
}
