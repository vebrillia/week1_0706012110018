package Adapter

import Interface.CardListener
import Model.Animal
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week1_0706012110018.R
import kotlinx.android.synthetic.main.activity_card.view.*


class ListDataRVAdapter(val listAnimal: ArrayList<Animal>, val cardListener: CardListener):
    RecyclerView.Adapter<ListDataRVAdapter.viewHolder>() {

        class viewHolder(itemView: View, val cardListener: CardListener):
                RecyclerView.ViewHolder(itemView) {
            val animal_name = itemView.name
            val animal_species = itemView.species
            val animal_age = itemView.age
            val animal_image = itemView.imageAnimal
            val delete = itemView.delete_btn
            val edit = itemView.cancel_button

            fun setData(data: Animal) {
                animal_name.text = data.name
                animal_species.text = data.species
                animal_age.text= data.age.toString()
                animal_image.setImageURI(Uri.parse(data.imageUri))
                if (data.imageUri.isNotEmpty()) {
                    animal_image.setImageURI(Uri.parse(data.imageUri))
                }
                itemView.setOnClickListener {
                    cardListener.onCardClick(adapterPosition)
                }

                edit.setOnClickListener {
                    cardListener.onEditClick(adapterPosition)
                }

                delete.setOnClickListener{
                    cardListener.onDeleteClick(adapterPosition)
                }


            }
        }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.activity_card, parent, false)
                return viewHolder(view, cardListener)

            }

            override fun onBindViewHolder(holder: viewHolder, position: Int){
                holder.setData(listAnimal[position])
            }

            override fun getItemCount(): Int{
                return listAnimal.size
            }




}