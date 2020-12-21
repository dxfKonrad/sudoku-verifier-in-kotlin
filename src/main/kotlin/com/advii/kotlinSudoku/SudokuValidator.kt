package com.advii.kotlinSudoku

import kotlinx.coroutines.*

class SudokuValidator(private val sudoku: Array<Array<Int>>) {

    private fun isColumnValid(columnNumber: Int): Boolean {

        return sudoku.indices.map {
            sudoku[it][columnNumber]
        }.groupingBy {
            it
        }.eachCount().filter {
           isValueValid(it)
        }.isEmpty()
    }

    private fun isRowValid(rowNumber: Int): Boolean {

        return sudoku[rowNumber].groupingBy {
            it
        }.eachCount().filter {
            isValueValid(it)
        }.isEmpty()
    }

    private fun isSubsectionValid(row: Int, column: Int): Boolean {

        return sudoku.slice(row until row + sudoku.subSectionSize).map {
            it.slice(column until column + sudoku.subSectionSize)
        }.flatMap { it.toList() }
            .groupingBy { it }.eachCount().filter {
                isValueValid(it)
            }.isEmpty()
    }

    private fun isValueValid(valueToCount: Map.Entry<Int, Int>) =
        valueToCount.value > 1 || valueToCount.key !in 1..sudoku.size


    fun isValid(): Boolean {

        with(sudoku) {
            val deferredList = indices.map {
                listOf(GlobalScope.async { isRowValid(it) },
                    GlobalScope.async { isColumnValid(it) },
                    GlobalScope.async {
                        isSubsectionValid(
                            it / subSectionSize * subSectionSize,
                            it % subSectionSize * subSectionSize
                        )
                    })
            }.flatMap { it.toList() }

            return runBlocking {
                deferredList.map {
                    it.await()
                }.none { !it }
            }
        }
    }
}
