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

  // Create pieces for testing
  val piece1 = new Piece(1, "red", "RP1")
  val piece2 = new Piece(2, "green", "GP2")

  // Insert pieces at starting positions
  board.insertPieceAtStart(piece1, "red")
  board.insertPieceAtStart(piece2, "green")

  // Print the board after inserting pieces
  board.printBoard()

  // Move a piece
  board.movePiece(piece1, 3)

  // Print the board after moving the piece
  board.printBoard()

  board.printBoard2()

  board.printBoard3()

  board.movePiece(piece1, 2)
  board.printBoard3()

  board.movePiece(piece1, 4)
  board.printBoard3()

  board.movePiece(piece2, 6)
  board.printBoard3()

  board.movePiece(piece1, 17)
  board.printBoard3()

  board.movePiece(piece1, 12)
  board.printBoard3()

  board.movePiece(piece1, 3)
  board.printBoard3()

}
