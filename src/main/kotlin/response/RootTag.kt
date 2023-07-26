package response

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import response.RootTag.*

@JacksonXmlRootElement(localName = "root")
data class RootTag(
    @JacksonXmlProperty(localName = "person") val personTag: PersonTag? = null,
    @JacksonXmlProperty(localName = "date") val date: String? = null,
    @JsonIgnore val dynamicData: MutableMap<String, DynamicData> = mutableMapOf(),
) {
    @JacksonXmlRootElement(localName = "person")
    data class PersonTag(
        @JacksonXmlProperty(localName = "firstname", isAttribute = true) val firstname: String? = null,
        @JacksonXmlProperty(localName = "lastname", isAttribute = true) val lastname: String? = null,
        @JacksonXmlProperty(localName = "firstname2", isAttribute = true) val firstname2: String? = null,
        @JacksonXmlProperty(localName = "email", isAttribute = true) val email: String? = null,
    )

    data class DynamicData(val name: String, val age: Int)

    @JsonAnySetter
    fun addToDynamicData(name: String, node: JsonNode) {
        val age = node.get("age").asInt()
        dynamicData[name] = DynamicData(name, age)
    }
}





