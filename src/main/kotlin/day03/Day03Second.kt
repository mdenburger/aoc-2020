package day03

import java.io.File

fun main() {
    val lines = File("src/main/kotlin/day03/day03-input.txt").readLines()
    val slopes = listOf(
        1 to 1,
        3 to 1,
        5 to 1,
        7 to 1,
        1 to 2
    )

    val answer: Long = slopes.fold(1) { answer, slope ->
        answer * treeCount(lines, slope.first, slope.second)
    }

    println(answer)
}

private fun treeCount(lines: List<String>, dx: Int, dy: Int): Int {
    val width = lines[0].length
    val height = lines.size

    var x = 0
    var y = 0
    var result = 0

    while (y < height) {
        if (lines[y][x % width] == '#') {
            result += 1
        }
        x += dx
        y += dy
    }

    return result
}
