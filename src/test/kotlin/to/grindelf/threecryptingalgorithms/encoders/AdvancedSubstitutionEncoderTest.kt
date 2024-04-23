package to.grindelf.threecryptingalgorithms.encoders

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

class AdvancedSubstitutionEncoderTest {

    private val advancedSubstitutionEncoder = AdvancedSubstitutionEncoder("брусилова")

    private val message = "апельсин яблоко лимон мандарин"

    @Test
    fun test() {

        val trimmedMessage = message.lowercase().replace(" ", "")

        val encrypted = advancedSubstitutionEncoder.encrypt(message)
        val decrypted = advancedSubstitutionEncoder.decrypt(encrypted)

        assertThat(decrypted).isEqualTo(trimmedMessage)
    }
}
