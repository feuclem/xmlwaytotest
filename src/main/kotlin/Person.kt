import java.time.LocalDate

data class Person(
    val firstName: String,
    val lastName: String,
    val email: String,
    val inscriptionDate: LocalDate? = null,
    val age: Int? = null,
)
