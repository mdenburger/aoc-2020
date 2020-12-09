package day09

import java.io.File

fun main() {
    val numbers = File("src/main/kotlin/day09/day09-input.txt").readLines().map { it.toLong() }.toLongArray()
    val window = 25

    val invalidNumber = answer1(numbers, window)
    println("Answer 1: $invalidNumber")
    println("Answer 2: " + answer2(numbers, invalidNumber))
}

private fun answer1(numbers: LongArray, window: Int): Long {
    for (start in 0 until numbers.size - window) {
        val next = numbers[start + window]
        if (!numbers.containsSum(start, start + window, next)) {
            return next
        }
    }
    error("No answer found")
}

private fun LongArray.containsSum(start: Int, end: Int, sum: Long): Boolean {
    for (i in start until end) {
        val first = get(i)
        for (j in i + 1 until end) {
            val second = get(j)
            if (first != second && first + second == sum) {
                return true
            }
        }
    }
    return false
}

private fun answer2(numbers: LongArray, sum: Long): Long {
    for (start in numbers.indices) {
        numbers.findRangeSumsTo(start, sum)?.let {
            return it.minOrNull()!! + it.maxOrNull()!!
        }
    }
    error("No answer found")
}

private fun LongArray.findRangeSumsTo(start: Int, sum: Long): List<Long>? {
    val range = mutableListOf<Long>()
    var rangeSum = 0L
        for (i in start until size) {
            range += get(i)
            rangeSum += get(i)
            if (rangeSum == sum) {
                return range
            }
            if (rangeSum > sum) {
                return null
            }
        }
    return null
}
