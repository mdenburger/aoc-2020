package day05

import java.io.File

fun main() = println(
    File("src/main/kotlin/day05/day05-input.txt").readLines()
        .map {
            val row = it.substring(0, 7).asNumber("FB")
            val column = it.substring(7).asNumber("LR")
            (row * 8) + column
        }
        .maxOrNull()
)

private fun String.asNumber(symbols: String) =
    map { symbols.indexOf(it).toString() }.joinToString("").toInt(symbols.length)
