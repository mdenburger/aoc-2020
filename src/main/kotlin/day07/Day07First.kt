package day07

import java.io.File

fun main() {
    val containers = mutableMapOf<String, MutableSet<String>>()

    File("src/main/kotlin/day07/day07-input.txt").readLines().map { line ->
        val (color, contentDescription) = line.split(" bags contain ")

        Regex("""(\d+) (\w+ \w+) bag""")
            .findAll(contentDescription)
            .map { it.groupValues[2] }
            .forEach { contentColor ->
                containers.getOrPut(contentColor, { mutableSetOf() }).add(color)
            }
    }

    println(containers.findContainersOf("shiny gold").size)
}

private fun Map<String, Set<String>>.findContainersOf(color: String): Set<String> =
    get(color)?.let { colors -> colors + colors.map { findContainersOf(it) }.flatten() } ?: emptySet()
