import java.io.File

fun main() = println(
    File("src/main/kotlin/day05/day05-input.txt").readLines()
        .map {
            val row = it.substring(0, 7).asNumber("FB")
            val column = it.substring(7).asNumber("LR")
            (row * 8) + column
        }
        .sorted()
        .windowed(2)
        .find { it[0] == (it[1] - 2) }
        ?.let { it[0] + 1 }
)

private fun String.asNumber(symbols: String) =
    Integer.parseInt(map { symbols.indexOf(it).toString() }.joinToString(""), symbols.length)
