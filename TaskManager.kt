import kotlin.system.exitProcess

fun main() {
    val taskManager = TaskManager()
    while (true) {
        taskManager.acceptInput()
    }
}

class TaskManager {
    private var tasks: ArrayList<Task> = ArrayList()
    private var count = 0
    fun acceptInput() {
        println("Task Manager")
        println("1. Add a task")
        println("2. Remove a task/Mark a task as complete")
        println("3. View all tasks")
        println("4. Exit")
        println("Please select an option:")
        when (readln()) {
            "1" -> appendTask()
            "2" -> removeTask()
            "3" -> viewAllTasks()
            "4" -> exit()
            else -> println("Invalid input! Please try again.")
        }
    }

    private fun appendTask() {
        val task = Task()
        println("Enter task name:")
        task.name = readln()
        println("Enter in a suggested due date (not required)")
        task.dueDate = readln()
        task.id = count++
        tasks.add(task)
    }
    private fun removeTask() {
        println("Enter task ID:")
        val taskName = readln()
        try {
            val task = tasks.find { it.id == taskName.toInt() }
            if (task != null) {
                tasks.remove(task)
                println("Task completed!")
            } else {
                println("Task not found!")
            }
        } catch (e: NumberFormatException) {
            println("Invalid task ID! Please try again.")
        }
    }
    private fun viewAllTasks() {
        println("------------")
        tasks.forEach {
            it.printAsStr()
        }
    }
    private fun exit() {
        println("Confirm exit? (Y or N, case insensitive)")
        val input = readln()
        if (input.equals("Y", true)) {
            println("Exiting...")
            exitProcess(0)
        }
    }
}
class Task {
    var id: Int = 0
    var name: String = ""
    var dueDate: String = ""
    fun printAsStr() {
        println("Task ID: $id")
        println("Task Name: $name")
        println("Task Due Date: $dueDate")
        println("------------")
    }
}