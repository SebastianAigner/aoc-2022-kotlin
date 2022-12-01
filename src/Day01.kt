import java.io.File
import java.util.PriorityQueue

fun main() {
    fun parseInput(input: String) = input.split("\n\n").map { elf ->
        elf.lines().map { it.toInt() }
    }

    // O(size * log size) -> O(size * log n) -> O(size)
    fun List<List<Int>>.topNElves(n: Int): Int {
        fun findTopN(n: Int, element: List<Int>): List<Int> {
            if (element.size == n) return element
            val x = element.random()
            val small = element.filter { it < x }
            val equal = element.filter { it == x }
            val big = element.filter { it > x }
            if (big.size >= n) return findTopN(n, big)
            if (equal.size + big.size >= n) return (equal + big).takeLast(n)
            return findTopN(n - equal.size - big.size, small) + equal + big
        }
        return findTopN(n, this.map { it.sum() }).sum()
    }

    fun part1(input: String): Int {
        val data = parseInput(input)
        return data.topNElves(1)
    }

    fun part2(input: String) : Int {
        val data = parseInput(input)
        return data.topNElves(3)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = File("src/Day01_test.txt").readText()
    check(part1(testInput) == 24000)

    val input = File("src/Day01.txt").readText()
    println(part1(input))
    println(part2(input))

//    val input = readInput("Day01")
//    println(part1(input))
//    println(part2(input))
}
