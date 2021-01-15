package krasnikov.project.textapp

import kotlin.collections.ArrayList

data class Person(
    val firstName: String,
    val lastName: String,
) {
    companion object {
        // Arrays of names
        private val firstNames = arrayOf("Alex", "Victor", "Roma", "Vova")
        private val lastNames = arrayOf("Salo", "Ivanov", "Banan", "Pomidor")

        fun genPersonList(numberOfPerson: Int = 5): MutableList<Person> {
            val list: MutableList<Person> = ArrayList<Person>(5)

            for (i in 0..numberOfPerson) {
                list.add(Person(firstNames.random(), lastNames.random()))
            }

            return list
        }
    }
}