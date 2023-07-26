package request

import Person
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import request.RootTag.TravellersTag.TravellerTag
import java.time.LocalDate

class PersonToTravellersRequestBuilder(
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

    fun withTravellers(): PersonToTravellersRequestBuilder {
        travellersTag = RootTag.TravellersTag(persons.map { TravellerTag(firstName = it.firstName, lastName = it.lastName) })

        return this
    }

    fun build(): String {
        val rootTag = RootTag(
            orderId = orderId,
            customer = customerTag,
            travellers = travellersTag,
        )
        val xmlMapper = XmlMapper().registerKotlinModule().registerModule(JavaTimeModule()).disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

        return xmlMapper.writeValueAsString(rootTag)
    }
}