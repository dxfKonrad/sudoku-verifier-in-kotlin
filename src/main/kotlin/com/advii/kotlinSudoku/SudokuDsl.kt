package com.advii.kotlinSudoku

class Sudoku(private val sudoku: Array<Array<Int>>) {

    fun isValid() =
        SudokuValidator(sudoku).isValid()
}

class SudokuBuilder {
    var file = ""

    inline fun file(file: () -> String) {
        this.file = file()
    }

    fun build(): Sudoku {
        val sudokuArray = SudokuReader().readSudokuArrayFromFile(file)
        return Sudoku(sudokuArray)
    }
}

fun sudoku(block: SudokuBuilder.() -> Unit): Sudoku = SudokuBuilder().apply(block).build()

