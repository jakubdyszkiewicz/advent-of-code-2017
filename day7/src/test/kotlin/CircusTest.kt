import io.kotlintest.matchers.*
import io.kotlintest.properties.forAll
import io.kotlintest.properties.headers
import io.kotlintest.properties.row
import io.kotlintest.properties.table
import io.kotlintest.specs.StringSpec

class CircusTest : StringSpec() {
    init {
        "should parse line with children" {
            val result = parseLine("fwft (72) -> ktlj, cntj, xhth")
            result.first shouldBe "fwft"
            result.second.run {
                weight shouldBe 72
                children shouldBe listOf("ktlj", "cntj", "xhth")
            }
        }

        "should parse line withouth children" {
            val result = parseLine("fwft (72) ")
            result.first shouldBe "fwft"
            result.second.run {
                weight shouldBe 72
                children should beEmpty()
            }
        }

        "should read input" {
            input("src/main/resources/input.txt").size should beGreaterThan(10)
        }

        val nodes = listOf(
                Node(key = "a", weight = 1, children = listOf("b")),
                Node(key = "b", weight = 2, children = listOf("c")),
                Node(key = "c", weight = 3, children = listOf()))
                .associateBy { it.key }

        "should find total children" {
            val t = table(
                    headers("nodeKey", "result"),
                    row("a", 3),
                    row("b", 2),
                    row("c", 1))

            forAll(t) { nodeKey, result ->
                findTotalChildrenWithCurrent(nodes[nodeKey]!!, nodes) shouldBe result
            }
        }

        "should find parent of all" {
            findParentOfAll(nodes) shouldBe nodes["a"]
        }

        "should find total weight" {
            val t = table(
                    headers("nodeKey", "result"),
                    row("a", 6),
                    row("b", 5),
                    row("c", 3))

            forAll(t) { nodeKey, result ->
                totalWeight(nodes[nodeKey]!!, nodes) shouldBe result
            }
        }
    }
}