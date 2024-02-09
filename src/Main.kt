import java.time.LocalDateTime
import java.util.Scanner
import java.time.format.DateTimeFormatter



class TodoListApp() {
    data class Task(val description: String, val dueDateTime: LocalDateTime)

    private val todoList = mutableListOf<Task>()
    private val scanner = Scanner(System.`in`)
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    fun start() {
        var option: Int

        do {
            printMenu()
            option = scanner.nextInt()

            when (option) {
                1 -> displayTasks()
                2 -> addTask()
                3 -> removeTask()
                4 -> clearTasks()
                0 -> println("Exiting the app")
                else -> println("Invalid option. Please try again.")
            }
        } while (option != 0)
    }

    private fun printMenu() {
        println("\n--- ToDo List App")
        println("1. Display Tasks")
        println("2. Add Tasks")
        println("3. Remove Tasks")
        println("4. Clear All Tasks")
        println("0. Exit")
        println("Choose an option")
    }

    private fun displayTasks() {
        if (todoList.isEmpty()) {
            println("No tasks found.")
        } else {
            println("\n--- Tasks ---")
            for ((index, task) in todoList.withIndex()) {
                println("${index + 1}. ${task.description} - Due: ${task.dueDateTime.format(dateTimeFormatter)}")
            }
        }
    }

    private fun addTask(){
        print("Enter task description: ")
        val description = scanner.next()

        println("Enter due date and time (yyyy-MM-dd HH:mm): ")
        val dueDateTimeString = readLine()!!.toString()
        val dueDateTime = LocalDateTime.parse(dueDateTimeString, dateTimeFormatter)

        todoList.add(Task(description, dueDateTime))
        println("Task added successfully.")
    }

    private fun removeTask() {
        if (todoList.isEmpty()) {
            println("No tasks to remove.")
        } else {
            displayTasks()
            print("Enter the task number to remove: ")
            val taskNumber = scanner.nextInt()

            if (taskNumber > 0 && taskNumber <= todoList.size) {
                val removedTask = todoList.removeAt(taskNumber - 1)
                println("Task '${removedTask.description}' removed successfully.")
            } else {
                println("Invalid task number. Please try again.")
            }
        }
    }

    private fun clearTasks() {
        if (todoList.isEmpty()) {
            println("No tasks to clear.")
        } else {
            todoList.clear()
            println("All tasks cleared successfully.")
        }

    }
}

fun main() {
    val todoApp = TodoListApp()
    todoApp.start()
}