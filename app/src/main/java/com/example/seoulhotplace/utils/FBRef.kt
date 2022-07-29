package com.example.seoulhotplace.utils


import com.example.seoulhotplace.contentsList.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FBRef {

    companion object {

        private val database = Firebase.database

        //val category1 = database.getReference("contents")
        val button1 = database.getReference("button1")
        val button2 = database.getReference("button2")
        val button3 = database.getReference("button3")
        val button4 = database.getReference("button4")
        val button5 = database.getReference("button5")
        val button6 = database.getReference("button6")
        val button7 = database.getReference("button7")
        val button8 = database.getReference("button8")

        //val category1 = database.getReference("contents")
        //val ContentsListActivity = database.getReference("contents")


        val bookmarkRef = database.getReference("bookmark_list")

        val boardRef = database.getReference("board")

        val commentRef = database.getReference("comment")


    }

}