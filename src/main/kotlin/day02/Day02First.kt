package day02

import java.io.File

fun main() {
    val lines = File("src/main/kotlin/day02/day02-input.txt").readLines()

    val validPasswordCount = lines.map { line ->
        val tokens = line.split(" ")

        val (min, max) = tokens[0].split("-").map { it.toInt() }
        val character = tokens[1][0]
        val password = tokens[2]

        val characterCount = password.count { it == character }

        (characterCount in min..max).toInt()
    }.sum()

    println(validPasswordCount)
}

private fun Boolean.toInt() = if (this) 1 else 0