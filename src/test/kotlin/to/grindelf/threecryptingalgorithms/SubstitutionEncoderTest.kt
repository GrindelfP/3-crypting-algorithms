package to.grindelf.threecryptingalgorithms

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

class SubstitutionEncoderTest {

    private val message = "Местами эти дома казались затерянными среди широкой, как поле, улицы и нескончаемых " +
            "деревянных заборов; местами сбивались в кучу, и здесь было заметно более движения народа и живости. " +
            "Попадались почти смытые дождем вывески с кренделями и сапогами, кое-где с нарисованными синими брюками " +
            "и подписью какого-то Аршавского портного"

    private val keyphrase = "Без предисловий, сей же час\n Позвольте познакомить вас:"

    @Test
    fun `GIVEN text and keyphrase WHEN encrypted and decrypted THEN received simmilar results`() {

        val trimmedMessage = message.lowercase().filter { it.isLetter() }

        val substitutionEncoder = SubstitutionEncoder(keyphrase, Language.RUSSIAN)

        val encrypted = substitutionEncoder.encrypt(message)
        val decrypted = substitutionEncoder.decrypt(encrypted)

        assertThat(decrypted).isEqualTo(trimmedMessage)
    }
}
