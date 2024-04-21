package to.grindelf.threecryptingalgorithms.encoders

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AdvancedSubstitutionEncoderTest {

    private val advancedSubstitutionEncoder = AdvancedSubstitutionEncoder()

    private val message = "Местами эти дома казались затерянными среди широкой, как поле, улицы и нескончаемых " +
            "деревянных заборов; местами сбивались в кучу, и здесь было заметно более движения народа и живости. " +
            "Попадались почти смытые дождем вывески с кренделями и сапогами, кое-где с нарисованными синими брюками " +
            "и подписью какого-то Аршавского портного"

    @Test
    fun test() {
        val trimmedMessage = message.lowercase().filter { it.isLetter() }

        val encrypted = advancedSubstitutionEncoder.encrypt(trimmedMessage)

        println(encrypted.filter { it.isLetter() })
        val decrypted = advancedSubstitutionEncoder.decrypt(encrypted.filter { it.isLetter() })

        println(encrypted)
        println(decrypted)
    }
}