package to.grindelf.threecryptingalgorithms.utility

object Toolbox {

    val ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyz".toList()
    val RUSSIAN_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toList()
    val UNSUPPORTED_SYMBOL = Char(0)

    fun processPhrase(phrase: String): String = phrase.lowercase().filter { it.isLetter() }

    fun processKeyWord(phrase: String): String = processPhrase(phrase).toSet().joinToString("")

}

enum class Language {
    ENGLISH,
    RUSSIAN
}

class UnsupportedSymbolException : Throwable() {
    override val message: String = "This letter is unsupported!"
}
