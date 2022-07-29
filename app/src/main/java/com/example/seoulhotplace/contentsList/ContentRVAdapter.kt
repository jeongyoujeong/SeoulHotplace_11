//package com.example.seoulhotplace.contentsList
//
//import android.content.Context
//import android.content.Intent
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.seoulhotplace.R
//import com.example.seoulhotplace.utils.FBAuth
//import com.example.seoulhotplace.utils.FBRef
//
//class ContentRVAdapter(val context : Context,
//                       val items: ArrayList<ContentModel>,
//                       val itemkeyList: ArrayList<String>,
//                       val bookmarkIdList : MutableList<String>)
//    : RecyclerView.Adapter<ContentRVAdapter.Viewholder>() {
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRVAdapter.Viewholder {
//        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_content_rvadapter, parent, false)
//        return Viewholder(v)
//    }
//
//    override fun onBindViewHolder(holder: ContentRVAdapter.Viewholder, position: Int) {
//        holder.bindItems(items[position], itemkeyList[position])
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//    inner class Viewholder(itemView : View) : RecyclerView.ViewHolder(itemView){
//        fun bindItems(item : ContentModel, key : String){
//
//            itemView.setOnClickListener {
//                Toast.makeText(context, item.title, Toast.LENGTH_LONG).show()
//                val intent = Intent(context, ContentShowActivity::class.java)
//                intent.putExtra("url", item.webUrl)
//                //url로 이동 (url값이 item에 있는 weburl)
//                itemView.context.startActivity(intent)
//            }
//
//            val contentTitle = itemView.findViewById<TextView>(R.id.textArea)
//            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)
//            val bookmarkArea = itemView.findViewById<ImageView>(R.id.bookmarkArea)
//
//            if(bookmarkIdList.contains(key)) {
//                bookmarkArea.setImageResource(R.color.bookmark_color)
//            } else {
//                bookmarkArea.setImageResource(R.color.bookmark_white)
//            }
//
//            //북마크 UI 색칠 & 삭제
//           bookmarkArea.setOnClickListener {
//                Log.d("ContentRVAdapter", FBAuth.getUid())
//                Toast.makeText(context, key, Toast.LENGTH_LONG).show()
//
//                if(bookmarkIdList.contains(key)) {
//                    // 북마크가 있을 때 삭제
//                    FBRef.bookmarkRef
//                        .child(FBAuth.getUid())
//                        .child(key)
//                        .removeValue()
//
//                } else {
//                    // 북마크가 없을 때
//                    FBRef.bookmarkRef
//                        .child(FBAuth.getUid())
//                        .child(key)
//                        .setValue(BookmarkModel(true))
//
//                }
//
//            }
//
//
//            contentTitle.text = item.title
//
//            Glide.with(context)
//                .load(item.imageUrl)
//                .into(imageViewArea)
//
//        }
//    }
//}

package com.example.seoulhotplace.contentsList

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seoulhotplace.R
import com.example.seoulhotplace.utils.FBAuth
import com.example.seoulhotplace.utils.FBRef

class ContentRVAdapter(val context : Context,
                       val items: ArrayList<ContentModel>,
                       val keyList: ArrayList<String>,
                       val bookmarkIdList : MutableList<String>)
    : RecyclerView.Adapter<ContentRVAdapter.Viewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRVAdapter.Viewholder {
        //val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_content_rvadapter, parent, false)
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)

        Log.d("ContentRVAdapter", keyList.toString())
        Log.d("ContentRVAdapter", bookmarkIdList.toString())
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: ContentRVAdapter.Viewholder, position: Int) {
        //아이템별로 가져오기
        holder.bindItems(items[position], keyList[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Viewholder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindItems(item : ContentModel, key : String){

            itemView.setOnClickListener {
                Toast.makeText(context, item.title, Toast.LENGTH_LONG).show()
                val intent = Intent(context, ContentShowActivity::class.java)
                intent.putExtra("url", item.webUrl)
                //url로 이동 (url값이 item에 있는 weburl)
                itemView.context.startActivity(intent)
            }

            val contentTitle = itemView.findViewById<TextView>(R.id.textArea)
            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)
            //북마크가 클릭되었을 때
            val bookmarkArea = itemView.findViewById<ImageView>(R.id.bookmarkArea)

            if(bookmarkIdList.contains(key)) {
                bookmarkArea.setImageResource(R.drawable.bookmark_color)
            } else {
                bookmarkArea.setImageResource(R.drawable.bookmark_white)
            }

            //북마크 UI 색칠 & 삭제
            bookmarkArea.setOnClickListener {
                Log.d("ContentRVAdapter", FBAuth.getUid())
                Toast.makeText(context, key, Toast.LENGTH_LONG).show()

                if(bookmarkIdList.contains(key)) {

                    FBRef.bookmarkRef
                        .child(FBAuth.getUid())
                        .child(key)
                        .removeValue()

                } else {
                    // 북마크가 없을 때
                    FBRef.bookmarkRef
                        .child(FBAuth.getUid())
                        .child(key)
                        .setValue(BookmarkModel(true))

                }

            }


            contentTitle.text = item.title

            Glide.with(context)
                .load(item.imageUrl)
                .into(imageViewArea)

        }
    }
}