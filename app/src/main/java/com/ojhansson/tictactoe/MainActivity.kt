package com.ojhansson.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.ojhansson.tictactoe.WinningCombinations.winningLines
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var activePlayer = 0
    var xFilledCells = mutableListOf<Int>()
    var oFilledCells = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activePlayerTxt.text = "X to play"
    }

    fun fillCell(selectedCell: Button, cellNo: Int) {
        if (activePlayer == 0) {
            selectedCell.text = "X"
            xFilledCells.add(cellNo)
            activePlayer = 1
            activePlayerTxt.text = "O to play"

        } else if (activePlayer == 1) {
            selectedCell.text = "O"
            oFilledCells.add(cellNo)
            activePlayer = 0
            activePlayerTxt.text = "X to play"
        }

        selectedCell.isEnabled = false
        isFinished()

    }

    fun onCellClick(view : View) {
        var selectedCell = view as Button
        var cellNo = 0
        when(selectedCell.id) {
            R.id.cell0 -> cellNo = 0
            R.id.cell1 -> cellNo = 1
            R.id.cell2 -> cellNo = 2
            R.id.cell3 -> cellNo = 3
            R.id.cell4 -> cellNo = 4
            R.id.cell5 -> cellNo = 5
            R.id.cell6 -> cellNo = 6
            R.id.cell7 -> cellNo = 7
            R.id.cell8 -> cellNo = 8
        }

        fillCell(selectedCell, cellNo)
    }

    fun isFinished() {
        for (lines in winningLines) {
            if(xFilledCells.containsAll(lines)) {
                Toast.makeText(this, "X WON", Toast.LENGTH_LONG).show()
            }
            if(oFilledCells.containsAll(lines)) {
                Toast.makeText(this, "O WON", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun onResetClick(view: View) {
        recreate()
    }

}