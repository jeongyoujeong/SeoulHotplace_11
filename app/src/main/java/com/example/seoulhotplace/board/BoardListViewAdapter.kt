package com.example.seoulhotplace.board

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.seoulhotplace.R
import com.example.seoulhotplace.utils.FBAuth

class BoardListViewAdapter(val boardList : MutableList<BoardModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return boardList.size
    }

    override fun getItem(position: Int): Any {
        return boardList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        view = LayoutInflater.from(parent?.context).inflate(R.layout.board_list_item, parent,false)


        val itemLinearLayoutView = view?.findViewById<LinearLayout>(R.id.itemview)
        val title = view?.findViewById<TextView>(R.id.titleArea)
        val content = view?.findViewById<TextView>(R.id.contentArea)
        val time = view?.findViewById<TextView>(R.id.timeArea)
        //Toast.makeText(this, "aa", Toast.LENGTH_LONG).show()
        if(boardList[position].uid.equals(FBAuth.getUid())){
                itemLinearLayoutView?.setBackgroundColor(Color.parseColor("#FFD9E4"))
        }


        title!!.text = boardList[position].title
        content!!.text = boardList[position].content
        time!!.text = boardList[position].time


        return view!!
    }
}