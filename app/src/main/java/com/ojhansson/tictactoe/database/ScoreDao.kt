package com.ojhansson.tictactoe.database



import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ScoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(score : Score)

    @Query("SELECT * FROM score_table ORDER BY scoreNumber DESC")
    fun getAllScores() : LiveData<List<Score>>

    @Query("DELETE FROM score_table")
    fun deleteAll()

    @Query("SELECT * FROM score_table WHERE name = :strName")
    fun getScoreByName(strName: String): LiveData<Score>
//
//    @Query("UPDATE score_table SET scoreNumber = scoreNumber+1 WHERE name= :name")
//        fun updateScore(scoreNumber: Number, name: String)
}