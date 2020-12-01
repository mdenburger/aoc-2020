package day01

import java.io.File

fun main() {
    val entries = File("src/main/kotlin/day01/day01-input.txt").readLines().map { it.toInt() }.sorted()
    entries.forEach { first ->
        entries.forEach { second ->
            if (first != second && first + second == 2020) {
                println(first * second)
                return
            }
        }
    }
}