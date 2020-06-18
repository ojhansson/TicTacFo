package com.ojhansson.tictactoe.database

import android.app.Application
import androidx.lifecycle.LiveData
import android.os.AsyncTask

class ScoreRepository internal constructor(application: Application) {

    private val scoreDao: ScoreDao
    internal val allScores: LiveData<List<Score>>

    init {
        val db = ScoreDatabase.getDatabase(application)
        scoreDao = db.scoreDao()
        allScores = scoreDao.getAllScores()
    }

    class AsyncInsert internal constructor(private val asyncDao: ScoreDao) : AsyncTask<Score, Void, Void>() {
        override fun doInBackground(vararg params: Score): Void? {
            asyncDao.insert(params[0])
            return null
        }
    }

    fun insert(score: Score) {
        AsyncInsert(scoreDao).execute(score)
    }

    fun getScoreByName(strName: String): LiveData<Score> {
        return scoreDao.getScoreByName(strName)
    }

}