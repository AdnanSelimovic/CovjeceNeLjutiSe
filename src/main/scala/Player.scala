import scala.Array

class Player(val id: Int, val color: String) {
    private var pieces: Array[Piece] = new Array[Piece](4)
    private var safeHouse: Array[Piece] = new Array[Piece](4)

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



}
