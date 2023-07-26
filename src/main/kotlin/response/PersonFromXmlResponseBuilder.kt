package response

import Person
import java.time.LocalDate

class PersonFromXmlResponseBuilder(private var rootTag: RootTag) {
    private var firstName: String = ""
    private var lastName: String = ""
    private var email: String = ""
    private var inscriptionDate: LocalDate? = null
    private var age: Int? = null

    fun withFirstName(): PersonFromXmlResponseBuilder {
        firstName = rootTag.personTag!!.firstname ?: ""
        return this
    }

    fun withLastName(): PersonFromXmlResponseBuilder {
        lastName = rootTag.personTag!!.lastname ?: ""
        return this
    }

    fun withEmail(): PersonFromXmlResponseBuilder {
        email = rootTag.personTag!!.email ?: ""
        return this
    }

    fun withInscriptionDate(): PersonFromXmlResponseBuilder {
        inscriptionDate = LocalDate.parse(rootTag.date!!)
        return this
    }

    fun withAge() {

    }

    fun build(): Person {
        return Person(
            firstName = firstName,
            lastName = lastName,
            email = email,
            inscriptionDate = inscriptionDate,
            age = age,
        )
    }
}