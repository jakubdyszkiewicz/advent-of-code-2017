package org.aoc.registers

import java.io.File

class Registers {
    private val map = mutableMapOf<String, Int>()
    operator fun get(register: String): Int {
        if (map[register] == null) {
            map[register] = 0
        }
        return map[register]!!
    }

    fun increment(register: String, value: Int) {
        map[register] = get(register) + value
    }

    fun maxValue() = map.values.max()!!
}

// itw dec 894 if t != 0
fun parseOperation(line: String): (registers: Registers) -> Unit {
    val chunks = line.split(" ")
    val reg = chunks[0]
    val changeOp = chunks[1]
    val changeNum = chunks[2].toInt()
    val condReg = chunks[4]
    val cond = chunks[5]
    val condNum = chunks[6].toInt()
    return { registers ->
        if (condition(cond)(registers[condReg], condNum)) {
            registers.increment(reg, if (changeOp == "inc") changeNum else -changeNum)
        }
    }
}

fun condition(cond: String): (Int, Int) -> Boolean = when(cond) {
    ">" -> { a, b -> a > b }
    ">=" -> { a, b -> a >= b }
    "<" -> { a, b -> a < b }
    "<=" -> { a, b -> a <= b }
    "!=" -> { a, b -> a != b }
    "==" -> { a, b -> a == b }
    else -> throw IllegalArgumentException()
}

fun main(args: Array<String>) {
    val registers: Registers = Registers()
    File("src/main/resources/input.txt").readLines()
            .map {
                parseOperation(it)(registers)
                registers.maxValue()
            }
            .max()
            .let(::println)
}