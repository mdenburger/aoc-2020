package day04

import java.io.File

fun main() = println(
    File("src/main/kotlin/day04/day04-input.txt").readText()
        .split("\n\n")
        .map { it.split(' ', '\n') }
        .filter { it.size == 8 || (it.size == 7 && !it.any { it.contains("cid:") }) }
        .count()
)
