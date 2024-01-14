object Main extends App {
  println("Hello, World!")


//  val adnan: Player = new Player(1, "red")
//  adnan.initializePieces()
//  adnan.printPieces()
//
//  adnan.printSafeHouse()
//  adnan.enterPiece(1)
//  adnan.enterPiece(2)
//  adnan.enterPiece(3)
//  adnan.isSafeHouseFull
//  adnan.printSafeHouse()
//  adnan.enterPiece(4)
//  adnan.isSafeHouseFull
//  adnan.printSafeHouse()

  // Create a new Board object
  val board = new Board()

  // Initialize the board
  board.initializeBoard()

  // Initialize players
  val players = Array(
    new Player(1, "red"),
    new Player(2, "green"),
    new Player(3, "blue"),
    new Player(4, "yellow")
  )

  // Initialize player pieces
  players.foreach(_.initializePieces())

  // Initialize and start the game
  val game = new Game(board, players)
  game.startGame()

  // Create pieces for testing
 // val piece1 = new Piece(1, "red", "RP1")
  //val piece2 = new Piece(2, "green", "GP2")

  // Insert pieces at starting positions
  //board.insertPieceAtStart(piece1, "red")
  //board.insertPieceAtStart(piece2, "green")
  board.printBoard3();





}
