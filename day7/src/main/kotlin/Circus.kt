import java.io.File
import kotlin.math.min

typealias NodeKey = String
typealias Nodes = Map<NodeKey, Node>

data class Node(val key: NodeKey, val weight: Int, val children: List<String>)

fun parseLine(line: String): Pair<NodeKey, Node> {
    val groups = """(\w+) \((\d+)\)[-> ]*([\w, ]*)""".toRegex()
            .matchEntire(line)?.groups ?: throw IllegalArgumentException("Invalid line")
    return groups[1]!!.value to Node(key = groups[1]!!.value, weight = groups[2]!!.value.toInt(),
                                        children = groups[3]!!.value.split(", ").filter { it != "" })
}

fun input(filename: String): Nodes =
    File(filename).readLines()
            .map(::parseLine)
            .toMap()

fun findTotalChildrenWithCurrent(node: Node, nodes: Nodes): Int =
        if (node.children.isEmpty()) 1
        else 1 + node.children.sumBy { findTotalChildrenWithCurrent(nodes[it]!!, nodes) }

fun findParentOfAll(nodes: Nodes): Node =
        nodes.maxBy { findTotalChildrenWithCurrent(it.value, nodes) }!!.value

fun totalWeight(node: Node, nodes: Nodes): Int =
        if (node.children.isEmpty()) node.weight
        else node.weight + node.children.sumBy { totalWeight(nodes[it]!!, nodes) }

tailrec fun findCorrectWeight(node: Node, curDiff: Int, nodes: Nodes): Int {
    if (node.children.size < 2) return node.weight
    val (max, diff) = findMaxWithDifference(node, nodes)
    return if (diff == 0) node.weight - curDiff else findCorrectWeight(max, diff, nodes)
}

fun findMaxWithDifference(node: Node, nodes: Nodes): Pair<Node, Int> {
    val children = node.children.map { nodes[it]!! to totalWeight(nodes[it]!!, nodes) }
    val (_, minWeight) = children.minBy { (_, weight) -> weight }!!
    val (max, maxWeight) = children.maxBy { (_, weight) -> weight }!!
    return max to (maxWeight - minWeight)
}



fun main(args: Array<String>) {
    val nodes = input("src/main/resources/input.txt")
    val parent = findParentOfAll(nodes)
    println(parent)
    val shouldHave = findCorrectWeight(parent, 0, nodes)
    println(shouldHave)
}
