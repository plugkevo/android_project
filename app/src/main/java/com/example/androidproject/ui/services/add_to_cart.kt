package com.example.androidproject.ui.services

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.goodsadapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
class add_to_cart : AppCompatActivity() {

    private lateinit var gdsRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var goodslist: ArrayList<ItemsViewModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_cart)


        gdsRecyclerView = findViewById(R.id.rvEmp)
        gdsRecyclerView.layoutManager = LinearLayoutManager(this)
        gdsRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        goodslist = arrayListOf<ItemsViewModel>()

        getGoodsData()
    }
    private fun getGoodsData() {

        gdsRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Goods Shipped")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                goodslist.clear()
                //getting data
                if (snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val goodsData = empSnap.getValue(ItemsViewModel::class.java)
                        goodslist.add(goodsData!!)
                    }
                    val mAdapter = goodsadapter(goodslist)
                    gdsRecyclerView.adapter = mAdapter

                    //when an item is clicked the all the employee data is displayed in a different page
                    mAdapter.setOnItemClickListener(object : goodsadapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@add_to_cart, view_goods::class.java)

                            //put extras
                            intent.putExtra("goodid", goodslist[position].goodid)
                            intent.putExtra("goodsname", goodslist[position].goodsname)
                            intent.putExtra("gdscharge", goodslist[position].gdscharge)
                            intent.putExtra("goodimage", goodslist[position].goodimage)
                            startActivity(intent)
                        }

                    })

                    gdsRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}


