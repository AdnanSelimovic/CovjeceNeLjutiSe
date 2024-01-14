object Main extends App {
  println("Hello, World!")



  // Create a new Board object
  val board = new Board()

  // Initialize the board
  board.initializeBoard()

  // Initialize players
 /* val players = Array(
    new Player(1, "red"),
    new Player(2, "green"),
    new Player(3, "blue"),
    new Player(4, "yellow")
  )*/
  val numberOfPlayers = chooseNumberOfPlayers()
  val players = initializePlayers(numberOfPlayers)
  val startingColor = chooseStartingColor()
  // Reorder players based on the starting color
  val orderedPlayers = reorderPlayersByStartingColor(players, startingColor)
 /*players.foreach { player =>
    player.initializePieces()
  }*/

  // Initialize player pieces
  orderedPlayers.foreach(_.initializePieces())

  // Initialize and start the game
  val game = new Game(board, orderedPlayers)
  game.startGame()

  // Create pieces for testing
 // val piece1 = new Piece(1, "red", "RP1")
  //val piece2 = new Piece(2, "green", "GP2")

  // Insert pieces at starting positions
  //board.insertPieceAtStart(piece1, "red")
  //board.insertPieceAtStart(piece2, "green")
  board.printBoard3();


  def chooseNumberOfPlayers(): Int = {
    println("Choose the number of players (2-4):")
    var numPlayers = 0
    do {
      numPlayers = scala.io.StdIn.readInt()
      if (numPlayers < 2 || numPlayers > 4) {
        println("Invalid number of players. Please enter a number between 2 and 4.")
      }
    } while (numPlayers < 2 || numPlayers > 4)
    numPlayers
  }

  def initializePlayers(numPlayers: Int): Array[Player] = {
    val colors = Array("yellow", "green", "red", "blue")
    val chosenColors = scala.collection.mutable.Set[String]()
    val players = new Array[Player](numPlayers)

    for (i <- 0 until numPlayers) {
      println(s"Player ${i + 1}, choose your color (${colors.mkString(", ")}):")
      var color = ""
      do {
        color = scala.io.StdIn.readLine().trim.toLowerCase
        if (!colors.contains(color) || chosenColors.contains(color)) {
          println("Invalid color or color already taken. Please choose one of the available colors: " + colors.mkString(", "))
          color = ""
        }
      } while (color.isEmpty)
      chosenColors.add(color)
      players(i) = new Player(i + 1, color)
    }
    players
  }

  def chooseStartingColor(): String = {
    println("Choose the starting color (yellow, green, red, blue):")
    var color = ""
    do {
      color = scala.io.StdIn.readLine().trim.toLowerCase
      if (!Array("yellow", "green", "red", "blue").contains(color)) {
        println("Invalid color. Please choose one of the following colors: yellow, green, red, blue.")
      }
    } while (!Array("yellow", "green", "red", "blue").contains(color))
    color
  }

  def reorderPlayersByStartingColor(players: Array[Player], startingColor: String): Array[Player] = {
    val colorOrder = Array("red", "blue", "yellow", "green")
    val startIndex = colorOrder.indexOf(startingColor)
    val orderedColors = colorOrder.drop(startIndex) ++ colorOrder.take(startIndex)

    orderedColors.flatMap(color => players.find(_.color == color))
  }

}
