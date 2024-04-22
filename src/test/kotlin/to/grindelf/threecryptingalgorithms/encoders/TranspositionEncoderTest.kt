package to.grindelf.threecryptingalgorithms.encoders

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

class TranspositionEncoderTest {

    private val message = "Местами эти дома казались затерянными среди широкой, как поле, улицы и нескончаемых " +
            "деревянных заборов; местами сбивались в кучу, и здесь было заметно более движения народа и живости. " +
            "Попадались почти смытые дождем вывески с кренделями и сапогами, кое-где с нарисованными синими брюками " +
            "и подписью какого-то Аршавского портного"

    private val keyphrase = "Без предисловий, сей же час\n Позвольте познакомить вас:"

    @Test
    fun testOne() {
        val transpositionEncoder = TranspositionEncoder(keyphrase)
        val encoded = transpositionEncoder.encrypt(message)
        val decoded = transpositionEncoder.decrypt(encoded)

        assertThat(decoded).isEqualTo(message.lowercase().filter { it.isLetter() })
    }
}
