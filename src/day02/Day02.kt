package day02

import println
import readInput

private const val DAY = "02"

fun main() {
    data class GameSet(
        val cubes: Map<String, Int>
    )

    data class Game(
        val id: Int,
        val sets: List<GameSet>
    )

    fun part1(input: List<Game>): Int {
        val limitSet = buildMap {
            this["red"] = 12
            this["green"] = 13
            this["blue"] = 14
        }

        return input.sumOf {
            val isValid = it.sets.all { set ->
                return@all set.cubes.all { (color, count) ->
                    count <= limitSet[color]!!
                }
            }
            if (isValid) it.id else 0
        }
    }

    fun part2(input: List<Game>): Int {
        return input.sumOf {
            val maxCubes = HashMap<String, Int>()
            it.sets.forEach { set ->
                set.cubes.forEach { (color, count) ->
                    if (!maxCubes.containsKey(color) || count > maxCubes[color]!!) {
                        maxCubes[color] = count
                    }
                }
            }

            maxCubes.values.fold(1) { acc, cur ->
                acc * cur
            }.toInt()
        }
    }

    fun mapInput(list: List<String>) : List<Game> {
        return list.map {
            val parts = it.split(';', ':')
            val id = parts[0].split(' ')[1].toInt()
            val sets = parts.drop(1).map { cubesString ->
                val cubes = cubesString.split(',')
                GameSet(
                    cubes.associate { cubeResult ->
                        val cubeParts = cubeResult.trim().split(' ')
                        cubeParts[1] to cubeParts[0].toInt()
                    }
                )
            }
            Game(id, sets)
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = mapInput(readInput("day$DAY/day${DAY}_test"))
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = mapInput(readInput("day$DAY/day$DAY"))
    part1(input).println()
    part2(input).println()
}
