package com.ojhansson.tictactoe

object WinningCombinations {
    val winningLine1 = arrayListOf(0, 1, 2)
    val winningLine2 = arrayListOf(3, 4, 5)
    val winningLine3 = arrayListOf(6, 7, 8)
    val winningLine4 = arrayListOf(0, 3, 6)
    val winningLine5 = arrayListOf(1, 4, 7)
    val winningLine6 = arrayListOf(2, 5, 8)
    val winningLine7 = arrayListOf(0, 4, 8)
    val winningLine8 = arrayListOf(2, 4, 6)

    val winningLines = listOf<Collection<Int>>(winningLine1, winningLine2, winningLine3, winningLine4,
                                                winningLine5, winningLine6, winningLine7, winningLine8)
}