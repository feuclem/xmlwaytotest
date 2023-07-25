package request

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.time.LocalDate

@JacksonXmlRootElement(localName = "root")
data class RootTag(
    @JacksonXmlProperty(localName = "ReferenceOrder")
    val orderId: String,
    @JacksonXmlProperty(localName = "Customer")
    val customer: CustomerTag,
    @JacksonXmlProperty(localName = "Travellers")
    val travellers: TravellersTag? = null,
) {
    data class CustomerTag(
        @JacksonXmlProperty(localName = "purchaseDate")
        val orderDate: String,
        @JacksonXmlProperty(localName = "status")
        val orderStatus: String,
    )

    data class TravellersTag(
        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "Traveller")
        val list: List<TravellerTag>
    ) {
        data class TravellerTag(
            @JacksonXmlProperty(localName = "Firstname", isAttribute = true)
            val firstName: String,
            @JacksonXmlProperty(localName = "Lastname", isAttribute = true)
            val lastName: String,
        )
    }
}
