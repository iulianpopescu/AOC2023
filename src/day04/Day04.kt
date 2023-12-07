package day04

import println
import readInput
import kotlin.math.pow

private const val DAY = "04"
fun main() {

    fun part1(input: List<Card>) = input.sumOf { it.score1() }

    fun part2(input: List<Card>): Int {
        val cards = IntArray(input.size) { 1 }
        input.forEachIndexed { index, card ->
            val score = card.score2()
            for (i in index + 1..index + score) {
                cards[i] += cards[index]
            }
        }
        return cards.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day$DAY/day${DAY}_test").toCards()
    check(part1(testInput) == 13)
    check(part2(testInput) == 30)

    val input = readInput("day$DAY/day$DAY").toCards()
    part1(input).println()
    part2(input).println()
}

data class Card(
    val winningNumbers: Set<Int>,
    val numbers: List<Int>,
) {
    fun score1() = numbers.count {
        winningNumbers.contains(it)
    }.let { count ->
        // in case no match is found, we'll have a negative exponent that will result in a value of 0.XX,
        // which will be converted to 0 by the [toInt] call
        2.0.pow(count - 1).toInt()
    }

    fun score2() = numbers.count {
        winningNumbers.contains(it)
    }
}

private fun List<String>.toCards() = this.map { card ->
    val parts = card.split(':', '|')
    val winningNumbers = parts[1]
        .split(' ')
        .filter { it.isNotBlank() }
        .map { it.trim().toInt() }
        .toSet()
    val numbers = parts[2]
        .split(' ')
        .filter { it.isNotBlank() }.map { it.trim().toInt() }
    Card(winningNumbers, numbers)
}