package to.grindelf.threecryptingalgorithms.encoders

import kotlin.math.ceil

class AdvancedSubstitutionEncoder(
    private val key: String = "шипунов",
) {

    private val priorities: List<Int> = getColumnsPriorities(key)
    private val keyLength: Int = key.length

    companion object {
        private const val CHUNK_SIZE = 5
    }

    fun encrypt(message: String): String = produceEncryptedMessage(
        priorities, initializeMessageMatrix(message, columns = key.length)
    )


    fun decrypt(message: String): String {
        var messageProcessed = message
        val rowsNumber = getNumberOfRows(message.length) // 4
        val irregularColumns = getIrregularColumnsIndexes(message.length, rowsNumber) // [5, 4, 3]

        // ehmsehoaepwtanrci
        // ehms ehoa epwt anrc i
        // ehms eho aepw tanrci

        val matrix = mutableListOf<MutableList<Char>>()

        repeat(keyLength) { matrix.add(mutableListOf()) }

        var fragment = ""

        priorities.forEach { priorityIndex ->
            if (irregularColumns.contains(priorityIndex)) {
                fragment = messageProcessed.substring(0, rowsNumber - 1).plus(" ")
                messageProcessed = messageProcessed.drop(rowsNumber - 1)

            } else {
                fragment = messageProcessed.substring(0, rowsNumber)
                messageProcessed = messageProcessed.drop(rowsNumber)
            }
            fragment.forEach { char ->
                matrix[priorityIndex].add(char)
            }
        }

        println(matrix)

        // matrix is done

        var decryptedMessage = ""
        for (i in 0 until matrix[0].size) {
            for (j in 0 until matrix.size){
                decryptedMessage += matrix[j][i]
            }
        }

        return decryptedMessage
    }

    private fun getIrregularColumnsIndexes(length: Int, rows: Int): List<Int> {
        val numOfIrregulars = rows * keyLength % length
        val indexes = mutableListOf<Int>()

        var top = keyLength - 1

        repeat(numOfIrregulars) { indexes.add(top--) }

        return indexes
    }

    private fun getNumberOfRows(length: Int): Int {
        val lenAsDouble = length.toDouble()
        val numberOfRows = lenAsDouble / keyLength.toDouble()

        return ceil(numberOfRows).toInt()
    }

    private fun reorderColumns(matrix: List<List<Char>>, priorities: List<Int>): List<List<Char>> {
        val reorderedMatrix = mutableListOf<List<Char>>()
        for (priorityIndex in priorities) {
            reorderedMatrix.add(matrix[priorityIndex])
        }
        return reorderedMatrix
    }


    private fun produceEncryptedMessage(priorities: List<Int>, messageMatrix: List<List<Char>>): String {
        var message = ""
        priorities.forEach { priorityIndex ->
            val currentColumn = messageMatrix[priorityIndex]
            message += currentColumn.joinToString("")
        }

        return message.chunked(CHUNK_SIZE).joinToString(" ")
    }

    private fun initializeMessageMatrix(message: String, columns: Int): List<List<Char>> {
        val chars = message.toList()
        val matrix = mutableListOf<List<Char>>()

        for (i in 0 until columns) {
            val column = mutableListOf<Char>()
            for (j in i until chars.size step columns) {
                column.add(chars[j])
            }
            matrix.add(column)
        }

        return matrix
    }

    private fun getColumnsPriorities(key: String): List<Int> {
        val priorities = mutableListOf<Int>()
        val codes = mutableListOf<Int>()

        key.forEach { char ->
            codes.add(char.code)
        }

        while (codes.any { it != Int.MAX_VALUE }) {
            val minIndex = codes.indices.minByOrNull { codes[it] } ?: break
            priorities.add(minIndex)
            codes[minIndex] = Int.MAX_VALUE
        }

        return priorities.reversed()
    }
}
