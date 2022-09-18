package com.example.week1_0706012110018

import Database.GlobalVar
import android.app.Activity
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_addanimal.*
import kotlinx.android.synthetic.main.activity_card.*

class CardActivity : AppCompatActivity() {
    private var position: Int = -1

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            val uri = it.data?.data
            imageView2.setImageURI(uri)
            GlobalVar.listDataAnimal[position].imageUri = uri.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        listener()
    }

    private fun listener(){
        toolbar2.getChildAt(1).setOnClickListener {
            finish()
        }

    }


}