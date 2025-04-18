import kotlin.random.Random

class NumberGuessingGame {

    fun getUserIntGuess(message: String): Int {
        var number: Int

        while (true) {
            print(message)
            var guess = readln()
            try {
                number = guess.toInt()
                break
            } catch (e: NumberFormatException) {
                println("that is not a number")
            }
        }
        return number
    }

    fun steps() {
        println("starting")
        val random_number = Random.nextInt(0, 100)

        var win: Boolean = false

        var tries: Int = getUserIntGuess("how many tries? ")

        for (i in tries downTo 0) {
            println("you have $i chances left")
            val guess = getUserIntGuess("guess the number? ")

            if (guess == random_number) {
                println("you won!!!!!")
                win = true
                break
            }
            if (guess > random_number) {
                println("too hight ")
            } else if (guess < random_number) {
                println("too low")
            } else {
                println(" that is not a answer")
            }
        }
        if (win == false) {
            println("you lose")
        } else {
            println("congratulations")
        }
    }
}

fun main() {
    val numberGuessingGame = NumberGuessingGame()
    val mapOfGames: Map<String, () -> Unit> =
            hashMapOf(
                    "1" to numberGuessingGame::steps,
            )
    while (true) {
        println("""
        1. numberGuessingGame
        2.

        """)
        print("with game do you want to play? ")

        var game = readln()
        try {
            game.toInt()
            mapOfGames[game]?.invoke()
        } catch (e: NumberFormatException) {
            println("that is not a answer")
        }
    }
}
