package request

import Person
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.time.LocalDate

class PersonToTravellersBuilder(
    private val orderId: String,
    private val orderDate: LocalDate,
    private val orderStatus: String,
    private val persons: List<Person> = emptyList(),
) {

    private lateinit var customerTag: RootTag.CustomerTag
    private var travellersTag: RootTag.TravellersTag? = null

    init {
        customerTag = RootTag.CustomerTag(
            orderDate = orderDate.toString(),
            orderStatus = orderStatus,
        )
    }

    fun build(): String {
        val rootTag = RootTag(
            orderId = orderId,
            customer = customerTag,
            travellers = travellersTag
        )
        val xmlMapper = XmlMapper().registerKotlinModule().registerModule(JavaTimeModule()).disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

        return xmlMapper.writeValueAsString(rootTag)
    }
}