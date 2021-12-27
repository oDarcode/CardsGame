package ru.dariamikhailukova.cardsgame.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.dariamikhailukova.cardsgame.HeroesFragmentDirections
import ru.dariamikhailukova.cardsgame.R
import ru.dariamikhailukova.cardsgame.model.Hero
import ru.dariamikhailukova.cardsgame.util.Constants.Companion.ZERO

class HeroAdapter: RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {

    private var heroList = emptyList<Hero>()

    inner class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var eachHero: ConstraintLayout? = null
        var heroName: TextView? = null
        var winRate: TextView? = null
        var averagePosition: TextView? = null
        var selectionFrequency: TextView? = null

        init {
            eachHero = itemView.findViewById(R.id.hero_row)
            heroName = itemView.findViewById(R.id.h_hero_name)
            winRate = itemView.findViewById(R.id.h_win_rate)
            averagePosition = itemView.findViewById(R.id.h_average_position)
            selectionFrequency = itemView.findViewById(R.id.h_selection_frequency)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder =
        HeroViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hero_row_layout, parent, false))

    override fun getItemCount(): Int {
        return heroList.size
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.heroName?.text = heroList[position].heroName
        holder.winRate?.text = heroList[position].winRate.toString()
        val averagePosition = heroList[position].averagePosition
        if (averagePosition != null) {
            holder.averagePosition?.text = averagePosition.toString()
        } else {
            holder.averagePosition?.text = ZERO
        }
        val selectionFrequency = heroList[position].selectionFrequency
        if (selectionFrequency != null) {
            holder.selectionFrequency?.text = selectionFrequency.toString()
        } else {
            holder.selectionFrequency?.text = ZERO
        }

        holder.eachHero?.setOnClickListener { view ->
            val action = HeroesFragmentDirections.actionHeroesFragmentToShowHeroFragment(heroList[position])
            view.findNavController().navigate(action)
        }
    }

    fun setData(newList: List<Hero>) {
        heroList = newList
        notifyDataSetChanged()
    }
}