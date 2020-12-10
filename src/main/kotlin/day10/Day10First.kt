package day10

import java.io.File

fun main() = println(
    File("src/main/kotlin/day10/day10-input.txt")
        .readLines()
        .map { it.toInt() }
        .sorted()
        .let { listOf(0) + it + listOf(it.last() + 3) }
        .windowed(2)
        .map { it[1] - it[0] }
        .let { differences ->
            differences.count { it == 1 } * differences.count { it == 3 }
        }
)
