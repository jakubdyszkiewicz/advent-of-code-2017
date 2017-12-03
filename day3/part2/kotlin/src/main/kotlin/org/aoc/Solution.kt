package org.aoc

data class Coordinate(val x: Int, val y: Int) {

    private fun upper() = this.copy(y = y + 1)
    private fun lower() = this.copy(y = y - 1)
    private fun left() = this.copy(x = x - 1)
    private fun right() = this.copy(x = x  + 1)
    // Did it this way because there is no Collection#combine(Collection) function to generate combinations
    fun adjacents() =
            setOf(right(), right().upper(),
                    upper(), upper().left(), left(),
                    left().lower(), lower(), lower().right())
}

class Fields(
        val edge: Int,
        private val fieldsWithValues: Map<Coordinate, Int> = mapOf()) {
    val values: Collection<Int> = fieldsWithValues.values

    operator fun plus(coordWithValue: Pair<Coordinate, Int>) = Fields(edge, fieldsWithValues + coordWithValue)
    operator fun get(coordinate: Coordinate): Int? = fieldsWithValues[coordinate]
    operator fun get(coordinates: Set<Coordinate>): List<Int> = coordinates.mapNotNull { this[it] }
    fun extendEdgeBy(extension: Int) = Fields(edge + extension, fieldsWithValues)
}

fun startingField() = Fields(edge = 1, fieldsWithValues = mapOf(Coordinate(x = 0, y = 0) to 1))

fun findFirstHigherThanNumByExtendingField(num: Int, fields: Fields = startingField()): Int =
    findFirstHigherThan(fields, num) ?: findFirstHigherThanNumByExtendingField(num, extendFields(fields))

fun findFirstHigherThan(fields: Fields, num: Int): Int? =
        fields.values
                .sorted()
                .find { it > num }

fun extendFields(fields: Fields): Fields =
        sequenceOf(::rightEdge, ::upperEdge, ::leftEdge, ::lowerEdge)
                .flatMap { it(fields.edge + 2) }
                .fold(fields.extendEdgeBy(2)) { fields, coord -> fields + (coord to sumOfAdjacentCoords(coord, fields)) }

fun rightEdge(edge: Int) = (-(edge / 2) + 1 .. edge / 2).asSequence().map { Coordinate(x = edge / 2, y = it) }
fun upperEdge(edge: Int) = (edge / 2 - 1 downTo -(edge / 2)).asSequence().map { Coordinate(x = it, y = edge / 2) }
fun leftEdge(edge: Int) = (edge / 2 - 1 downTo -(edge / 2)).asSequence().map { Coordinate(x = - edge / 2, y = it) }
fun lowerEdge(edge: Int) = (-edge / 2 .. (edge / 2)).asSequence().map { Coordinate(x = it, y = - edge / 2) }

fun sumOfAdjacentCoords(coordinate: Coordinate, fields: Fields) = fields[coordinate.adjacents()].sum()

fun main(args: Array<String>) {
    println(findFirstHigherThanNumByExtendingField(747))
}