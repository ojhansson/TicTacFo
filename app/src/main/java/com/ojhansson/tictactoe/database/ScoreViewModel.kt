package com.ojhansson.tictactoe.database

import android.app.Application


import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScoreViewModel(application: Application) : AndroidViewModel(application) {

    internal val allScores: LiveData<List<Score>>
    private val repository: ScoreRepository = ScoreRepository(application)

    init {
        allScores = repository.allScores
    }

    fun insert(score: Score) {
        var newScoreNumber = score.scoreNumber!!.toInt() + 1

        var newScore = Score(score.id, score.name, newScoreNumber)
        repository.insert(newScore)
    }

    fun getScoreByName(strName: String): LiveData<Score> {
        return repository.getScoreByName(strName)
    }
}