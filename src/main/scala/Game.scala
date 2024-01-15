import scala.io.StdIn.readLine
import scala.util.Random

class Game(board: Board, players: Array[Player]) {
  private var currentPlayerIndex = 0
  private var turnCounter = 0

  def startGame(): Unit = {

    while (!isGameOver) {
      val currentPlayer = players(currentPlayerIndex)
      println(s"\nCurrent Board:")
      board.printBoard4(players)
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
    var diceRoll = 0
    var allowExtraRoll = true

    do {
      diceRoll = promptForDiceRoll(player)
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

      if (!player.noPiecesOnBoard(board) || diceRoll == 6) {
        movePieceBasedOnDiceRoll(player, diceRoll)
        if (diceRoll == 6) {
          println("You rolled a 6! Rolling again...")
          board.printBoard4(players)
        }
        allowExtraRoll = diceRoll == 6 && allowExtraRoll
      } else {
        println(s"Player ${player.id} skipped as no 6 was rolled.")
        allowExtraRoll = false
      }
    } while (allowExtraRoll)

    // Reset allowExtraRoll for the next turn
    allowExtraRoll = true
  }

  private def promptForDiceRoll(player: Player): Int = {
    println(s"Player ${player.id} (${player.color}), type 'throw' to roll the dice.")
    var input_throw = scala.io.StdIn.readLine().trim.toLowerCase
    while (input_throw != "throw" && input_throw != "lucky" && input_throw != "kec") {
      println("Invalid command. Please type 'throw' to roll the dice.")
      input_throw = scala.io.StdIn.readLine().trim.toLowerCase
    }
    if (input_throw == "throw"){
      rollDice()
    } else if(input_throw =="lucky"){
      rollDiceCheat()
    } else{
      rollDiceCheat1()
    }

  }

  private def rollDice(): Int = {
    Random.nextInt(6) + 1
  }

  private def rollDiceCheat(): Int = {
    6
  }
  private def rollDiceCheat1(): Int = {
    1
  }

  private def movePieceBasedOnDiceRoll(player: Player, diceRoll: Int): Unit = {
    if (player.noPiecesOnBoard(board) || (diceRoll == 6 && player.askIfStartNewPiece)) {
      val pieceId = player.getNextPieceId
      val newPiece = new Piece(pieceId, player.color, s"${player.color.charAt(0).toUpper}P$pieceId")
      board.insertPieceAtStart(newPiece, player.color)
    } else {
      val pieceId = player.selectPieceToMove(board)
      val piece = player.getPieceById(pieceId)
      board.movePiece(piece, diceRoll, player)
    }
  }

  private def isGameOver: Boolean = {
    // for Demir
    false
  }
}
