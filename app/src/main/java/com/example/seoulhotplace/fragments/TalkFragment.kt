package com.example.seoulhotplace.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.seoulhotplace.R
import com.example.seoulhotplace.board.BoardInsideActivity
import com.example.seoulhotplace.board.BoardListViewAdapter
import com.example.seoulhotplace.board.BoardModel
import com.example.seoulhotplace.board.BoardWriteActivity
import com.example.seoulhotplace.databinding.FragmentTalkBinding
import com.example.seoulhotplace.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class TalkFragment : Fragment() {
    private lateinit var binding: FragmentTalkBinding

    private val boardDataList = mutableListOf<BoardModel>()
    private val boardKeyList =  mutableListOf<String>()
    private val TAG = TalkFragment::class.java.simpleName
    private lateinit var boardRVAdapter : BoardListViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_talk_fragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_talk, container, false)

        boardRVAdapter = BoardListViewAdapter(boardDataList)
        binding.boardListView.adapter = boardRVAdapter

        binding.boardListView.setOnItemClickListener { parent, view, position, id ->
//            val intent = Intent(context, BoardInsideActivity::class.java)
//            intent.putExtra("title", boardDataList[position].title)
//            intent.putExtra("content", boardDataList[position].content)
//            intent.putExtra("time", boardDataList[position].time)
//            startActivity(intent)

            val intent = Intent(context, BoardInsideActivity::class.java)
            intent.putExtra("key",boardKeyList[position])
            startActivity(intent)

        }
//        binding.writeBtn.setOnClickListener {
//            val intent = Intent(context, BoardInsideActivity::class.java)
//            //intent.putExtra("key",boardKeyList[position])
//            startActivity(intent)
//
//        }

        binding.writeBtn.setOnClickListener {
            val intent = Intent(context, BoardWriteActivity::class.java)
            intent.putExtra("writeBtn","writeBtn")
            startActivity(intent)
        }

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_homeFragment)
        }

        binding.tipTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_tipFragment)
        }

        binding.bookMarkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_bookFragment)
        }

        binding.storeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_storeFragment)
        }
        //위에는 연동문제인듯

        getFBBoardData()
        return binding.root
    }
    private fun getFBBoardData(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot){

                boardDataList.clear()
                for (dataModel in dataSnapshot.children){

                    Log.d(TAG, dataModel.toString())
                    dataModel.key

                    val item = dataModel.getValue(BoardModel::class.java)
                    boardDataList.add(item!!)
                    boardKeyList.add(dataModel.key.toString())
                }
                boardKeyList.reverse()
                boardDataList.reverse()
                boardRVAdapter.notifyDataSetChanged()

                Log.d(TAG, boardDataList.toString())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }

        }
        FBRef.boardRef.addValueEventListener(postListener)

    }
}