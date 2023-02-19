class Course {
    private var isFivePoint: Boolean = false
    private var grade: Int = 0
    private var gpaPoint: Int = 0

    init {
        println("Is your course a 5.0 course? Pre-AP or AP classes are generally 5.0: (Y or N, default N, case insensitive)")
        val isFivePoint = readln()
        if (isFivePoint.equals("Y", true)) {
            this.isFivePoint = true
        }
        this.grade = acceptInt("Input your grade:")
    }

    fun calculateGpaPoint() : Int {
        if (isFivePoint) {
            gpaPoint = when (grade) {
                //Anything above a 90 is a 5.0
                in 90..Int.MAX_VALUE -> 5
                in 80..89 -> 4
                in 75..79 -> 3
                in 70..74 -> 2
                else -> 0
            }
        } else {
            gpaPoint = when (grade) {
                in 90..Int.MAX_VALUE -> 4
                in 80..89 -> 3
                in 75..79 -> 2
                in 70..74 -> 1
                else -> 0
            }
        }
        return gpaPoint
    }
}

fun acceptInt(prompt: String) : Int {
    val validInput = false
    while (!validInput) {
        try {
            println(prompt)
            return readln().toInt()
        } catch (e: NumberFormatException) {
            println("Invalid input, please input an integer.")
        }
    }
    return -1
}
fun main() {
    println("Grade Calculator")
    //Catch a possible exception- if the user inputs a non-integer
    val numberOfCourses = acceptInt("Input number of courses:")
    //Parse numberOfCourses to an integer. If an error occurs, display an error message and exit the program
    println("You have $numberOfCourses courses")
    val classes = Array(numberOfCourses) {
        Course()
    }
    val gpaPoints = calculateGpaPoints(classes)
    println("Your GPA is $gpaPoints")
}

fun calculateGpaPoints(grades: Array<Course>): Double {
    //Collect the gpaPoints using calculateGpaPoint
    val gpaPoints = IntArray(grades.size)
    for (i in grades.indices) {
        gpaPoints[i] = grades[i].calculateGpaPoint()
    }
    if (grades.isEmpty()) {
        return -1.0
    }
    return gpaPoints.sum().toDouble()/gpaPoints.size
}