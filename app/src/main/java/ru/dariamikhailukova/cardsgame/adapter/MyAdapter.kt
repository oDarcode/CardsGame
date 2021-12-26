package ru.dariamikhailukova.cardsgame.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.dariamikhailukova.cardsgame.R
import ru.dariamikhailukova.cardsgame.model.ShortHero

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var myList = emptyList<ShortHero>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false))

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.card_id).text = myList[position].cardId.toString()
        holder.itemView.findViewById<TextView>(R.id.count_chosen).text = myList[position].countChosen.toString()
        holder.itemView.findViewById<TextView>(R.id.selection_frequency).text = myList[position].selectionFrequency.toString()
        holder.itemView.findViewById<TextView>(R.id.win_rate).text = myList[position].winRate.toString()
    }

    fun setData(newList: List<ShortHero>) {
        myList = newList
        notifyDataSetChanged()
    }

}