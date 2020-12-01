package day01

import java.io.File

fun main() {
    val entries = File("src/main/kotlin/day01/day01-input.txt").readLines().map { it.toInt() }.sorted()
    entries.forEach { first ->
        entries.forEach { second ->
            entries.forEach { third ->
                if (first != second && first != third && first + second + third == 2020) {
                    println(first * second * third)
                    return
                }
            }
        }
    }
}