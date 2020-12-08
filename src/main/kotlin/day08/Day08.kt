package day08

import java.io.File

data class Operation(val name: String, val argument: Int)

class Program(private val operations: List<Operation>) {
    private var instruction = 0
    private var accumulator = 0
    private val visited = BooleanArray(operations.size)

    fun run(): Int {
        while (instruction < operations.size && !visited[instruction]) {
            visited[instruction] = true
            val operation = operations[instruction]
            when (operation.name) {
                "nop" -> instruction += 1
                "acc" -> {
                    accumulator += operation.argument
                    instruction += 1
                }
                "jmp" -> instruction += operation.argument
                else -> error("unknown operation: ${operation.name}")
            }
        }
        return accumulator
    }

    fun terminated() = instruction >= operations.size
}

fun main() {
    val operations = File("src/main/kotlin/day08/day08-input.txt").readLines().map {
        val (operationName, argument) = it.split(" ")
        Operation(operationName, argument.toInt())
    }

    println("Answer 1: " + operations.run())
    println("Answer 2: " + operations.modifyAndRun())
}

fun List<Operation>.run() = Program(this).run()

fun List<Operation>.modifyAndRun(): Int? {
    forEachIndexed { index, operation ->
        when (operation.name) {
            "jmp", "nop" -> {
                val modifiedOperations = toMutableList()
                val modifiedOperationName = when (operation.name) {
                    "jmp" -> "nop"
                    "nop" -> "jmp"
                    else -> operation.name
                }
                modifiedOperations[index] = Operation(modifiedOperationName, operation.argument)

                val program = Program(modifiedOperations)
                val result = program.run()

                if (program.terminated()) {
                    return result
                }
            }
        }
    }
    return null
}
