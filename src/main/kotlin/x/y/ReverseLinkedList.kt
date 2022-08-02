package x.y

fun main() {

    var head: Node? = null
    var current = head
    for (i in 1..5) {
        val newNode = Node(i, null)
        if (current == null) {
            current = newNode
            head = current
        } else {
            current.next = newNode
            current = newNode
        }
    }

    printLinkList(head)
    println()
    head = reverseLinkList(head)
    printLinkList(head)
}

fun printLinkList(head: Node?) {
    var current = head
    while (current != null) {
        print(" ${current.value} -> ")
        current = current.next
    }
}

fun reverseLinkList(head: Node?): Node? {
    var current: Node? = head
    var previous: Node? = null
    while (current != null) {
        val next = current.next
        current.next = previous
        previous = current
        current = next
    }
    return previous
}

class Node (
    val value: Int,
    var next: Node?
)