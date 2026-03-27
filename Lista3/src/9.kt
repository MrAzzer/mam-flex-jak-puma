data class StudentScore(val name: String, val subject: String, val score: Int)



fun analyzeResults(
    students: List<StudentScore>
): Triple<Map<String, List<StudentScore>>, List<StudentScore>, List<String>> {

    val passThreshold = 50


    val (passed, failed) = students.partition { it.score >= passThreshold }

    //przedmiot zdany
    val passedBySubject: Map<String, List<StudentScore>> =
        passed.groupBy { it.subject }

    //niezdane
    val failedBySubject: Map<String, List<StudentScore>> =
        failed.groupBy { it.subject }

    //wszystkie zdane
    val subjectsAllPassed: List<String> =
        students.map { it.subject }
            .distinct()
            .filter { subj -> failedBySubject[subj].isNullOrEmpty() }

    return Triple(passedBySubject, failed, subjectsAllPassed)
}

fun main() {
    val students = listOf(
        StudentScore("Alice", "Math", 78),
        StudentScore("Bob", "Math", 45),
        StudentScore("Charlie", "Physics", 92),
        StudentScore("Dave", "Physics", 55),
        StudentScore("Eve", "Physics", 40),
        StudentScore("Frank", "CS", 60),
        StudentScore("Grace", "CS", 80),
    )

    val (passedBySubject, failed, subjectsAllPassed) = analyzeResults(students)

    println("Zdani studenci według przedmiotów: $passedBySubject")
    println("Niezdani studenci: $failed")
    println("Przedmioty, w których wszyscy zdali: $subjectsAllPassed")
}
