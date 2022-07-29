//
//package com.example.seoulhotplace.contentsList
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.Toast
//import androidx.recyclerview.widget.GridLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.seoulhotplace.R
//import com.example.seoulhotplace.utils.FBAuth
//import com.example.seoulhotplace.utils.FBRef
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.ValueEventListener
//import com.google.firebase.database.ktx.database
//import com.google.firebase.ktx.Firebase
//
//class ContentsListActivity : AppCompatActivity() {
//
//    lateinit var myRef : DatabaseReference
//
//    val bookmarkIdList = mutableListOf<String>() // 북마크 데이터 id 값을 담을 리스트 생성
//
//    lateinit var rvAdapter: ContentRVAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//        super.onCreate(savedInstanceState)
//        //setContentView(R.layout.activity_contents_list)
//        setContentView(R.layout.activity_content_rvadapter)
//
//        val rv : RecyclerView = findViewById(R.id.rv)
//
//        rv.adapter = rvAdapter
//
//        rv.layoutManager = GridLayoutManager(this, 2)
//
//        val items = ArrayList<ContentModel>()
//        val itemKeyList = ArrayList<String>()
//
//        val button1 = intent.getStringExtra("button1")
//        rvAdapter = ContentRVAdapter(baseContext, items, itemKeyList, bookmarkIdList)
//
//        // Write a message to the database
//        val database = Firebase.database
//        val category = intent.getStringExtra("contents")
//
//
//
//            myRef = database.getReference("button1")
//
//        //content들 받아오는 부분
//        val postListener = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                for (dataModel in dataSnapshot.children) {
//                    Log.d("ContentsListActivity", dataModel.toString())
//                    Log.d("ContentsListActivity", dataModel.key.toString())
//                    val item = dataModel.getValue(ContentModel::class.java)
//                    items.add(item!!)
//                    //아이템 키 리스트 안에 키 값 들어감 이제 키 값을 adapter로 넘겨줘야 함
//                    itemKeyList.add(dataModel.key.toString())
//
//                }
//                rvAdapter.notifyDataSetChanged()
//                Log.d("ContentsListActivity", items.toString())
//
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Post failed, log a message
//                Log.w("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
//            }
//        }
//        myRef.addValueEventListener(postListener)
//
//
//
//        getBookmarkData()
//    }
//
//
//    private fun getBookmarkData(){
//
//        val postListener = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                bookmarkIdList.clear()
//
//                for (dataModel in dataSnapshot.children) {
//                    bookmarkIdList.add(dataModel.key.toString())
//                }
//                Log.d("Bookmark : ", bookmarkIdList.toString())
//                rvAdapter.notifyDataSetChanged()
//
//
//
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Post failed, log a message
//                Log.w("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
//            }
//        }
//        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)
//
//
//
//    }
//
//
//
//
//}

package com.example.seoulhotplace.contentsList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seoulhotplace.R
import com.example.seoulhotplace.utils.FBAuth
import com.example.seoulhotplace.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ContentsListActivity : AppCompatActivity() {

    lateinit var myRef : DatabaseReference

    val bookmarkIdList = mutableListOf<String>() // 북마크 데이터 id 값을 담을 리스트 생성

    lateinit var rvAdapter: ContentRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents_list)

        val items = ArrayList<ContentModel>()
        val itemKeyList = ArrayList<String>()

        val button1 = intent.getStringExtra("button1")
        rvAdapter = ContentRVAdapter(baseContext, items, itemKeyList, bookmarkIdList)

        // Write a message to the database
        val database = Firebase.database
        val category = intent.getStringExtra("contents")


        myRef = database.getReference("button1")

        //content들 받아오는 부분
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (dataModel in dataSnapshot.children) {
                    Log.d("ContentsListActivity", dataModel.toString())
                    Log.d("ContentsListActivity", dataModel.key.toString())
                    val item = dataModel.getValue(ContentModel::class.java)
                    items.add(item!!)
                    //아이템 키 리스트 안에 키 값 들어감 이제 키 값을 adapter로 넘겨줘야 함
                    itemKeyList.add(dataModel.key.toString())

                }
                rvAdapter.notifyDataSetChanged()
                Log.d("ContentsListActivity", items.toString())

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)

        val rv : RecyclerView = findViewById(R.id.rv)

        rv.adapter = rvAdapter

        rv.layoutManager = GridLayoutManager(this, 2)

        getBookmarkData()
    }


    private fun getBookmarkData(){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                bookmarkIdList.clear()

                for (dataModel in dataSnapshot.children) {
                    bookmarkIdList.add(dataModel.key.toString())
                }
                Log.d("Bookmark : ", bookmarkIdList.toString())
                rvAdapter.notifyDataSetChanged()



            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)
    }
}

