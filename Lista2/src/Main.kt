data class UserInput(val name: String?, val email: String?, val age: String?)
data class UserProfile(
    var name: String = "",
    var email: String = "",
    var age: Int = 0,
    var isAdult: Boolean = false
)
fun buildProfile(input: UserInput?, logs: MutableList<String>): UserProfile? =
    input?.run {
        //hesli imie not null- ztrimuj
        val validName = name?.trim()
            //przyjmij jesli wieksze od 3 i nie null
            ?.takeIf { it.length >= 3 }
            //zwroc jezeli exception
            ?: return logs.also {
                it.add(
                    when {
                        name == null -> "Name is null"
                        name.trim().length < 3 -> "Name too short"
                        else -> "Name too short"
                    }
                )
            }.let { null }

        // email jezeli nie null
        val emailNotNull = email ?: return logs.also { it.add("Email is null") }.let { null }
        val validEmail = emailNotNull.trim().lowercase().takeIf { it.contains("@") }
            ?: return logs.also { it.add("Invalid email") }.let { null }

        // age jezeli nie null
        val ageNotNull = age ?: return logs.also { it.add("Age is null") }.let { null }
        val validAge = ageNotNull.toIntOrNull()
            ?: return logs.also { it.add("Age is not a number") }.let { null }

        UserProfile()
            .apply {
                name = validName
                email = validEmail
                age = validAge
                isAdult = validAge >= 18
            }
            .also { logs.add("Profile created for ${it.email}") }
    } ?: run {
        logs.add("Input is null")
        null
    }

fun main() {
    val logs = mutableListOf<String>()
    val inputOk = UserInput("Meh", "353724@uwr.edu.pl", null)
    val profile = buildProfile(inputOk, logs)
    println("Profile: $profile")
    println("Logs: $logs")

}
