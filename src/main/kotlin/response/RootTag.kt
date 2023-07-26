package response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "root")
data class RootTag(
    @JacksonXmlProperty(localName = "person") val personTag: PersonTag? = null,
    @JacksonXmlProperty(localName = "date") val date: String? = null,
    val dynamicElements: Map<String, AgeTag> = emptyMap(),
) {
    @JacksonXmlRootElement(localName = "person")
    data class PersonTag(
        @JacksonXmlProperty(localName = "firstname", isAttribute = true) val firstname: String? = null,
        @JacksonXmlProperty(localName = "lastname", isAttribute = true) val lastname: String? = null,
        @JacksonXmlProperty(localName = "firstname2", isAttribute = true) val firstname2: String? = null,
        @JacksonXmlProperty(localName = "email", isAttribute = true) val email: String? = null,
    )

    data class AgeTag(
        @JacksonXmlProperty(localName = "age") val age: Int,
    )
}





