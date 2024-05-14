package com.sherina.mammalsclassification

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class ListMammals : AppCompatActivity() {
    private lateinit var rvMammals: RecyclerView
    private var list: ArrayList<Mammals> = arrayListOf()
    private lateinit var listMammalsAdapter: ListMammalsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_mammals)
        rvMammals = findViewById(R.id.recyclerview)
        rvMammals.setHasFixedSize(true)
        list.addAll(MammalsData.listMammals)
        showRecyclerList()

        val btnAbout: ImageButton = findViewById(R.id.about)
        btnAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
            finish()
        }

        val btnScan: ImageButton = findViewById(R.id.scanMe)
        btnScan.setOnClickListener {
            startActivity(Intent(this, MammalsCamera::class.java))
        }

    }

    private fun showRecyclerList() {
        rvMammals.layoutManager = LinearLayoutManager(this)
        listMammalsAdapter = ListMammalsAdapter(list)
        rvMammals.adapter = listMammalsAdapter


        listMammalsAdapter.setOnItemClick(object : ListMammalsAdapter.OnItemClick {
            override fun onItemClicked(data: Mammals) {
                intentData(data)
            }
        })
    }

    private fun intentData(mammals: Mammals) {
        val intent = Intent(this, MammalsDetails::class.java)
        intent.putExtra("name", mammals.mammalsName)
        intent.putExtra("photo", mammals.mammalsPhoto)
        intent.putExtra("information", mammals.mammalsInformation)
        startActivity(intent)
    }

    private fun intentAbout() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }


}
