package x.y

import java.io.StringReader
import java.util.*

fun main() {

    val nodeH = DNode("H", "he")
    val nodeJ = DNode("J", " ")
    val nodeE = DNode("E", "o")
    val nodeF = DNode("F", "there")
    val nodeB = DNode("B", "l")
    val nodeD = DNode("D", null, listOf(nodeE, nodeF))
    val nodeG = DNode("G", null, listOf(nodeH, nodeJ))
    val nodeC = DNode("C", null, listOf(nodeD))
    val nodeA = DNode("A", null, listOf(nodeG))
    val root = DNode("", null, listOf(nodeA, nodeB, nodeC))

    bfsDTree(root)
    println("---------------------------------------")
    println(decodeTree(root, "AGHBBCDEAGJCDF"))
    println("---------------------------------------")
    println(decodeTreeReader(root, StringReader("AGHBBCDEAGJCDF")))
    println("---------------------------------------")
    val decoder = Decoder(root, StringReader("AGHBBCDEAGJCDF"))
    while (decoder.hasNext()) {
        val next = decoder.next()
        if (next != null) {
            println(next)
        }
    }
    println("---------------------------------------")
}

class Decoder(
    private val root: DNode,
    private val reader: StringReader
) : Iterator<String?> {

    private var start: DNode? = root

    override fun hasNext(): Boolean {
        reader.mark(0)
        val read = reader.read()
        reader.reset()
        return read > 0
    }

    override fun next(): String? {
        val read = reader.read()
        val code = read.toChar().toString()
        //println("-----")
        //println("Searching $code from : ${start?.code}")
        val next = findNext(code, start)
        val found = next?.decode
        // only leaf nodes have decode values
        // once we reach there we have to start over
        start = if (found != null) {
            root
        } else {
            next
        }
        //println("Found : $found")
        //println("-----")
        return found
    }

    private fun findNext(code: String, root: DNode?): DNode? {
        val queue: Queue<DNode> = LinkedList()
        if (root != null) {
            queue.add(root)
        }
        while (queue.isNotEmpty()) {
            val first = queue.remove()
            if (first.code == code) {
                return first
            }
            queue.addAll(first.children)
        }
        return null
    }
}

fun decodeTreeReader(
    root: DNode,
    reader: StringReader
): String {
    val builder = StringBuilder()
    var current = reader.read()
    while (current > 0) {
        builder.append(decode(current.toChar().toString(), root))
        current = reader.read()
    }
    return builder.toString()
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
    while (queue.isNotEmpty()) {
        val first = queue.remove()
        if (first.code == code) {
            return first.decode ?: ""
        }
        queue.addAll(first.children)
    }

    return ""
}

data class DNode(
    val code: String = "",
    val decode: String? = null,
    val children: List<DNode> = emptyList()
)

fun bfsDTree(root: DNode?) {
    val queue: Queue<DNode> = LinkedList()
    if (root != null) {
        queue.add(root)
    }
    while (queue.isNotEmpty()) {
        val first = queue.remove()
        println(first.code)
        queue.addAll(first.children)
    }
}
