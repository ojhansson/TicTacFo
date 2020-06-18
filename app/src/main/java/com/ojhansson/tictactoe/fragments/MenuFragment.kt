package com.ojhansson.tictactoe.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.ojhansson.tictactoe.GameActivity
import com.ojhansson.tictactoe.LeaderboardActivity
import com.ojhansson.tictactoe.R
import com.ojhansson.tictactoe.model.Player
import com.ojhansson.tictactoe.utilities.EXTRA_PLAYER1
import com.ojhansson.tictactoe.utilities.EXTRA_PLAYER2


class MenuFragment : androidx.fragment.app.Fragment() {

    private lateinit var playBtn: Button
    private lateinit var lbBtn: Button
    private lateinit var cpuSwitch: Switch

    private lateinit var player1Field: EditText
    private lateinit var player2Field: EditText

    var player1 = Player("", false)
    var player2 = Player("", false)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val fragView = inflater.inflate(R.layout.fragment_menu, container, false)

        playBtn = fragView.findViewById(R.id.playBtn)
        lbBtn = fragView.findViewById(R.id.lbBtn)
        cpuSwitch = fragView.findViewById(R.id.cpuSwitch)
        player1Field = fragView.findViewById(R.id.player1Field)
        player2Field = fragView.findViewById(R.id.player2Field)

        return fragView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cpuSwitch.setOnCheckedChangeListener { view, isChecked ->
            if (isChecked) {
                player2Field.setText("TTTBot")
                player2Field.setTextColor(Color.BLACK)
                player2.isCpu = true
                player2Field.isEnabled = false
            } else {
                player2Field.setText("")
                player2.isCpu = false
                player2Field.isEnabled = true
            }

        }
        playBtn.setOnClickListener {
            startGame()
        }

        lbBtn.setOnClickListener {
            val lbIntent = Intent(activity, LeaderboardActivity::class.java)
            startActivity(lbIntent)
        }
    }



    fun setPlayers() {
        player1.name = player1Field.text.toString()
        player2.name = player2Field.text.toString()
    }

    fun startGame() {

        if(player1Field.text.isBlank() || player2Field.text.isBlank()) {
            Toast.makeText(activity, "Please enter your names", Toast.LENGTH_LONG).show()
        } else {
            setPlayers()

            val gameIntent = Intent(activity, GameActivity::class.java).apply {
                putExtra(EXTRA_PLAYER1, player1)
                putExtra(EXTRA_PLAYER2, player2)
            }
            startActivity(gameIntent)
        }
    }
}



