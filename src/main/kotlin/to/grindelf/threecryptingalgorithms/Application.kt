package to.grindelf.threecryptingalgorithms

import to.grindelf.threecryptingalgorithms.encoders.Language
import to.grindelf.threecryptingalgorithms.encoders.SubstitutionEncoder

object Application {

    @JvmStatic
    fun main(argv: Array<String>) {
        testFirstEncoder()



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