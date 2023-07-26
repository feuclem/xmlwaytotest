package request

import Person
import org.junit.jupiter.api.Test
import java.time.LocalDate
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.intellij.lang.annotations.Language


class PersonToTravellersRequestBuilderTest {

    @Test
    fun `verify order id and customer tag mapping`() {
        val personToTravellersRequestBuilder = PersonToTravellersRequestBuilder("1", LocalDate.MAX, "payed", emptyList())

        val result = personToTravellersRequestBuilder.build()

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

    @Test
    fun `verify travellers mapping`() {
        val personToTravellersRequestBuilder = PersonToTravellersRequestBuilder(
            orderId = "1",
            orderDate = LocalDate.MAX,
            orderStatus = "payed", persons = listOf(
                Person("Florencia", "Lia", ""), Person("Monika", "Gilbertson", "")
            ),
        )

        val result = personToTravellersRequestBuilder.withTravellers().build()

        @Language("XML")
        val expected = """
            <Travellers>
                <Traveller Firstname="Florencia" Lastname="Lia"/>
                <Traveller Firstname="Monika" Lastname="Gilbertson"/>
            </Travellers>
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