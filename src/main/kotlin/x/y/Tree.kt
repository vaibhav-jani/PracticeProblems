package x.y

import java.util.*
import kotlin.math.log2


fun main() {
    val array: Array<Int?> = arrayOf(6, 7, 8, 2, 7, 1, 3, 9, null, 1, 4, null, null, null, 5)
    val root = insertLevelOrder(array, 0)
    val depth = log2(array.size.toDouble()).toInt()
    //print(depth)
    //println(deepestLeavesSum(root))
    //dfs(root)
    bfs(root)
}

fun deepestLeavesSum(root: TreeNode?): Int {

    val stack: Stack<Pair<TreeNode, Int>> = Stack<Pair<TreeNode, Int>>()

    var currentNode: TreeNode? = root

    var result: Int = 0

    var currentDepth = 0
    var maxDepth = 0

    if (currentNode != null) {
        stack.push(Pair(currentNode, 0))
    }
    // Calculating the deepest depth
    while (!stack.isEmpty()) {
        val pair = stack.pop()
        currentNode = pair.first
        currentDepth = pair.second
        println("current: ${currentNode.`val`}, depth: ${currentDepth}, maxDepth: ${maxDepth}")
        if (currentNode.left == null && currentNode.right == null) {
            if (currentDepth > maxDepth) {
                // start sum as we found new depth
                result = currentNode.`val`
                maxDepth = currentDepth
            } else if (currentDepth == maxDepth) {
                // found another node with max depth
                result += currentNode.`val`
            }
        }
        if (currentNode.right != null) {
            stack.push(Pair(currentNode.right!!, currentDepth + 1))
        }
        if (currentNode.left != null) {
            stack.push(Pair(currentNode.left!!, currentDepth + 1))
        }
    }
    return result
}

fun insertLevelOrder(array: Array<Int?>, index: Int): TreeNode? {
    var root: TreeNode? = null
    if (index < array.size) {
        root = array[index]?.let {
            TreeNode(it)
        }
        root?.left = insertLevelOrder(array, (index * 2) + 1)
        root?.right = insertLevelOrder(array, (index * 2) + 2)
    }
    return root
}

fun bfs(root: TreeNode?) {

    val stack: Queue<TreeNode> = LinkedList()
    if (root != null) {
        stack.add(root)
    }
    var current = root
    while (!stack.isEmpty()) {
        current = stack.remove()
        if (current.left != null) {
            stack.add(current.left)
        }
        if (current.right != null) {
            stack.add(current.right)
        }
        println("current: ${current.`val`}")
    }
}

fun dfs(root: TreeNode?) {

    val stack: Stack<TreeNode> = Stack<TreeNode>()
    if (root != null) {
        stack.push(root)
    }
    var current = root
    while (!stack.isEmpty()) {
        current = stack.pop()
        if (current.right != null) {
            stack.push(current.right)
        }
        if (current.left != null) {
            stack.push(current.left)
        }
        println("current: ${current.`val`}")
    }
}

fun inOrder(root: TreeNode?) {

    if (root?.left != null) {
        inOrder(root.left)
    }
    println("${root?.`val`}")
    if (root?.right != null) {
        inOrder(root.right)
    }
}

fun preOrder(root: TreeNode?) {

    println("${root?.`val`}")
    if (root?.left != null) {
        preOrder(root.left)
    }
    if (root?.right != null) {
        preOrder(root.right)
    }
}

fun postOrder(root: TreeNode?) {

    if (root?.left != null) {
        postOrder(root.left)
    }
    if (root?.right != null) {
        postOrder(root.right)
    }
    println("${root?.`val`}")
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}