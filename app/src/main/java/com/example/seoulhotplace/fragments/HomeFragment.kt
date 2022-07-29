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
import com.example.seoulhotplace.databinding.FragmentHomeBinding
import com.example.seoulhotplace.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val TAG = HomeFragment::class.java.simpleName

    val bookIdList = mutableListOf<String>()
    val items = ArrayList<ContentModel>()
    val itemKeyList = ArrayList<String>()

    lateinit var rvAdapter: BookmarkRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("HomeFragment", "onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.tipTap.setOnClickListener {
            //Log.d("HomeFragment", "tipTap")
            it.findNavController().navigate(R.id.action_homeFragment_to_tipFragment)
        }

        binding.talkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_talkFragment)
        }

        binding.bookMarkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_bookFragment)
        }

        binding.storeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_storeFragment)
        }

        rvAdapter = BookmarkRVAdapter(requireContext(), items, itemKeyList, bookIdList)

        val rv: RecyclerView = binding.mainRV
        rv.adapter = rvAdapter

        rv.layoutManager = GridLayoutManager(requireContext(), 2) //화면에 2줄로 나오게

        getCategoryData()

        return binding.root
    }

    private fun getCategoryData() {

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (dataModel in dataSnapshot.children) {

                    val item = dataModel.getValue(ContentModel::class.java)

                    items.add(item!!)
                    itemKeyList.add(dataModel.key.toString())

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
}