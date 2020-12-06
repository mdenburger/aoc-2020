package day06

import java.io.File

fun main() = println(
    File("src/main/kotlin/day06/day06-input.txt").readText()
        .split("\n\n")
        .map { it.split('\n') }
        .map { answers -> answers.map { it.toList() }.flatten().toSet().size }
        .sum()
)