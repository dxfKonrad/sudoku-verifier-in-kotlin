import com.advii.kotlinSudoku.sudoku

fun main(args: Array<String>) {

    val fileName = if (args.isEmpty()) {
        print("Please specify the sudoku file name:")
        readLine()!!
    } else {
        args[0]
    }

    val sudoku = sudoku {
        file {
            fileName
        }
    }


    if (sudoku.isValid()) {
        println("The sudoku is valid.")
    } else {
        println("The sudoku is NOT valid.")
    }
}