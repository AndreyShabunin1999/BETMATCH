package com.googleduck.testapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.googleduck.testapp.R
import com.googleduck.testapp.model.StatisticsAttackModel
import com.googleduck.testapp.model.StatisticsDefenseModel

class StatisticsAdapter(private var mode: Int, private var statDefense: ArrayList<StatisticsDefenseModel>,
                        private var statAttack: ArrayList<StatisticsAttackModel>): RecyclerView.Adapter<StatisticsAdapter.StatisticsViewHolder>() {

    fun setMode(changeMode: Int){
        mode = changeMode
    }

    inner class StatisticsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCommands: TextView = itemView.findViewById(R.id.tv_commands)
        val tvBlows: TextView = itemView.findViewById(R.id.tv_blows)
        val tvFouls: TextView = itemView.findViewById(R.id.tv_fouls)
        val tvOffsides: TextView = itemView.findViewById(R.id.tv_offsides)
        val tvInterceptions: TextView = itemView.findViewById(R.id.tv_interceptions)
        val tvRating: TextView = itemView.findViewById(R.id.tv_rating)
        val tvSelections: TextView = itemView.findViewById(R.id.tv_selections)
        val tvTournament: TextView = itemView.findViewById(R.id.tv_tournament)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stat , parent , false)
        return StatisticsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(mode == 0){
            statDefense.size
        } else {
            statAttack.size
        }
    }

    override fun onBindViewHolder(holder: StatisticsViewHolder, position: Int) {
        if(mode == 0){
            holder.tvOffsides.visibility = View.VISIBLE
            holder.tvCommands.text = statDefense[position].comand
            holder.tvTournament.text = statDefense[position].tournament
            holder.tvBlows.text = statDefense[position].blows.toString()
            holder.tvSelections.text = statDefense[position].selections.toString()
            holder.tvInterceptions.text = statDefense[position].interceptions.toString()
            holder.tvFouls.text = statDefense[position].fouls.toString()
            holder.tvRating.text = statDefense[position].rating.toString()
            holder.tvOffsides.text = statDefense[position].offsides.toString()
        } else {
            holder.tvCommands.text = statAttack[position].commands
            holder.tvTournament.text = statAttack[position].tournament
            holder.tvBlows.text = statAttack[position].blows.toString()
            holder.tvSelections.text = statAttack[position].blowsVStv.toString()
            holder.tvInterceptions.text = statAttack[position].dribbling.toString()
            holder.tvFouls.text = statAttack[position].fouls.toString()
            holder.tvRating.text = statAttack[position].rating.toString()
            holder.tvOffsides.visibility = View.INVISIBLE
        }
    }
}