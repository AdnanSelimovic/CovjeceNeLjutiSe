object Main extends App {
  println("Hello, World!")


  val adnan: Player = new Player(1, "red")
  adnan.initializePieces()
  adnan.printPieces()

  adnan.printSafeHouse()
  adnan.enterPiece(1)
  adnan.enterPiece(2)
  adnan.enterPiece(3)
  adnan.isSafeHouseFull
  adnan.printSafeHouse()
  adnan.enterPiece(4)
  adnan.isSafeHouseFull
  adnan.printSafeHouse()

}
