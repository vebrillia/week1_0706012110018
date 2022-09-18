package com.example.week1_0706012110018

import Database.GlobalVar
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import kotlinx.android.synthetic.main.activity_addanimal.*
import kotlinx.android.synthetic.main.activity_card.*
import kotlinx.android.synthetic.main.activity_card.cancel_button
import kotlinx.android.synthetic.main.activity_delete.*
import kotlinx.android.synthetic.main.activity_delete.delete_button
import kotlinx.android.synthetic.main.activity_recyclerview.*

class DeleteActivity : AppCompatActivity() {
    var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)
        listener()

    }

    private fun listener(){
        cancel_button.setOnClickListener {
            finish()
        }

        delete_button.setOnClickListener {
            position = intent.getIntExtra("position", -1)
            if (position != -1){
                GlobalVar.listDataAnimal.removeAt(position)
            }
            finish()
        }
    }


}