package org.aoc.stream

import io.kotlintest.matchers.shouldBe
import io.kotlintest.properties.forAll
import io.kotlintest.properties.headers
import io.kotlintest.properties.row
import io.kotlintest.properties.table
import io.kotlintest.specs.StringSpec

class SolutionTest: StringSpec() {

    init {
        "should solve score" {
            val t = table(
                    headers("stream", "result"),
                    row("{}", 1),
                    row("{{{}}}", 6),
                    row("{{},{}}", 5),
                    row("{{{},{},{{}}}}", 16),
                    row("{<a>,<a>,<a>,<a>}", 1),
                    row("{{<ab>},{<ab>},{<ab>},{<ab>}}", 9),
                    row("{{<!!>},{<!!>},{<!!>},{<!!>}}", 9),
                    row("{{<a!>},{<a!>},{<a!>},{<ab>}}", 3))

            forAll(t) { stream, result ->
                val stateMachine = StateMachine()
                stateMachine.applyStream(stream)
                stateMachine.res shouldBe result
            }
        }

        "should count garbage" {
            val t = table(
                    headers("stream", "result"),
                    row("<>", 0),
                    row("<random characters>", 17),
                    row("<<<<>", 3),
                    row("<{!>}>", 2),
                    row("<!!>", 0),
                    row("<!!!>>", 0),
                    row("""<{o"i!a,<{i<a>""", 10))

            forAll(t) { stream, result ->
                val stateMachine = StateMachine()
                stateMachine.applyStream(stream)
                stateMachine.removedGarbage shouldBe result
            }
        }
    }
}