package com.ojhansson.tictactoe.utilities

const val EXTRA_PLAYER1 = "player1"
const val EXTRA_PLAYER2 = "player2"


    val winningLine1 = arrayListOf(1, 2, 3, 4)
    val winningLine2 = arrayListOf(5, 6, 7, 8)
    val winningLine3 = arrayListOf(9, 10, 11, 12)
    val winningLine4 = arrayListOf(13, 14, 15, 16)
    val winningLine5 = arrayListOf(1, 5, 9, 13)
    val winningLine6 = arrayListOf(2, 6, 10, 14)
    val winningLine7 = arrayListOf(3, 7, 11, 15)
    val winningLine8 = arrayListOf(4, 8, 12, 16)
    val winningLine9 = arrayListOf(1, 6, 11, 16)
    val winningLine10 = arrayListOf(4, 7, 10, 13)

    val winningLines = listOf<Collection<Int>>(
        winningLine1, winningLine2, winningLine3, winningLine4, winningLine5,
        winningLine6, winningLine7, winningLine8, winningLine9, winningLine10
    )
