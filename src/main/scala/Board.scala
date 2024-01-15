//import Comments.{BP1, BP3, YP1, YP3, blueO, path, spacing, startingPos, yellowO}
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
//            boardSpaces.insert(new Piece(index, "Empty", s"Empty$index"))
            boardSpaces.insert(new Piece(index, "Empty", "Empty"))
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



    //Function to move a piece on the board
    def movePiece(piece: Piece, steps: Int, player: Player): Unit = {
        val currentPosition = findPiecePosition(piece)

        if (currentPosition.isDefined) {
            val newPosition = (currentPosition.get + steps) % 40
            val safeHouseEntrance = safeHouseEntrances(player.color)
            val distanceToSafeHouse = if (newPosition >= safeHouseEntrance) {
                newPosition - safeHouseEntrance
            } else {
                40 - safeHouseEntrance + newPosition
            }

            // Check if piece should enter or has passed the safe house
            if (distanceToSafeHouse <= steps) {
                // Move piece into safe house
                val entered = player.movePieceToSafeHouse(piece, steps - distanceToSafeHouse)
                if (entered) {
                    boardSpaces.set(currentPosition.get, new Piece(currentPosition.get, "Empty", "Empty"))
                    println(s"Piece ${piece.getPieceName()} entered its safe house.")
                } else {
                    println(s"Piece ${piece.getPieceName()} cannot move into the safe house yet.")
                }
            } else {
                // Move piece normally on the board
                boardSpaces.set(currentPosition.get, new Piece(currentPosition.get, "Empty", "Empty"))
                boardSpaces.set(newPosition, piece)
                println(s"Piece ${piece.getPieceName()} moved to position $newPosition.")
            }
        } else {
            println(s"Piece ${piece.getPieceName()} is not on the board.")
        }
    }

    // Helper function for normal movement
    private def moveNormally(piece: Piece, currentPosition: Int, newPosition: Int): Unit = {
        boardSpaces.set(currentPosition, new Piece(currentPosition, "Empty", "Empty"))
        boardSpaces.set(newPosition, piece)
        println(s"Piece ${piece.getPieceName()} moved to position $newPosition.")
    }



    // Helper function to find the position of a piece on the board
    /*def findPiecePosition(piece: Piece): Option[Int] = {
        (0 until 40).find(index => boardSpaces.get(index) == piece)
    }*/

    def findPiecePosition(piece: Piece): Option[Int] = {
        (0 until 40).find(index => {
            val boardPiece = boardSpaces.get(index)
            boardPiece != null && boardPiece.pieceId == piece.pieceId && boardPiece.color == piece.color
        })
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


        val paths: Seq[String] = (0 until 40).map(index => getColoredPieceName(boardSpaces.get(index)))

//        initializeBoard()

        // this still has major issues, need work
        println(YP1 + YP2 + spacing + spacing + paths(38) + paths(39) + paths(0) + spacing + spacing + GP1 + GP2)
        println()
        println(YP3 + YP4 + spacing + spacing + paths(37) + greenO + paths(1) + spacing + spacing + GP3 + GP4)
        println()
        println(spacing + spacing + spacing + spacing + paths(36) + greenO + paths(2) + spacing + spacing + spacing + spacing)
        println()
        println(spacing + spacing + spacing + spacing + paths(35) + greenO + paths(3) + spacing + spacing + spacing + spacing)
        println()
        println(paths(30) + paths(31) + paths(32) + paths(33) + paths(34) + greenO + paths(4) + paths(5) + paths(6) + paths(7) + paths(8))
        println()
        println(paths(29) + yellowO + yellowO + yellowO + yellowO + spacing + redO + redO + redO + redO + paths(9))
        println()
        println(paths(28) + paths(27) + paths(26) + paths(25) + paths(24) + blueO + paths(14) + paths(13) + paths(12) + paths(11) + paths(10))
        println()
        println(spacing + spacing + spacing + spacing + paths(23) + blueO + paths(15) + spacing + spacing + spacing + spacing)
        println()
        println(spacing + spacing + spacing + spacing + paths(22) + blueO + paths(16) + spacing + spacing + spacing + spacing)
        println()
        println(BP1 + BP2 + spacing + spacing + paths(21) + blueO + paths(17) + spacing + spacing + RP1 + RP2)
        println()
        println(BP3 + BP3 + spacing + spacing + paths(20) + paths(19) + paths(18) + spacing + spacing + RP3 + RP4)
    }
    def printBoard4(players: Array[Player]): Unit = {
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


        val paths: Seq[String] = (0 until 40).map(index => getColoredPieceName(boardSpaces.get(index)))

        //        initializeBoard()

        // this still has major issues, need work
        println(YP1 + YP2 + spacing + spacing + paths(38) + paths(39) + paths(0) + spacing + spacing + GP1 + GP2)
        println()
        println(YP3 + YP4 + spacing + spacing + paths(37) + greenO + paths(1) + spacing + spacing + GP3 + GP4)
        println()
        println(spacing + spacing + spacing + spacing + paths(36) + greenO + paths(2) + spacing + spacing + spacing + spacing)
        println()
        println(spacing + spacing + spacing + spacing + paths(35) + greenO + paths(3) + spacing + spacing + spacing + spacing)
        println()
        println(paths(30) + paths(31) + paths(32) + paths(33) + paths(34) + greenO + paths(4) + paths(5) + paths(6) + paths(7) + paths(8))
        println()
        println(paths(29) + yellowO + yellowO + yellowO + yellowO + spacing + redO + redO + redO + redO + paths(9))
        println()
        println(paths(28) + paths(27) + paths(26) + paths(25) + paths(24) + blueO + paths(14) + paths(13) + paths(12) + paths(11) + paths(10))
        println()
        println(spacing + spacing + spacing + spacing + paths(23) + blueO + paths(15) + spacing + spacing + spacing + spacing)
        println()
        println(spacing + spacing + spacing + spacing + paths(22) + blueO + paths(16) + spacing + spacing + spacing + spacing)
        println()
        println(BP1 + BP2 + spacing + spacing + paths(21) + blueO + paths(17) + spacing + spacing + RP1 + RP2)
        println()
        println(BP3 + BP3 + spacing + spacing + paths(20) + paths(19) + paths(18) + spacing + spacing + RP3 + RP4)
        // Print the safe house status for each player
        players.foreach { player =>
            println(s"\nSafe House for Player ${player.id} (${player.color}):")
            val safeHouseStatus = player.getSafeHouseStatus
            safeHouseStatus.zipWithIndex.foreach { case (pieceOption, index) =>
                val display = pieceOption.map(_.getPieceName()).getOrElse("Empty")
                print(s"[$display]")
                if ((index + 1) % safeHouseStatus.length == 0) println()
            }
        }


    }

    // Helper function to get the symbol representation of a piece
    private def getSymbol(piece: Piece): String = {
        if (piece != null) {
            piece.getPieceName()
        } else {
            "Empty"
        }
    }



    // Helper function to get colored piece name
    private def getColoredPieceName(piece: Piece): String = {
        if (piece != null) {
            if (piece.getPieceName() == "Empty") {
                "  \u001b[37mO\u001b[0m  " // White "O" for "Empty" piece
            } else {
                val colorCode = getColorCode(piece.getColor())
                s" $colorCode${piece.getPieceName()}${Console.RESET} "
            }
        } else {
            "  \u001b[37mO\u001b[0m  " // White "O" for null piece
        }
    }

    // Helper function to get ANSI color code based on color string
    private def getColorCode(color: String): String = color.toLowerCase match {
        case "red"    => Console.RED
        case "green"  => Console.GREEN
        case "yellow" => Console.YELLOW
        case "blue"   => Console.BLUE
        case "Empty"  => Console.WHITE
        case _        => Console.RESET
    }
    private def calculateStepsToEntrance(currentPosition: Int, steps: Int, color: String): Int = {
        val entrancePosition = safeHouseEntrances(color)
        val distanceToEntrance = if (entrancePosition >= currentPosition) {
            entrancePosition - currentPosition
        } else {
            40 - currentPosition + entrancePosition
        }

        distanceToEntrance
    }



}
