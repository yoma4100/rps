import java.util.*
import kotlin.system.exitProcess
import kotlin.random.Random

fun main() {
    val optionParams = arrayOf("Камень", "Ножницы", "Бумага")
    val gameChoice = getGameChoice(optionParams)
    val userChoice = getUserChoice(optionParams)

    printResult(gameChoice, userChoice)
}

fun getGameChoice(optionParams: Array<String>): String {
    val random = Random.Default
    val gameChoice = optionParams[random.nextInt(optionParams.size)]

    return gameChoice
}

fun getUserChoice(optionParams: Array<String>): String {
    var userChoice = ""
    var choiceIsValid = false
    var i = 0

    println("Выбери один из вариантов:")
    optionParams.forEachIndexed { index, choice ->
        println("${index + 1} - $choice")
    }

    while (!choiceIsValid && i != 5) {
        var userInput = readLine()

        when {
            userInput == "1" -> userInput = "Камень"
            userInput == "2" -> userInput = "Ножницы"
            userInput == "3" -> userInput = "Бумага"
            userInput == "1 - Камень" -> userInput = "Камень"
            userInput == "2 - Ножницы" -> userInput = "Ножницы"
            userInput == "3 - Бумага" -> userInput = "Бумага"
        }

        if (!userInput.isNullOrBlank()) {
            val firstChar = userInput[0].uppercaseChar()
            val remainingChars = userInput.substring(1).lowercase(Locale.getDefault())
            userInput = firstChar + remainingChars
        }

        if (userInput.isNullOrBlank() || userInput !in optionParams) {
            i++
            when {
                i == 1 -> println("Не понял тебя")
                i in 2..4 -> println("Ты идиот?")
                else -> {
                    println("Идиот")
                    exitProcess(0)
                }
            }
        } else {
            if (userInput in optionParams) {
                userChoice = userInput
                choiceIsValid = true
                if (i in 1..3) println("Так бы сразу!")
                if (i == 4) println("То-то же!")
            }
        }
    }
    return userChoice
}

fun printResult(gameChoice: String, userChoice: String) {
    when {
        gameChoice == userChoice -> println("У машины тот же выбор - $gameChoice. Ничья получается")

        gameChoice == "Камень" && userChoice == "Ножницы" -> println("Выбор машины - $gameChoice. Теперь ты знаешь о ещё одной точке роста")
        gameChoice == "Ножницы" && userChoice == "Бумага" -> println("Выбор машины - $gameChoice. Теперь ты знаешь о ещё одной точке роста")
        gameChoice == "Бумага" && userChoice == "Камень" -> println("Выбор машины - $gameChoice. Теперь ты знаешь о ещё одной точке роста")

        userChoice == "Камень" && gameChoice == "Ножницы" -> println("Выбор машины - $gameChoice. На этот раз тебе повезло, кожаный мешок")
        userChoice == "Ножницы" && gameChoice == "Бумага" -> println("Выбор машины - $gameChoice. На этот раз тебе повезло, кожаный мешок")
        userChoice == "Бумага" && gameChoice == "Камень" -> println("Выбор машины - $gameChoice. На этот раз тебе повезло, кожаный мешок")
    }
}