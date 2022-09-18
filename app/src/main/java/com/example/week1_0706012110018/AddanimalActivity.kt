package com.example.week1_0706012110018

import Database.GlobalVar
import Model.Animal
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_addanimal.*

class AddanimalActivity : AppCompatActivity() {
    private lateinit var animal: Animal
    var position = -1
    var image : String = ""

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            val uri = it.data?.data
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if(uri != null){
                    baseContext.getContentResolver().takePersistableUriPermission(uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }}
            imageView2.setImageURI(uri)
            image = uri.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addanimal)
        getintent()
        listener()
    }

    private fun getintent(){
        position = intent.getIntExtra("position",-1)
        if(position != -1){
            val animal = GlobalVar.listDataAnimal[position]
            toolbar2.title = "Edit Animal"
            delete_button.text = "Edit"
            imageView2.setImageURI(Uri.parse(GlobalVar.listDataAnimal[position].imageUri))
            input_name.editText?.setText(animal.name)
            input_species.editText?.setText(animal.species)
            input_age.editText?.setText(animal.age.toString())

        }
    }

    private fun listener(){
        imageView2.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        delete_button.setOnClickListener {
            var name = input_name.editText?.text.toString().trim()
            var species = input_species.editText?.text.toString().trim()
            var age = 0

            animal = Animal(name, species, age)
            checker()
        }


        toolbar2.getChildAt(1).setOnClickListener {
            finish()
        }
    }

    private fun checker(){
        var isCompleted : Boolean = true
        if(animal.name!!.isEmpty()){
            input_name.error = "Name cannot be empty"
            isCompleted = false
        }else{
            input_name.error= ""
        }

        if (animal.species!!.isEmpty()){
            input_species.error = "Species cannot be empty"
            isCompleted = false
        }else{
            input_species.error = ""
        }

        animal.imageUri = image.toString()

        if(input_age.editText?.text.toString().isEmpty() || input_age.editText?.text.toString().toInt()<0){
            input_age.error = "Age cannot be empty or 0"
            isCompleted = false
        }

        if (isCompleted == true){
            if (position == -1){
                animal.age = input_age.editText?.text.toString().toInt()
                GlobalVar.listDataAnimal.add(animal)
            }else{
                animal.age = input_age.editText?.text.toString().toInt()
                GlobalVar.listDataAnimal[position] = animal
            }
            finish()

        }
    }
}