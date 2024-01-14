import scala.io.StdIn.readLine
import scala.util.Random

class Game(board: Board, players: Array[Player]) {
  private var currentPlayerIndex = 0
  private var turnCounter = 0

  def startGame(): Unit = {
    while (!isGameOver) {
      val currentPlayer = players(currentPlayerIndex)
      println(s"\nCurrent Board:")
      board.printBoard3()
      println(s"Player ${currentPlayer.id}'s (${currentPlayer.color}) turn:")
      handlePlayerTurn(currentPlayer)

      // Proceed to next player
      currentPlayerIndex = (currentPlayerIndex + 1) % players.length
      if (currentPlayerIndex == 0) {
        turnCounter += 1
      }
    }

    println(s"Game Over after $turnCounter turns")
    // Add logic to declare the winner
  }

  private def handlePlayerTurn(player: Player): Unit = {
    println(s"Current Turn: $turnCounter")
    val hasPieces = !player.noPiecesOnBoard(board)
    println(s"Player ${player.id} (${player.color}) has pieces on the board: $hasPieces")
    var diceRoll = 0

    println(s"Player ${player.id} (${player.color}), type 'throw' to roll the dice.")
    while (scala.io.StdIn.readLine().trim.toLowerCase != "throw") {
      println("Invalid command. Please type 'throw' to roll the dice.")
    }

    diceRoll = rollDice()
    println(s"Dice roll: $diceRoll")

    if (turnCounter == 0 && player.noPiecesOnBoard(board)) {
      var attempts = 1
      while (diceRoll != 6 && attempts < 3) {
        println("Rolling again...")
        diceRoll = rollDice()
        println(s"Dice roll: $diceRoll")
        attempts += 1
      }
    }

    if (player.noPiecesOnBoard(board) && diceRoll != 6) {
      println(s"Player ${player.id} skipped as no 6 was rolled.")
    } else {
      movePieceBasedOnDiceRoll(player, diceRoll)
    }
  }


  private def rollDice(): Int = {
    Random.nextInt(6) + 1
  }

  private def movePieceBasedOnDiceRoll(player: Player, diceRoll: Int): Unit = {
    if (player.noPiecesOnBoard(board) || (diceRoll == 6 && player.askIfStartNewPiece)) {
      // Start a new piece if no pieces on board or player chooses to start a new one on a roll of 6
      val newPiece = new Piece(1, player.color, s"${player.color.charAt(0).toUpper}P1") // You can enhance the logic to choose which piece to start
      board.insertPieceAtStart(newPiece, player.color)
    } else {
      // Move an existing piece
      val pieceId = player.selectPieceToMove(board)
      val piece = player.getPieceById(pieceId)
      board.movePiece(piece, diceRoll)
    }
  }

  private def isGameOver: Boolean = {
    // for Demir
    false
  }
}
