import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import response.PersonFromXmlResponseBuilder
import response.RootTag

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

        val expected = Person("Florencia", "Lia", "Celisse.Angelis@yopmail.com")
        assertEquals(expected, result)
    }
}