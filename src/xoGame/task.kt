package xoGame

import kotlin.math.max


enum class XOGameSign {
    X, O, EMPTY
}


// Cell of game field
data class Cell(val row: Int, val column: Int)

/*
interface Field<E> {

    val size: Int

    // Правильно ли здесь использовать Any ?
    operator fun get(row: Int, column: Int): Any

    operator fun get(cell: Cell): Any

    operator fun set(row: Int, column: Int, value: Any)
    operator fun set(cell: Cell, value: Any)
}
*/


class XOGameField(val size: Int) {

    //this.field = Array(size, { i -> Array(size, { j -> Cell(i, j, XOGameSign.EMPTY)})})

    private var field = mutableMapOf<Cell, XOGameSign>()

    init {

        for (row in 0 until size) {
            for (column in 0 until size)

                field[Cell(row, column)] = XOGameSign.EMPTY
        }
    }

    fun get(cell: Cell): XOGameSign = field[cell] ?: throw IllegalAccessException()
    fun get(row: Int, column: Int): XOGameSign = get(Cell(row, column))

    // Нужно ли делать эту функцию приватной?
    fun set(cell: Cell, value: XOGameSign) {

        field[cell] = value
    }

    fun set(row: Int, column: Int, value: XOGameSign) = set(Cell(row, column), value)

    fun cellAddX(cell: Cell) = this.set(cell, XOGameSign.X)
    fun cellAddO(cell: Cell) = this.set(cell, XOGameSign.O)
    fun cellClear(cell: Cell) = this.set(cell, XOGameSign.EMPTY)


    fun findLongestSequenceHorizontally(signRequested: XOGameSign): Int {

        var counter = 0
        var maxSequence = 0

        for (indexRow in 0 until size) {
            for (indexColumn in 0 until size) {

                if (get(Cell(indexRow, indexColumn)) == signRequested) counter++ else {

                    maxSequence = max(counter, maxSequence)
                    counter = 0
                }
            }

            maxSequence = max(counter, maxSequence)
            counter = 0
        }

        return maxSequence
    }


    fun findLongestSequenceVertically(signRequested: XOGameSign): Int {

        var counter = 0
        var maxSequence = 0

        for (indexColumn in 0 until size) {
            for (indexRow in 0 until size) {

                if (get(Cell(indexRow, indexColumn)) == signRequested) counter++ else {

                    maxSequence = max(counter, maxSequence)
                    counter = 0
                }
            }

            maxSequence = max(counter, maxSequence)
            counter = 0
        }

        return maxSequence
    }


    fun findLongestSequenceDiagonally(signRequested: XOGameSign): Int {


        var maxSequence = 0
        var counter = 0

        var indexRow = 0
        var indexColumn = 0

        for (startValue in 0 until size) {

            for (indexColumn in startValue until size) {

                if (get(Cell(indexRow, indexColumn)) == signRequested) counter++ else {

                    maxSequence = max(counter, maxSequence)
                    counter = 0
                }

                maxSequence = max(counter, maxSequence)
                counter = 0

                indexRow++

            }

            for (indexRow in startValue until size) {

                if (get(Cell(indexRow, indexColumn)) == signRequested) counter++ else {

                    maxSequence = max(counter, maxSequence)
                    counter = 0
                }

                maxSequence = max(counter, maxSequence)
                counter = 0

                indexColumn++

            }
        }

        return maxSequence
    }
}