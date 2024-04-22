package to.grindelf.threecryptingalgorithms

import to.grindelf.threecryptingalgorithms.encoders.AdvancedSubstitutionEncoder
import to.grindelf.threecryptingalgorithms.encoders.SubstitutionEncoder
import to.grindelf.threecryptingalgorithms.encoders.TranspositionEncoder
import to.grindelf.threecryptingalgorithms.utility.Language

object Application {

    @JvmStatic
    fun main(argv: Array<String>) {

        print("Insert keyphrase: ")
        val keyphrase: String = readln()
        print("Insert your message: ")
        val message: String = readln()

        testFirstEncoder(keyphrase, message)

        testSecondEncoder()

        testThirdEncoder(keyphrase, message)

    }

    private fun testFirstEncoder(keyphrase: String, message: String) {
        println("====================\nSUBSTITUTION ENCODER\n====================")


        val substitutionEncoder = SubstitutionEncoder(keyphrase, Language.RUSSIAN)
        val encryptedMessage = substitutionEncoder.encrypt(message)
        println("Encrypted message: $encryptedMessage")
        println("Decrypted message: ${substitutionEncoder.decrypt(encryptedMessage)}")
    }

    private fun testSecondEncoder() {
        println("\n=============================\nADVANCED SUBSTITUTION ENCODER\n=============================")
        println("This algorithm is tested on a prescribed and phrase, " +
                "because phrase contains newline character and so cannot be inputted.")

        val message = "Без предисловий, сей же час \nПозвольте познакомить вас:"

        val advancedSubstitutionEncoder = AdvancedSubstitutionEncoder()
        val encryptedMessage = advancedSubstitutionEncoder.encrypt(message)
        println("Encrypted message: $encryptedMessage")
        println("Decrypted message: ${advancedSubstitutionEncoder.decrypt(encryptedMessage)}")
    }

    private fun testThirdEncoder(keyphrase: String, message: String) {
        println("\n=====================\nTRANSPOSITION ENCODER\n=====================")

        val transpositionEncoder = TranspositionEncoder(keyphrase)
        val encryptedMessage = transpositionEncoder.encrypt(message)
        println("Encrypted message: $encryptedMessage")
        println("Decrypted message: ${transpositionEncoder.decrypt(encryptedMessage)}")
    }


    /*
    KEY: Без предисловий, сей же час Позвольте познакомить вас:
    MESSAGE: Местами эти дома казались затерянными среди широкой, как поле, улицы и нескончаемых деревянных заборов; местами сбивались в кучу, и здесь было заметно более движения народа и живости. Попадались почти смытые дождем вывески с кренделями и сапогами, кое-где с нарисованными синими брюками и подписью какого-то Аршавского портного
     */

}
