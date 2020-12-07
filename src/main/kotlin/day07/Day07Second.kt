package day07

import java.io.File

data class Bags(val quantity: Int, val color: String)

fun main() = println(
    File("src/main/kotlin/day07/day07-input.txt").readLines().map { line ->
        val (color, contentDescription) = line.split(" bags contain ")

        val content = Regex("""(\d+) (\w+ \w+) bag""")
            .findAll(contentDescription)
            .map { Bags(quantity = it.groupValues[1].toInt(), color = it.groupValues[2]) }
            .toList()

        color to content
    }.toMap().countContentOf("shiny gold") - 1
)

private fun Map<String, List<Bags>>.countContentOf(color: String): Long =
    get(color)!!.map { bags -> bags.quantity * countContentOf(bags.color) }.sum() + 1
