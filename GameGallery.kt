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

class TickTackToe {
    var board: MutableList<MutableList<String>>
    var symbol: String

    init {
        this.board =
                mutableListOf(
                        mutableListOf("0", "1", "2"),
                        mutableListOf("3", "4", "5"),
                        mutableListOf("6", "7", "8")
                )
        this.symbol = "x"
    }
    fun printBoard() {
        for (line in this.board) {
            println(line)
        }
    }
    fun placeOnBoard() {
        print("where do you want to place your (write the number it is on)")
        var placeToPlace = readln()

        for (line: MutableList<String> in this.board) {
            if (line.contains(placeToPlace)) {
                for ((colIndex, tile) in line.withIndex()) if (placeToPlace == tile) {
                    line[colIndex] = this.symbol
                    return
                }
            }
        }
    }
    fun win() {
        println("$symbol has won")
    }
    fun checkThreeInRow(): Boolean {

        for (list in this.board) {
            if (list.all { it == this.symbol }) {
                win()
                return false
            }
        }
        if (this.board[0][0] == this.symbol &&
                        this.board[1][1] == this.symbol &&
                        this.board[2][2] == this.symbol ||
                        this.board[2][2] == this.symbol &&
                                this.board[1][1] == this.symbol &&
                                this.board[0][0] == this.symbol
        ) {
            win()
            return false
        }
        // nedover
        var a: Int
        for (i in 0..2) {
            a = 0
            for (list in this.board) {
                if (list[i] == this.symbol) {
                    a = a + 1
                }
            }
            if (a >= 3) {
                win()
                return false
            }
        }
        return true
    }

    fun choiceSymbol() {
        if (this.symbol == "x") {
            this.symbol = "O"
        } else if (this.symbol == "O") {
            this.symbol = "x"
        } else {
            println("somthing is wrong")
        }
    }

    fun steps() {
        this.symbol = "O"
        this.board =
                mutableListOf(
                        mutableListOf("0", "1", "2"),
                        mutableListOf("3", "4", "5"),
                        mutableListOf("6", "7", "8")
                )

        while (true == checkThreeInRow()) {
            choiceSymbol()
            printBoard()
            placeOnBoard()
        }
    }
}

fun main() {
    val numberGuessingGame = NumberGuessingGame()
    val tickTackToe = TickTackToe()
    val mapOfGames: Map<String, () -> Unit> =
            hashMapOf(
                    "1" to numberGuessingGame::steps,
                    "2" to tickTackToe::steps,
            )
    while (true) {
        println("""
        1. NumberGuessingGame
        2. TickTackToe

        """)
        print("with game do you want to play? (write the number behined the game)")
        var game = readln()
        try {
            game.toInt()
            mapOfGames[game]?.invoke()
        } catch (e: NumberFormatException) {
            println("that is not a answer")
        }
    }
}
