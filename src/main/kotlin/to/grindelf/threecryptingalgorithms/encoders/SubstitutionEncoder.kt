package to.grindelf.threecryptingalgorithms.encoders

import to.grindelf.threecryptingalgorithms.utility.Toolbox.ENGLISH_ALPHABET
import to.grindelf.threecryptingalgorithms.utility.Toolbox.RUSSIAN_ALPHABET
import to.grindelf.threecryptingalgorithms.utility.Toolbox.UNSUPPORTED_SYMBOL


class SubstitutionEncoder(
    keyword: String,
    language: Language = Language.ENGLISH
) {

    private val keyword: String = processKeyWord(keyword)
    private val alphabet: List<Char> = initializeAlphabet(language)
    private val encryptorTable: List<Pair<Char, Char>> = generateEncryptorTable()

    fun encrypt(codePhrase: String): String = processSecretMessage(processPhrase(codePhrase), encrypt = true)

    fun decrypt(cipher: String): String = processSecretMessage(cipher, encrypt = false)

    private fun initializeAlphabet(language: Language): List<Char> = when (language) {
        Language.ENGLISH -> ENGLISH_ALPHABET
        Language.RUSSIAN -> RUSSIAN_ALPHABET
    }

    private fun processSecretMessage(phrase: String, encrypt: Boolean): String {
        var symbol: Char
        var message = ""

        phrase.forEach { char ->
            symbol = swapMySymbol(char, encrypt = encrypt)
            if (symbol == UNSUPPORTED_SYMBOL) throw UnsupportedSymbolException()
            message += symbol
        }

        return message
    }


    private fun swapMySymbol(char: Char, encrypt: Boolean): Char {
        var newSymbol = UNSUPPORTED_SYMBOL

        when (encrypt) {
            true -> encryptorTable.forEach { pair ->
                if (pair.first == char) newSymbol = pair.second
            }
            else -> encryptorTable.forEach { pair ->
                if (pair.second == char) newSymbol = pair.first
            }
        }

        return newSymbol
    }

    private fun generateEncryptorTable(): List<Pair<Char, Char>> {
        val table: MutableList<Pair<Char, Char>> = mutableListOf()

        val firstLevel: MutableList<Char> = keyword.toSet().toMutableList()
        firstLevel.addAll(alphabet)
        val firstLevelAsList: List<Char> = firstLevel.toSet().toList()

        alphabet.forEachIndexed { index, letter ->
            table.add(Pair(letter, firstLevelAsList[index]))
        }

        return table
    }

    private fun processPhrase(phrase: String): String = phrase.lowercase().filter { it.isLetter() }

    private fun processKeyWord(phrase: String): String = processPhrase(phrase).toSet().joinToString("")
}

enum class Language {
    ENGLISH,
    RUSSIAN
}

class UnsupportedSymbolException : Throwable() {
    override val message: String = "This letter is unsupported!"
}
