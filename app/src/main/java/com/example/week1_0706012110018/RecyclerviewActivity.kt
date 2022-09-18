package com.example.week1_0706012110018

import Adapter.ListDataRVAdapter
import Database.GlobalVar
import Interface.CardListener
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_card.*
import kotlinx.android.synthetic.main.activity_recyclerview.*
import java.util.jar.Manifest

class RecyclerviewActivity : AppCompatActivity(), CardListener {

    private val adapter = ListDataRVAdapter(GlobalVar.listDataAnimal,this)
    private var jumlah : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)
        supportActionBar?.hide()
        permission()
        setUpRecyclerView()
        listener()
    }

    override fun onResume() {
        super.onResume()
        jumlah = GlobalVar.listDataAnimal.size
        if (jumlah == 0){
            addanimal_txt.alpha = 1f
        }else{
            addanimal_txt.alpha = 0f
        }
        adapter.notifyDataSetChanged()
    }

    private fun permission(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), GlobalVar.STORAGE_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), GlobalVar.STORAGE_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == GlobalVar.STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun listener(){
        addanimal_FAB.setOnClickListener{
            val myIntent = Intent(this, AddanimalActivity::class.java)
            startActivity(myIntent)
        }


    }


    private fun setUpRecyclerView(){
        val layoutManager = GridLayoutManager(this,1)
        listData_RV.layoutManager = layoutManager
        listData_RV.adapter = adapter
    }

    override fun onCardClick(position: Int) {
//
    }

    override fun onDeleteClick(position: Int) {
        val myIntent = Intent(this, DeleteActivity::class.java).apply {
            putExtra("position", position)
        }
        startActivity(myIntent)
//
    }

    override fun onEditClick(position: Int) {
        val myIntent = Intent(this, AddanimalActivity::class.java).apply {
            putExtra("position", position)
        }
        startActivity(myIntent)
    }



}