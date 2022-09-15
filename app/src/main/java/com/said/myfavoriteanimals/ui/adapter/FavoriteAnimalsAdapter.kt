package com.said.myfavoriteanimals.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.said.myfavoriteanimals.R
import com.said.myfavoriteanimals.data.db.entity.Animal
import com.said.myfavoriteanimals.databinding.ItemAnimalBinding
import com.said.myfavoriteanimals.ui.view.FavoriteAnimalsFragmentDirections

class FavoriteAnimalsAdapter(private var animalList: ArrayList<Animal>) :
    RecyclerView.Adapter<FavoriteAnimalsAdapter.AnimalViewHolder>(), AnimalClickListener {

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
        holder.view.listener = this
//        holder.view.llRow.setOnClickListener {
//            val url = animalList[position].imgUrl
//            val action = FavoriteAnimalsFragmentDirections.actionAnimalListFragmentToShowAnimalFragment(url)
//            it.findNavController().navigate(action)
//        }
    }

    fun updateAnimalList(newAnimalList: List<Animal>) {
        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()
    }

    override fun onAnimalClicked(v: View) {
        val imgUrl = v.tag as Animal
        val action = FavoriteAnimalsFragmentDirections.actionAnimalListFragmentToShowAnimalFragment(
            "",
            imgUrl
        )
        v.findNavController().navigate(action)
    }
}
