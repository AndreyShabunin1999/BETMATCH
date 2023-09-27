package com.googleduck.testapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.googleduck.testapp.R
import com.googleduck.testapp.model.TableModel
import com.googleduck.testapp.view.ListTableFragment
import com.googleduck.testapp.view.TableFragment

class TableBtnAdapter(private var fragment: ListTableFragment, private var tables: ArrayList<TableModel>): RecyclerView.Adapter<TableBtnAdapter.TableViewHolder>() {

    inner class TableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnTable: Button = itemView.findViewById(R.id.table_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_btn_table , parent , false)
        return TableViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tables.size
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.btnTable.text = tables[position].name

        holder.btnTable.setOnClickListener {
            fragment.tableActivity.goToFragment(TableFragment())
            fragment.tableActivity.sharedPref.edit().putString("COUNTRY", holder.btnTable.text.toString()).apply()
            fragment.tableActivity.sharedPref.edit().putInt("INDEX", position).apply()
        }
    }
}