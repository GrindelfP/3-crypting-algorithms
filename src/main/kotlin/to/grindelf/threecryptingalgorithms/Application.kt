package to.grindelf.threecryptingalgorithms

import to.grindelf.threecryptingalgorithms.encoders.AdvancedSubstitutionEncoder
import to.grindelf.threecryptingalgorithms.encoders.Language
import to.grindelf.threecryptingalgorithms.encoders.SubstitutionEncoder

object Application {

    @JvmStatic
    fun main(argv: Array<String>) {
        testFirstEncoder()

        testSecondEncoder()

    }

    private fun testSecondEncoder() {
        println("This algorithm is tested on a prescribed and phrase, " +
                "because phrase contains newline character and so cannot be inputted.")

        val message = "Без предисловий, сей же час \nПозвольте познакомить вас:"

        val advancedSubstitutionEncoder = AdvancedSubstitutionEncoder()
        val encryptedMessage = advancedSubstitutionEncoder.encrypt(message)
        println("Encrypted message: $encryptedMessage")
        println("Decrypted message: ${advancedSubstitutionEncoder.decrypt(encryptedMessage)}")
    }

    private fun testFirstEncoder() {
        print("Insert keyphrase: ")
        val keyphrase: String = readln()
        print("Insert your message: ")
        val message: String = readln()

        val substitutionEncoder = SubstitutionEncoder(keyphrase, Language.RUSSIAN)
        val encryptedMessage = substitutionEncoder.encrypt(message)
        println("Encrypted message: $encryptedMessage")
        println("Decrypted message: ${substitutionEncoder.decrypt(encryptedMessage)}")
    }

}