package com.advii.kotlinSudoku

import com.advii.kotlinSudoku.exceptions.WrongSudokuSizeException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.nio.file.Paths
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SudokuDslTests {

    @Test
    fun `sudoku correctly validated`() {

        val path = Paths.get("").toAbsolutePath().toString()
        println("Working Directory = $path")

        val sudoku = sudoku {
            file {
                "data/9x9Sudoku.txt"
            }
        }

        assertTrue { sudoku.isValid() }
    }

    @Test
    fun `sudoku correctly invalidated`() {

        val path = Paths.get("").toAbsolutePath().toString()
        println("Working Directory = $path")

        val sudoku = sudoku {
            file {
                "data/9x9Sudoku_invalid.txt"
            }
        }

        assertFalse { sudoku.isValid() }
    }

    @Test
    fun `wrong size sudoku throws exception`() {

        val path = Paths.get("").toAbsolutePath().toString()
        println("Working Directory = $path")

        assertThrows<WrongSudokuSizeException> {
            sudoku {
                file {
                    "data/somenumbers.txt"
                }
            }
        }
    }
}