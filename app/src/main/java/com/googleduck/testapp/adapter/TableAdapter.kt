package com.googleduck.testapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.googleduck.testapp.R
import com.googleduck.testapp.model.DataCommand


class TableAdapter(private var tableCommand: ArrayList<DataCommand>): RecyclerView.Adapter<TableAdapter.TableViewHolder>() {

    inner class TableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCommand: TextView = itemView.findViewById(R.id.tv_command_table)
        val tvGame: TextView = itemView.findViewById(R.id.tv_game)
        val tvV: TextView = itemView.findViewById(R.id.tv_v)
        val tvN: TextView = itemView.findViewById(R.id.tv_n)
        val tvP: TextView = itemView.findViewById(R.id.tv_p)
        val tvBalls: TextView = itemView.findViewById(R.id.tv_balls)
        val tvPoints: TextView = itemView.findViewById(R.id.tv_points)
        val tvPointsPercentage: TextView = itemView.findViewById(R.id.tv_points_percentage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_table_command , parent , false)
        return TableViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tableCommand.size
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.tvCommand.text = tableCommand[position].command
        holder.tvGame.text = tableCommand[position].game.toString()
        holder.tvV.text = tableCommand[position].v.toString()
        holder.tvN.text = tableCommand[position].n.toString()
        holder.tvP.text = tableCommand[position].p.toString()
        holder.tvBalls.text = tableCommand[position].balls
        holder.tvPoints.text = tableCommand[position].points.toString()
        holder.tvPointsPercentage.text = tableCommand[position].pointsPercentage.toString()
    }
}





