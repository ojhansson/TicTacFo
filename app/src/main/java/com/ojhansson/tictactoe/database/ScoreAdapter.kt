package com.ojhansson.tictactoe.database

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ojhansson.tictactoe.R

class ScoreAdapter internal constructor(context: Context) : RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var scores: List<Score>? = null

    class ScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val scoreItemName = itemView.findViewById(R.id.scoreItemName) as TextView
        val scoreItemScore = itemView.findViewById(R.id.scoreItemScore) as TextView
    }

    override fun getItemCount(): Int {
        return if (this.scores != null) this.scores!!.size else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return ScoreViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
//        val current = scores!![position]
        when {
            scores != null -> {
                val current = scores!![position]
                holder.scoreItemName.text = current.name
                holder.scoreItemScore.text = current.scoreNumber.toString()
            }
            else -> {
                holder.scoreItemName.text = "It's empty here!"
                holder.scoreItemScore.text = ""
            }
        }
    }

    fun setScore(newScore: List<Score>) {
        this.scores = newScore
        notifyDataSetChanged()
    }
}