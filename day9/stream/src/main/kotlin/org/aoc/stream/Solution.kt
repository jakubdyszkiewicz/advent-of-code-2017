package org.aoc.stream

import java.io.File

class StateMachine {
    private var state: State = State.READING_GROUPS
    private var previous: State = State.READING_GROUPS
    private var openGroups: Int = 0
    var res: Int = 0
    var removedGarbage: Int = 0

    fun applyStream(string: String) {
        string.forEach { applyChar(it) }
    }

    private fun applyChar(char: Char) {
        when (state) {
            State.READING_GROUPS -> applyForReadingGroups(char)
            State.READING_GARBAGE -> applyForReadingGarbage(char)
            State.IGNORE_CHAR -> applyForIgnoreChar()
        }
    }

    private fun applyForReadingGroups(char: Char) {
        when (char) {
            '{' -> {
                openGroups++
                changeStateTo(State.READING_GROUPS)
            }
            '}' -> {
                res += openGroups
                openGroups--
                changeStateTo(State.READING_GROUPS)
            }
            '<' -> changeStateTo(State.READING_GARBAGE)
        }
    }

    private fun applyForReadingGarbage(char: Char) {
        when (char) {
            '!' -> changeStateTo(State.IGNORE_CHAR)
            '>' -> changeStateTo(State.READING_GROUPS)
            else -> removedGarbage++
        }
    }

    private fun applyForIgnoreChar() {
        changeStateTo(previous)
    }

    private fun changeStateTo(state: State) {
        this.previous = this.state
        this.state = state
    }
}

enum class State {
    READING_GROUPS,
    READING_GARBAGE,
    IGNORE_CHAR
}

fun main(args: Array<String>) {
    val stream = File("src/main/resources/input.txt").readText()
    val stateMachine = StateMachine()
    stateMachine.applyStream(stream)
    println(stateMachine.res)
    println(stateMachine.removedGarbage)
}