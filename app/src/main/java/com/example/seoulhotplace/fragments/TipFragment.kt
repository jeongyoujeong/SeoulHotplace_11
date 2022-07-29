package com.example.seoulhotplace.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.seoulhotplace.R
import com.example.seoulhotplace.contentsList.*
import com.example.seoulhotplace.databinding.FragmentTipBinding

class TipFragment : Fragment() {

    private lateinit var binding: FragmentTipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tip, container, false)

        binding.button1.setOnClickListener {
            val intent = Intent(context, ContentsListActivity::class.java)
            intent.putExtra("button1", "button1")
            startActivity(intent)
        }

        binding.button2.setOnClickListener {
            val intent = Intent(context, ContentsListActivity2::class.java)
            intent.putExtra("button2", "button2")
            startActivity(intent)
        }

        binding.button3.setOnClickListener {
            val intent = Intent(context, ContentsListActivity3::class.java)
            intent.putExtra("button3", "button3")
            startActivity(intent)
        }

        binding.button4.setOnClickListener {
            val intent = Intent(context, ContentsListActivity4::class.java)
            intent.putExtra("button4", "button4")

            startActivity(intent)
        }

        binding.button5.setOnClickListener {
            val intent = Intent(context, ContentsListActivity5::class.java)
            intent.putExtra("button5", "button5")

            startActivity(intent)
        }

        binding.button6.setOnClickListener {
            val intent = Intent(context, ContentsListActivity6::class.java)
            intent.putExtra("button6", "button6")

            startActivity(intent)
        }

        binding.button7.setOnClickListener {
            val intent = Intent(context, ContentsListActivity7::class.java)
            intent.putExtra("button7", "button7")

            startActivity(intent)
        }

        binding.button8.setOnClickListener {
            val intent = Intent(context, ContentsListActivity8::class.java)
            intent.putExtra("button8", "button8")
            startActivity(intent)
        }

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_homeFragment)
        }

        binding.talkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_talkFragment)
        }

        binding.bookmarkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_bookFragment)
        }

        binding.storeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_storeFragment)
        }


        return binding.root
    }
}
