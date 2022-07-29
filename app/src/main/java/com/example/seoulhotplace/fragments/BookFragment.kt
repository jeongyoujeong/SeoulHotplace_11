package com.example.seoulhotplace.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seoulhotplace.R
import com.example.seoulhotplace.contentsList.BookmarkRVAdapter
import com.example.seoulhotplace.contentsList.ContentModel
import com.example.seoulhotplace.databinding.FragmentBookBinding
import com.example.seoulhotplace.utils.FBAuth
import com.example.seoulhotplace.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class BookFragment : Fragment() {

    private lateinit var binding : FragmentBookBinding

    private val TAG = BookFragment::class.java.simpleName

    val bookIdList = mutableListOf<String>()
    val items = ArrayList<ContentModel>()
    val itemKeyList = ArrayList<String>()

    lateinit var rvAdapter : BookmarkRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book, container, false)


        // 2. 사용자가 북마크한 정보를 다 가져옴!
        getBookmarkData()


        rvAdapter = BookmarkRVAdapter(requireContext(), items, itemKeyList, bookIdList)

        val rv : RecyclerView = binding.bookmarkRV
        rv.adapter = rvAdapter

        rv.layoutManager = GridLayoutManager(requireContext(), 2) //화면에 2줄로 나오게

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookFragment_to_homeFragment)
        }

        binding.tipTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookFragment_to_tipFragment)
        }

        binding.talkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookFragment_to_talkFragment)
        }

        binding.storeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookFragment_to_storeFragment)
        }

        return binding.root
    }

    private fun getCategoryData(){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (dataModel in dataSnapshot.children) {

                    //items는 이 밑에 코드에서 받아오는거
                    Log.d(TAG, dataModel.toString())
                    val item = dataModel.getValue(ContentModel::class.java)

                    // 3. 전체 컨텐츠 중에서, 사용자가 북마크한 정보만 보여줌!
                    if (bookIdList.contains(dataModel.key.toString())){
                        items.add(item!!)
                        itemKeyList.add(dataModel.key.toString())
                    }


                }
                rvAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }

        FBRef.button1.addValueEventListener(postListener)
        FBRef.button2.addValueEventListener(postListener)
        FBRef.button3.addValueEventListener(postListener)
        FBRef.button4.addValueEventListener(postListener)
        FBRef.button5.addValueEventListener(postListener)
        FBRef.button6.addValueEventListener(postListener)
        FBRef.button7.addValueEventListener(postListener)
        FBRef.button8.addValueEventListener(postListener)



    }

    private fun getBookmarkData(){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (dataModel in dataSnapshot.children) {

                    Log.e(TAG, dataModel.toString())
                    bookIdList.add(dataModel.key.toString())

                }

                // 1. 전체 카테고리에 있는 컨텐츠 데이터들을 다 가져옴!
                getCategoryData()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)

    }



}