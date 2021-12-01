import java.io.File
import java.lang.Integer.max
import kotlin.math.absoluteValue

fun main() {
    val seatLayout = File("src/main/kotlin/day11/day11-input.txt").readLines().map { it.toCharArray() }

    var current = seatLayout
    var currentHashCode = current.contentHashCode()
    val found = mutableSetOf<Int>()

    while (!found.contains(currentHashCode)) {
        found.add(currentHashCode)
        current = current.nextSeatLayout()
        currentHashCode = current.contentHashCode()
    }

    println("Answer 1: " + current.occupiedSeatCount())
}

private fun List<CharArray>.contentHashCode(): Int =
    fold(0) { result, line -> result + 31 * line.contentHashCode() }

private fun List<CharArray>.nextSeatLayout(): List<CharArray> {
    val next = toMutableList().map { it.clone() }
    for (row in 0 until size) {
        for (column in 0 until width()) {
            when (get(row)[column]) {
                'L' -> if (noSeatsAround(row, column)) {
                    next[row][column] = '#'
                }
                '#' -> if (atLeastFourSeatsAround(row, column)) {
                    next[row][column] = 'L'
                }
            }
        }
    }
    return next
}

private fun List<CharArray>.width() = first().size

private fun List<CharArray>.noSeatsAround(row: Int, column: Int) = forSeatsAround(row, column, true) { false }

private fun List<CharArray>.atLeastFourSeatsAround(row: Int, column: Int): Boolean {
    var count = 0
    return forSeatsAround(row, column, false) {
        count += 1
        if (count == 4) true else null
    }
}

private fun List<CharArray>.forSeatsAround(row: Int, column: Int, default: Boolean, block: List<CharArray>.() -> Boolean?): Boolean {
    for (dx in -1..1) {
        for (dy in -1..1) {
            val x = column + dy
            val y = row + dx
            if (!(x == column && y == row) && x >= 0 && x < width() && y >= 0 && y < size && get(y)[x] == '#') {
                val result = block()
                if (result != null) {
                    return result
                }
            }
        }
    }
    return default
}

private fun List<CharArray>.occupiedSeatCount() = map { row -> row.count { it == '#' } }.sum()

fun List<CharArray>.print() {
    forEach { println(it.joinToString("")) }
}