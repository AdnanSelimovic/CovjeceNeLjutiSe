package Util

class CircularArray[T: Manifest](size: Int) {
    private val array: Array[T] = new Array[T](size)
    private var currentIndex: Int = 0

    // Function to insert an element into the circular array
    def insert(element: T): Unit = {
        array(currentIndex) = element
        currentIndex = (currentIndex + 1) % size
    }

    // Function to get the element at a specific index in the circular array
    def get(index: Int): T = {
        if (size > 0) {
            val circularIndex = (currentIndex + index) % size
            array(circularIndex)
        } else {
            throw new IndexOutOfBoundsException("Circular array is empty")
        }
    }

    // Function to set the element at a specific index in the circular array
    def set(index: Int, element: T): Unit = {
        if (size > 0) {
            val circularIndex = (currentIndex + index) % size
            array(circularIndex) = element
        } else {
            throw new IndexOutOfBoundsException("Circular array is empty")
        }
    }

    // Function to print the current state of the circular array
    def printArray(): Unit = {
        // Print the circular array in the order of insertion
        for (i <- 0 until size) {
            print(get(i) + "\t")
        }
        println()
    }
}
