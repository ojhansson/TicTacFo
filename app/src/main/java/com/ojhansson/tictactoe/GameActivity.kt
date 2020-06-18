package com.ojhansson.tictactoe

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.*
import com.ojhansson.tictactoe.database.Score
import com.ojhansson.tictactoe.database.ScoreViewModel
import com.ojhansson.tictactoe.utilities.winningLines
import com.ojhansson.tictactoe.model.Player
import com.ojhansson.tictactoe.utilities.EXTRA_PLAYER1
import com.ojhansson.tictactoe.utilities.EXTRA_PLAYER2
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random
import androidx.lifecycle.Transformations
import androidx.lifecycle.LiveData


class GameActivity : AppCompatActivity() {

    lateinit var model: ScoreViewModel

    lateinit var player1 : Player
    lateinit var player2 : Player
    lateinit var cellArray : Array<Button>

    lateinit var player1Score: Score
    lateinit var player2Score: Score

    var isFinished = false
    var activePlayer = 1
    var xFilledCells = mutableListOf<Int>()
    var oFilledCells = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        player1 = intent.getParcelableExtra(EXTRA_PLAYER1)
        player2 = intent.getParcelableExtra(EXTRA_PLAYER2)
        cellArray = arrayOf(cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8,
            cell9, cell10, cell11, cell12, cell13, cell14, cell15, cell16)

        chronometer.start()

        activePlayerTxt.text = "${player1.name} to play"


        model = ViewModelProviders.of(this).get(ScoreViewModel::class.java)

        player1Score = Score(null, player1.name, 0)
        player2Score = Score(null, player2.name, 0)

    }

    fun fillCell(selectedCell: Button, cellNo: Int) {
        if (activePlayer == 1) {
            selectedCell.text = "X"
            xFilledCells.add(cellNo)
            activePlayer = 2
            activePlayerTxt.text = "${player2.name} to play"


        } else if (activePlayer == 2) {
            selectedCell.text = "O"
            oFilledCells.add(cellNo)
            activePlayer = 1
            activePlayerTxt.text = "${player1.name} to play"
        }

        selectedCell.isEnabled = false
        checkFinished()
    }

    fun computerMove () {
        if(!isFinished){
            var ran = createRandomNumber()

            Log.v("TAG","final random pick$ran")
//            Toast.makeText(this, "Number: $ran", Toast.LENGTH_SHORT).show()

            var selectedCell : Button
            if(ran == 1) {
                selectedCell = cell1
            }
            else {
                selectedCell = cellArray[ran - 1]
            }

            val handler = Handler()
            handler.postDelayed({
                fillCell(selectedCell, ran)
                enableBoard()
            }, 1000)
        }
    }

    fun createRandomNumber() : Int {
        var ran = Random.nextInt(1, 17)
//        Toast.makeText(this, "Number: $ran", Toast.LENGTH_SHORT).show()
        Log.v("TAG","random pick$ran")
        if (!xFilledCells.contains(ran) && !oFilledCells.contains(ran)) return ran
        return createRandomNumber()
    }

    fun onCellClick(view: View) {
        if(!isFinished) {
            var selectedCell = view as Button
            var cellNo = 1
            when (selectedCell) {
                cell1 -> cellNo = 1
                cell2 -> cellNo = 2
                cell3 -> cellNo = 3
                cell4 -> cellNo = 4
                cell5 -> cellNo = 5
                cell6 -> cellNo = 6
                cell7 -> cellNo = 7
                cell8 -> cellNo = 8
                cell9 -> cellNo = 9
                cell10 -> cellNo = 10
                cell11 -> cellNo = 11
                cell12 -> cellNo = 12
                cell13 -> cellNo = 13
                cell14 -> cellNo = 14
                cell15 -> cellNo = 15
                cell16 -> cellNo = 16
            }

            fillCell(selectedCell, cellNo)
            if(player2.isCpu) {
                disableBoard()
                computerMove()
            }
        }
    }

    fun addToLeaderBoard(playerName: String) {

        val liveData = model.getScoreByName(playerName)

        liveData.observe(this,
            Observer<Score>(fun(score: Score?) {

                //Hvis spilleren finnes, hentes score fra livedata og sender til model for +1, og lukker observer
                if(score != null){
                    model.insert(score)
                    liveData.removeObservers(this)

                    //Hvis ikke legges det til en ny med 0 og sendes til insert der den får +1
                } else {
                    model.insert(Score(null, playerName, 0))
                    liveData.removeObservers(this)
                }
            })
        )
    }



    fun checkFinished() {
        for (lines in winningLines) {
            when {
                xFilledCells.containsAll(lines) -> {
                    isFinished = true
                    disableBoard()
                    activePlayerTxt.text = "${player1.name} won!"
                    addToLeaderBoard(player1.name)
                    chronometer.stop()

                }
                oFilledCells.containsAll(lines) -> {
                    isFinished = true
                    disableBoard()
                    activePlayerTxt.text = "${player2.name} won!"
                    addToLeaderBoard(player2.name)
                    chronometer.stop()

                }
                oFilledCells.size==8 -> {
                    isFinished = true
                    disableBoard()
                    activePlayerTxt.text = "It's a tie!"
                    chronometer.stop()
                }
            }
        }
    }

    fun endGame() {
        xFilledCells.clear()
        oFilledCells.clear()
        player1.name = ""
        player2.name = ""
    }

    fun disableBoard() {
        cellArray.forEach { it.isEnabled = false }
    }

    fun enableBoard() {
        cellArray.forEach { it.isEnabled = true }
    }

    fun onMenuClick(view: View) {

        endGame()
        val menuIntent = Intent(this, MainActivity::class.java).apply {
        }
        startActivity(menuIntent)
    }

    fun onResetClick(view: View) {
        recreate()
    }

}