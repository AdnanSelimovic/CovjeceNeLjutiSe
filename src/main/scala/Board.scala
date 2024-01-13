import Comments.{BP1, BP3, YP1, YP3, blueO, path, spacing, startingPos, yellowO}
import Util.CircularArray

class Board {
    private val boardSpaces: CircularArray[Piece] = new CircularArray[Piece](40)

    private val safeHouseEntrances: Map[String, Int] = Map(
        "green" -> 39,
        "red" -> 9,
        "blue" -> 19,
        "yellow" -> 29
    )

    private val startingPositions: Map[String, Int] = Map(
        "green" -> 0,
        "red" -> 10,
        "blue" -> 20,
        "yellow" -> 30
    )

    // Function to initialize the board
    def initializeBoard(): Unit = {
        // Initialize the circular array representing the spaces on the board
        for (index <- 0 until 40) {
            // For now, let's initialize each space with an empty piece
            boardSpaces.insert(new Piece(index, "Empty", s"Empty$index"))
        }

    }

    // Function to insert a piece into the starting position based on color
    def insertPieceAtStart(piece: Piece, color: String): Unit = {
        startingPositions.get(color).foreach { startPosition =>
            if (boardSpaces.get(startPosition).getColor() == "Empty") {
                boardSpaces.set(startPosition, piece)
                println(s"Piece ${piece.getPieceName()} inserted at the starting position for $color.")
            } else {
                println(s"Starting position for $color is occupied. Cannot insert piece.")
            }
        }
    }

    // Function to move a piece on the board
    def movePiece(piece: Piece, steps: Int): Unit = {
        val currentPosition = findPiecePosition(piece)

        // Ensure the piece is on the board
        if (currentPosition.isDefined) {
            val newPosition = (currentPosition.get + steps) % 40

            // Check for special spaces (safe houses)
            if (safeHouseEntrances.values.toList.contains(newPosition)) {
                // Implement logic for safe houses
                println(s"Piece ${piece.getPieceName()} reached a safe house.")
            } else {
                // Regular movement
                boardSpaces.set(currentPosition.get, new Piece(currentPosition.get, "Empty", s"Empty${currentPosition.get}"))
                boardSpaces.set(newPosition, piece)
                println(s"Piece ${piece.getPieceName()} moved to position $newPosition.")
            }
        } else {
            println(s"Piece ${piece.getPieceName()} is not on the board.")
        }
    }

    // Helper function to find the position of a piece on the board
    private def findPiecePosition(piece: Piece): Option[Int] = {
        (0 until 40).find(index => boardSpaces.get(index) == piece)
    }

    // Function to print the current state of the board
    def printBoard(): Unit = {
        for (i <- 0 until 40) {
            print(s"${boardSpaces.get(i).getPieceName()}\t")
            if ((i + 1) % 10 == 0) {
                println()
            }
        }
    }

    // Function to print the current state of the board
    def printBoard2(): Unit = {
        // Example formatting, you can customize it based on your requirements
        for (i <- 0 until 10) {
            for (j <- 0 until 4) {
                val index = i + j * 10
                val piece = boardSpaces.get(index)
                print(getSymbol(piece) + "\t")
            }
            println()
        }
    }

    def printBoard3(): Unit = {
        val path: String = "  \u001b[37mO\u001b[0m  "
        val greenO: String = "  \u001b[32mO\u001b[0m  "
        val redO: String = "  \u001b[31mO\u001b[0m  "
        val yellowO: String = "  \u001b[33mO\u001b[0m  "
        val blueO: String = "  \u001b[34mO\u001b[0m  "
        val spacing: String = "     "
        val startingPos: String = "  \u001b[90mO\u001b[0m  "

        val RP1: String = " \u001b[31mR\u001b[0m\u001b[31mP\u001b[0m\u001b[31m1\u001b[0m "
        val RP2: String = " \u001b[31mR\u001b[0m\u001b[31mP\u001b[0m\u001b[31m2\u001b[0m "
        val RP3: String = " \u001b[31mR\u001b[0m\u001b[31mP\u001b[0m\u001b[31m3\u001b[0m "
        val RP4: String = " \u001b[31mR\u001b[0m\u001b[31mP\u001b[0m\u001b[31m4\u001b[0m "
        val BP1: String = " \u001b[34mB\u001b[0m\u001b[34mP\u001b[0m\u001b[34m1\u001b[0m "
        val BP2: String = " \u001b[34mB\u001b[0m\u001b[34mP\u001b[0m\u001b[34m2\u001b[0m "
        val BP3: String = " \u001b[34mB\u001b[0m\u001b[34mP\u001b[0m\u001b[34m3\u001b[0m "
        val BP4: String = " \u001b[34mB\u001b[0m\u001b[34mP\u001b[0m\u001b[34m4\u001b[0m "
        val YP1: String = " \u001b[33mY\u001b[0m\u001b[33mP\u001b[0m\u001b[33m1\u001b[0m "
        val YP2: String = " \u001b[33mY\u001b[0m\u001b[33mP\u001b[0m\u001b[33m2\u001b[0m "
        val YP3: String = " \u001b[33mY\u001b[0m\u001b[33mP\u001b[0m\u001b[33m3\u001b[0m "
        val YP4: String = " \u001b[33mY\u001b[0m\u001b[33mP\u001b[0m\u001b[33m4\u001b[0m "
        val GP1: String = " \u001b[32mG\u001b[0m\u001b[32mP\u001b[0m\u001b[32m1\u001b[0m "
        val GP2: String = " \u001b[32mG\u001b[0m\u001b[32mP\u001b[0m\u001b[32m2\u001b[0m "
        val GP3: String = " \u001b[32mG\u001b[0m\u001b[32mP\u001b[0m\u001b[32m3\u001b[0m "
        val GP4: String = " \u001b[32mG\u001b[0m\u001b[32mP\u001b[0m\u001b[32m4\u001b[0m "

        println(YP1 + YP2 + spacing + spacing + path + path + startingPos + spacing + spacing + GP1 + GP2)
        println()
        println(YP3 + YP4 + spacing + spacing + path + greenO + path + spacing + spacing + GP3 + GP4)
        println()
        println(spacing + spacing + spacing + spacing + path + greenO + path + spacing + spacing + spacing + spacing)
        println()
        println(spacing + spacing + spacing + spacing + path + greenO + path + spacing + spacing + spacing + spacing)
        println()
        println(startingPos + path + path + path + path + greenO + path + path + path + path + path)
        println()
        println(path + yellowO + yellowO + yellowO + yellowO + spacing + redO + redO + redO + redO + path)
        println()
        println(path + path + path + path + path + blueO + path + path + path + path + startingPos)
        println()
        println(spacing + spacing + spacing + spacing + path + blueO + path + spacing + spacing + spacing + spacing)
        println()
        println(spacing + spacing + spacing + spacing + path + blueO + path + spacing + spacing + spacing + spacing)
        println()
        println(BP1 + BP2 + spacing + spacing + path + blueO + path + spacing + spacing + RP1 + RP2)
        println()
        println(BP3 + BP3 + spacing + spacing + startingPos + path + path + spacing + spacing + RP3 + RP4)
    }

    // Helper function to get the symbol representation of a piece
    private def getSymbol(piece: Piece): String = {
        if (piece != null) {
            piece.getPieceName()
        } else {
            "Empty"
        }
    }


}
