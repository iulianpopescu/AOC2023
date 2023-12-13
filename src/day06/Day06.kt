package day06

import println
import readInput

private const val DAY = "06"
fun main() {

    fun part1(input: List<Race>): Int {
        var result = 1
        input.forEach { race ->
            var possibleWins = 0
            for (speed in 0..race.time) {
                val timeLeft = race.time - speed
                if (timeLeft * speed > race.distance) {
                    possibleWins++
                }
            }
            result *= possibleWins
        }
        return result
    }

    fun part2(input: Race): Int {
        return part1(listOf(input))
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$DAY/day${DAY}_test").toRaces()
    val testInput2 = readInput("day$DAY/day${DAY}_test").toRace()
    check(part1(testInput) == 288)
    check(part2(testInput2) == 71503)

    val input = readInput("day$DAY/day$DAY").toRaces()
    val input2 = readInput("day$DAY/day$DAY").toRace()
    part1(input).println()
    part2(input2).println()
}

data class Race(
    val time: Long,
    val distance: Long,
)

private fun List<String>.toRaces() = this.map { line ->
    line.dropWhile { !it.isDigit() }
}.let { entry ->
    val times = entry[0].split(' ').filter { it.isNotBlank() }.map { it.toLong() }
    val distances = entry[1].split(' ').filter { it.isNotBlank() }.map { it.toLong() }

    times.zip(distances).map { (time, distance) -> Race(time, distance) }
}

private fun List<String>.toRace() = this.map { line ->
    line.filter { it.isDigit() }
}.let { entry ->
    val time = entry[0].toLong()
    val distance = entry[1].toLong()
    Race(time, distance)
}