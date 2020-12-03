package day03

import java.io.File

fun main() {
    val lines = File("src/main/kotlin/day03/day03-input.txt").readLines()
    val width = lines[0].length
    var x = 0
    var treeCount = 0

    lines.forEachIndexed { y, line ->
        if (lines[y][x % width] == '#') {
            treeCount += 1
        }
        x += 3
    }

    println(treeCount)
}