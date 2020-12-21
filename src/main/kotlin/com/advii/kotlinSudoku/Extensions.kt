package com.advii.kotlinSudoku

import java.io.File
import kotlin.math.sqrt

fun sqrt(intValue: Int) = sqrt(intValue.toDouble()).toInt()


fun File.loadNumberList(filePath: String) = readText().trim().split(Regex("\\D+"))

val Array<Array<Int>>.subSectionSize: Int
        get() = sqrt(size)