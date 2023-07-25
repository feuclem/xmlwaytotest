package request

import org.junit.jupiter.api.Test
import java.time.LocalDate
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.intellij.lang.annotations.Language


class PersonToTravellersBuilderTest {

    @Test
    fun `verify order id and customer tag mapping`() {
        val personToTravellersBuilder = PersonToTravellersBuilder("1", LocalDate.MAX, "payed", emptyList())

        val result = personToTravellersBuilder.build()

        @Language("XML")
        val expected = """
            <ReferenceOrder>1</ReferenceOrder>
            <Customer>
                <purchaseDate>+999999999-12-31</purchaseDate>
                <status>payed</status>
            </Customer>
        """.removeMultipleWhiteSpacesAndNewsLines()
        assertThat(result, Matchers.containsString(expected))

    }
}

fun String.removeMultipleWhiteSpacesAndNewsLines(): String {
    return this
        .trimIndent()
        .replace(Regex(">\\s*<"), "><")
        .replace(Regex("  *"), " ")
        .replace(Regex("\n"), "")
}