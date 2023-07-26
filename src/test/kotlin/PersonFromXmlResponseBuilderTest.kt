import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import response.PersonFromXmlResponseBuilder
import response.RootTag
import java.time.LocalDate

class PersonFromXmlResponseBuilderTest {

    @Test
    fun `map fields from person tag`() {
        @Language("XML")
        val xml = """
            <root>
              <person
                firstname="Florencia"
                lastname="Lia"
                city="Majuro"
                country="Turkey"
                firstname2="Celisse"
                lastname2="Angelis"
                email="Celisse.Angelis@yopmail.com"
              />
            </root>
        """.trimIndent()

        val xmlMapper = XmlMapper().registerKotlinModule().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        val personFromXmlResponseBuilder =
            PersonFromXmlResponseBuilder(rootTag = xmlMapper.readValue(xml, RootTag::class.java))
        val result = personFromXmlResponseBuilder.withFirstName().withLastName().withEmail().build()

        assertEquals("Florencia", result.firstName)
        assertEquals("Lia", result.lastName)
        assertEquals("Celisse.Angelis@yopmail.com", result.email)
    }

    @Test
    fun `map inscription date`() {
        @Language("XML")
        val xml = """
            <root>
                <date>2022-12-31</date>
            </root>
        """.trimIndent()

        val xmlMapper = XmlMapper().registerKotlinModule().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        val personFromXmlResponseBuilder =
            PersonFromXmlResponseBuilder(rootTag = xmlMapper.readValue(xml, RootTag::class.java))
        val result = personFromXmlResponseBuilder.withInscriptionDate().build()

        assertEquals(LocalDate.of(2022, 12, 31), result.inscriptionDate)
    }
}