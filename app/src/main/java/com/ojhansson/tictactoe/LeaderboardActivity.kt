package com.ojhansson.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ojhansson.tictactoe.database.Score
import com.ojhansson.tictactoe.database.ScoreAdapter
import com.ojhansson.tictactoe.database.ScoreViewModel
import kotlinx.android.synthetic.main.activity_leaderboard.*

class LeaderboardActivity : AppCompatActivity() {

//    private lateinit var scoreViewModel: scoreViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)


        val scoreViewModel = ViewModelProviders.of(this).get(ScoreViewModel::class.java)


        val adapter = ScoreAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        scoreViewModel.allScores.observe(this,
            Observer<List<Score>> { score -> adapter.setScore(score) })
    }

    fun onMenuClick(view: View) {
        this.finish()
    }

}


