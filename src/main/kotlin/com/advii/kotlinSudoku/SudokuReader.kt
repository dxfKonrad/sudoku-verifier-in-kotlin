package com.advii.kotlinSudoku

import com.advii.kotlinSudoku.exceptions.WrongSudokuElementFormatException
import com.advii.kotlinSudoku.exceptions.WrongSudokuSizeException
import java.io.File

class SudokuReader {

    companion object {
        val SUPPORTED_SUDOKU_SIZES = arrayOf(4 * 4, 9 * 9)
    }

    fun readSudokuArrayFromFile(filePath: String): Array<Array<Int>> {
        val elements = readFileContentsIntoList(filePath)
        verifySudokuSize(elements)
        return convertElementListToSudokuArray(elements)
    }

    private fun readFileContentsIntoList(filePath: String) = File(filePath).loadNumberList(filePath)

    private fun verifySudokuSize(elements: List<String>) {
        if (!SUPPORTED_SUDOKU_SIZES.contains(elements.size)) {
            throw WrongSudokuSizeException(
                """
                    Read ${elements.size} elements.
                    However, only 4x4 and 9x9 Sudoku sizes are supported.
                """
            )
        }
    }

    private fun convertElementListToSudokuArray(elements: List<String>): Array<Array<Int>> {

        val sudokuSize = sqrt(elements.size)

        return Array(sudokuSize) { i ->
            Array(sudokuSize) { j ->
                try {
                    elements[i * sudokuSize + j].toInt()
                } catch (ex: NumberFormatException) {
                    throw WrongSudokuElementFormatException("Only digits are allowed in a sudoku")
                }
            }
        }
    }
}