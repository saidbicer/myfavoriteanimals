package com.said.myfavoriteanimals.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.said.myfavoriteanimals.R
import com.said.myfavoriteanimals.data.db.entity.Animal
import com.said.myfavoriteanimals.databinding.ItemAnimalBinding

class FavoriteAnimalsAdapter(private var animalList: ArrayList<Animal>) :
    RecyclerView.Adapter<FavoriteAnimalsAdapter.AnimalViewHolder>() {

    class AnimalViewHolder(var view: ItemAnimalBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemAnimalBinding>(
            inflater,
            R.layout.item_animal,
            parent,
            false
        )
        return AnimalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return animalList.size
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.view.animal = animalList[position]
    }

    fun updateAnimalList(newAnimalList : List<Animal>) {
        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()
    }
}
