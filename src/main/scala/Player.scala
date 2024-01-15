import scala.Array

class Player(val id: Int, val color: String) {
    private var pieces: Array[Piece] = new Array[Piece](4)
    private var safeHouse: Array[Piece] = new Array[Piece](4)
    private var pieceCount = 0

    def initializePieces(): Unit = {
        for (i <- pieces.indices) {
            pieces(i) = new Piece(i + 1, color, s"${color.charAt(0).toUpper}P${i + 1}")
        }
    }

    // Function to print pieces of the player
    def printPieces(): Unit = {
        if (pieces != null) {
            for (i <- 0 until 4) {
                println(pieces(i).getPieceName())
            }
        }
    }

    // Function to print pieces in the safeHouse
    def printSafeHouse(): Unit = {
        if (safeHouse != null) {
            for (i <- 0 until 4) {
                if (safeHouse(i) != null) {
                    println(safeHouse(i).getPieceName())
                } else {
                    println("Empty slot in safeHouse")
                }
            }
        }
    }

    // Function to get a Piece by its pieceId
    def getPieceById(pieceId: Int): Piece = {
        pieces.find(_.getPieceId() == pieceId).orNull
    }

    // Function to enter a piece into the safeHouse
    def enterPiece(pieceId: Int): Unit = {
        val piece = getPieceById(pieceId)

        if (piece != null) {
            if (safeHouse.contains(null)) {
                val emptyIndex = safeHouse.indexOf(null)
                safeHouse(emptyIndex) = piece
                println(s"Piece ${piece.getPieceName()} entered the safeHouse.")
            } else {
                println("SafeHouse is full. Cannot enter more pieces.")
            }
        } else {
            println("Invalid piece ID. Cannot enter piece into the safeHouse.")
        }
    }

    // Function to check if the safeHouse is full
    def isSafeHouseFull: Boolean = {
        !safeHouse.contains(null)
    }

    // Function to check if the player has no pieces on the board
    def noPiecesOnBoard(board: Board): Boolean = {
        // Checking if all player's pieces are not on the board
        pieces.forall(piece => board.findPiecePosition(piece).isEmpty)
    }

    // Function to allow the player to select a piece to move
    def selectPieceToMove(board: Board): Int = {
        // Filter to get only the pieces that are on the board
        val piecesOnBoard = pieces.filter(piece => board.findPiecePosition(piece).isDefined)

        println(s"Player $id (${color}), select a piece to move:")
        for (i <- piecesOnBoard.indices) {
            println(s"${i + 1}: Piece ${piecesOnBoard(i).getPieceName()}")
        }

        var selectedPieceIndex = -1
        do {
            print("Enter the number of the piece you want to move: ")
            selectedPieceIndex = scala.io.StdIn.readInt() - 1
        } while (selectedPieceIndex < 0 || selectedPieceIndex >= piecesOnBoard.length)

        // Return the pieceId of the selected piece
        piecesOnBoard(selectedPieceIndex).getPieceId()
    }

    def askIfStartNewPiece(): Boolean = {
        print(s"Player $id, do you want to start a new piece? (y/N): ")
        val response = scala.io.StdIn.readLine().trim.toLowerCase
        response == "y" || response == "yes"
    }
    def getPieces: Array[Piece] = pieces.clone()

    def getNextPieceId: Int = {
        pieceCount += 1
        pieceCount
    }

    def enterPieceIntoSafeHouse(piece: Piece): Unit = {
        // Find the first empty slot in the safe house
        val emptyIndex = safeHouse.indexOf(null)

        // If there is an empty slot, place the piece there
        if (emptyIndex != -1) {
            safeHouse(emptyIndex) = piece
            println(s"Piece ${piece.getPieceName()} entered the safe house.")
        } else {
            println("No empty slots in the safe house.")
        }
    }


}
