package day04

import java.io.File

fun main() {
    println(
        File("src/main/kotlin/day04/day04-input.txt")
            .readText()
            .split("\n\n")
            .map { it.split(' ', '\n') }
            .filter { fields -> fields.size == 8 || (fields.size == 7 && !fields.any { it.contains("cid:") }) }
            .filter { fields ->
                fields.all { field ->
                    val name = field.substring(0, 3)
                    val value = field.substring(4)
                    when (name) {
                        "byr" -> value.toInt() in 1920..2002
                        "iyr" -> value.toInt() in 2010..2020
                        "eyr" -> value.toInt() in 2020..2030
                        "hgt" -> {
                            val (number, unit) = Regex("(\\d+)(cm|in)").find(value)?.destructured ?: return@all false
                            when (unit) {
                                "cm" -> number.toInt() in 150..193
                                "in" -> number.toInt() in 59..76
                                else -> error("unknown unit: $unit")
                            }
                        }
                        "hcl" -> value.matches(Regex("#[0-9a-f]{6}"))
                        "ecl" -> listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value)
                        "pid" -> value.matches(Regex("[0-9]{9}"))
                        "cid" -> true
                        else -> error("unknown field: $name")
                    }
                }
            }
            .count()
    )
}
