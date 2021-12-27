package ru.dariamikhailukova.cardsgame.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.dariamikhailukova.cardsgame.*
import ru.dariamikhailukova.cardsgame.model.Card
import ru.dariamikhailukova.cardsgame.util.Constants.Companion.ZERO


class CardsAdapter: RecyclerView.Adapter<CardsAdapter.CardsViewHolder>() {

    private var cardList = emptyList<Card>()

    inner class CardsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var eachCard: ConstraintLayout? = null
        var cardName: TextView? = null
        var winRate: TextView? = null
        var averagePosition: TextView? = null

        init {
            eachCard = itemView.findViewById(R.id.card_row)
            cardName = itemView.findViewById(R.id.c_card_name)
            winRate = itemView.findViewById(R.id.c_win_rate)
            averagePosition = itemView.findViewById(R.id.c_average_position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder =
        CardsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_row_layout, parent, false))

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.cardName?.text = cardList[position].cardName
        holder.winRate?.text = cardList[position].winRate.toString()
        val averagePosition = cardList[position].averagePosition
        if (averagePosition != null) {
            holder.averagePosition?.text = averagePosition.toString()
        } else {
            holder.averagePosition?.text = ZERO
        }

        holder.eachCard?.setOnClickListener { view ->
            val action = CreaturesFragmentDirections.actionCreaturesFragmentToShowCardFragment(cardList[position])
            view.findNavController().navigate(action)
        }
    }

    fun setData(newList: List<Card>) {
        cardList = newList
        notifyDataSetChanged()
    }
}