data class Person (val name: String,
                   val phone: String,
                   val dateOfBirth: LocalDate

) {

    fun getAge(): Int {

        return Period.between(this.dateOfBirth, LocalDate.now()).years
    }
}
fun main() {
    val formatter = DateTimeFormatter.ofPattern("MM-dd-yyy")

    val dob = LocalDate.parse("10-15-1993", formatter)
    val person = Person(name="John", phone="123456789", dateOfBirth= dob )

    println("Age of ${person.name} is: ${person.getAge()}")
}
