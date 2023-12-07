package day01

import println
import readInput

private const val DAY = "01"
fun main() {
    val digits = buildMap {
        this["one"]   = "1"
        this["two"]   = "2"
        this["three"] = "3"
        this["four"]  = "4"
        this["five"]  = "5"
        this["six"]   = "6"
        this["seven"] = "7"
        this["eight"] = "8"
        this["nine"]  = "9"
    }

    fun buildNumber(input: String, matches: Collection<String>) : Int {
        val firstDigit = input.findAnyOf(matches)!!.second.let { matchedValue ->
            if (matchedValue.toIntOrNull() != null) {
                matchedValue.toInt()
            } else digits[matchedValue]!!.toInt()
        }
        val lastDigit = input.findLastAnyOf(matches)!!.second.let { matchedValue ->
            if (matchedValue.toIntOrNull() != null) {
                matchedValue.toInt()
            } else digits[matchedValue]!!.toInt()
        }
        return firstDigit * 10 + lastDigit
    }

    fun part1(input: List<String>) = input.sumOf { line ->
        buildNumber(line, digits.values)
    }

    fun part2(input: List<String>) = input.sumOf { line ->
        buildNumber(line, digits.keys + digits.values)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$DAY/day${DAY}_test")
    val testInputAlt = readInput("day$DAY/day${DAY}_test_alt")
    check(part1(testInput) == 142)
    check(part2(testInputAlt) == 281)

    val input = readInput("day$DAY/day$DAY")
    part1(input).println()
    part2(input).println()
}
