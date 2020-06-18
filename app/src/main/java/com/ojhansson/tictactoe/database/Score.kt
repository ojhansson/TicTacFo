package com.ojhansson.tictactoe.database

import androidx.room.*

//Her settes name til unique, siden jeg også har replace on conflict i dao, vil samme navn bli merged
@Entity(tableName ="score_table", indices = arrayOf(Index(value = ["name"], unique = true)))

data class Score(

    @PrimaryKey(autoGenerate = true)
    var id : Long?,

    @ColumnInfo(name = "name")
    var name : String?,

    var scoreNumber : Int?

)

//(@PrimaryKey(autoGenerate = true)

