package x.y


/***
 * 16. Find the count of total no of sub tree of a given tree of integrate no that sums up to X (integer).


1.                  -6
2.               /      \
3.           -10        3
4.            / \       / \
5.         9    8     -4   7


1.                  1
2.               /      \
3.              2        3


 */
fun main() {

    val n7 = BTNode(7, null, null)
    val nm4 = BTNode(-4, null, null)
    val n3 = BTNode(3, nm4, n7)

    val n9 = BTNode(9, null, null)
    val n8 = BTNode(8, null, null)
    val nm10 = BTNode(-10, n9, n8)

    val root = BTNode(-6, nm10, n3)

    val set = mutableSetOf<BTNode>()
    val target = 7
    sumOfNodes(root, target, set)


    /*val n3 = BTNode(3, null, null)
    val n2 = BTNode(2, null, null)

    val root = BTNode(1, n2, n3)

    val set = mutableSetOf<BTNode>()
    val target = 5*/
    sumOfNodes(root, target, set)

    println("Number of subtrees: ${set.size}")
}

fun sumOfNodes(node: BTNode?, target:Int, set: MutableSet<BTNode>): Int {
    if (node == null ) {
        return 0
    }

    var sum = 0
    if (node.left != null) {
        sum += sumOfNodes(node.left, target, set)
    }
    sum += node.value
    if (node.right != null) {
        sum += sumOfNodes(node.right, target, set)
    }

    if (sum == target) {
        set.add(node)
    }
    return sum
}

data class BTNode(
    val value: Int,
    val left: BTNode?,
    val right: BTNode?
)


/*****
 * 
 * Sprint velocity problem
 *
 You have list of Projects and the efforts in each projects asP[i]. You have total no of sprintSby which you have to complete these projects.  Ina sprint, youcan’twork onmultiple projects. If you have completed one project, you canwait till the next sprintto start the next project. Find thelowestsprint velocity(integer value ofeffort per sprint ), that you can put for these projects to complete all of them on time.
E.g. - 1.Project efforts P[i]=[3, 6, 7, 11],Total no of sprint S= 8.Output lowest effort per sprint = 4
                2.Project efforts P[i]=[30, 11, 23, 4, 20],Total no of sprint S= 6.Output lowest effort per sprint = 23
 * 
 * 
 */