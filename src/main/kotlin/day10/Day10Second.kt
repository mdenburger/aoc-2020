package day10

import java.io.File
import kotlin.math.pow

fun main() {
    val differences = File("src/main/kotlin/day10/day10-input.txt")
        .readLines()
        .map { it.toInt() }
        .sorted()
        .let { listOf(0) + it + listOf(it.last() + 3) }
        .windowed(2)
        .map { it[1] - it[0] }

    val permutationsOfOnes = listOf(0, 1, 2, 4, 7)
    val sublistCount = longArrayOf(0, 0, 0, 0, 0)

    var numberOfOnes = 0
    differences.forEach { diff ->
        if (diff == 1) {
            numberOfOnes++
        } else {
            sublistCount[numberOfOnes]++
            numberOfOnes = 0
        }
    }

    var answer = 1L

    for (i in 2 until sublistCount.size) {
        val permutations = permutationsOfOnes[i].pow(sublistCount[i])
        if (permutations > 0) {
            answer *= permutations
        }
    }

    println(answer)
}

fun Int.pow(n: Long) = toDouble().pow(n.toDouble()).toLong()
