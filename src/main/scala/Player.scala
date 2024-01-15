import scala.Array

class Player(val id: Int, val color: String) {
    private var pieces: Array[Piece] = new Array[Piece](4)
    //private var safeHouse: Array[Piece] = new Array[Piece](4)
    private var pieceCount = 0


    private val safeHouseTrackLength = 4
    private var safeHouse: Array[Option[Piece]] = Array.fill(safeHouseTrackLength)(None)

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


    // Function to get a Piece by its pieceId
    def getPieceById(pieceId: Int): Piece = {
        pieces.find(_.getPieceId() == pieceId).orNull
    }

    // Function to enter a piece into the safeHouse


    // Function to check if the safeHouse is full


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

    def movePieceToSafeHouse(piece: Piece, steps: Int): Boolean = {
        val safeHouseIndex = safeHouse.indexOf(None)

        if (safeHouseIndex != -1 && steps <= safeHouseTrackLength - safeHouseIndex) {
            // There is space in the safe house and the piece can move the required steps
            safeHouse(safeHouseIndex) = Some(piece)
            true // Piece successfully moved to the safe house
        } else {
            false // No space or too many steps to move into the safe house
        }
    }


    def getSafeHouseStatus: Array[Option[Piece]] = safeHouse

    def isSafeHouseFull: Boolean = {
        safeHouse.count(_.isDefined) == 4
    }



}
