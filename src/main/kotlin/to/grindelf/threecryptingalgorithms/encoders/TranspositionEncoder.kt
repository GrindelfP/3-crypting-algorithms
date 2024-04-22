package to.grindelf.threecryptingalgorithms.encoders

import to.grindelf.threecryptingalgorithms.utility.Toolbox.RUSSIAN_ALPHABET
import to.grindelf.threecryptingalgorithms.utility.Toolbox.processKeyWord
import to.grindelf.threecryptingalgorithms.utility.Toolbox.processPhrase
import to.grindelf.threecryptingalgorithms.utility.UnsupportedSymbolException

class TranspositionEncoder(
    keyword: String,
) {
    private val encoderMatrix: List<List<Char>> = initializeEncoderMatrix(keyword)

    companion object {
        private const val SQUARE_SIZE = 6
    }

    fun encrypt(message: String): String {
        val trimmedMessage = processPhrase(message)
        val coordinates = mutableListOf<Pair<Int, Int>>()

        trimmedMessage.forEach { char ->
            coordinates.add(getCoordinatesOf(char))
        }

        return buildByCoordinates(transformCoordinates(coordinates))
    }

    fun decrypt(message: String): String {
        val coordinates = mutableListOf<Pair<Int, Int>>()

        message.forEach { char ->
            coordinates.add(getCoordinatesOf(char))
        }

        return buildByCoordinates(reverseTransformCoordinates(coordinates))
    }

    private fun buildByCoordinates(transformCoordinates: List<Pair<Int, Int>>): String {
        var message = ""

        transformCoordinates.forEach { coordinate ->
            message += encoderMatrix[coordinate.second][coordinate.first]
        }

        return message
    }

    private fun transformCoordinates(coordinates: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
        val firsts = coordinates.map { it.first }
        val seconds = coordinates.map { it.second }
        val linearList = firsts + seconds

        return linearList.windowed(2, 2) {it[0] to it[1]}
    }

    private fun reverseTransformCoordinates(coordinates: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
        val newCoordinates = mutableListOf<Pair<Int, Int>>()
        val firsGroup = coordinates.subList(0, coordinates.size / 2)
        val secondGroup = coordinates.subList(coordinates.size / 2, coordinates.size)
        var i = 0
        while(i < firsGroup.size) {
            newCoordinates.add(Pair(firsGroup[i].first, secondGroup[i].first))
            newCoordinates.add(Pair(firsGroup[i].second, secondGroup[i].second))
            i++
        }

        return newCoordinates
    }

    private fun getCoordinatesOf(char: Char): Pair<Int, Int> {
        var coordinates: Pair<Int, Int> = Pair(-1, -1)

        encoderMatrix.forEachIndexed { topIndex, column ->
            column.forEachIndexed { bottomIndex, element ->
                if (element == char) {
                    coordinates = Pair(bottomIndex, topIndex) }
            }
        }

        return when {
            coordinates == Pair(-1, -1) -> throw UnsupportedSymbolException()
            else -> coordinates
        }
    }

    private fun initializeEncoderMatrix(keyword: String): List<List<Char>> {
        val matrix = mutableListOf<MutableList<Char>>()
        var topIndex = 0
        val completeKeyword = processKeyWord(keyword).toMutableList()
        completeKeyword.addAll(RUSSIAN_ALPHABET)
        val completeKeywordAsList = completeKeyword.toSet().toList()
        val specialSymbols = listOf('å', 'ß', '∂')
        var index = specialSymbols.size - 1

        repeat(SQUARE_SIZE) { matrix.add(mutableListOf()) }

        completeKeywordAsList.forEach { char ->
            if (topIndex == SQUARE_SIZE) {
                topIndex = 0
            }
            matrix[topIndex].add(char)
            topIndex++
        }

        matrix.forEach { row ->
            if (row.size != SQUARE_SIZE) row.add(specialSymbols[index--])
        }

        return matrix
    }
}
