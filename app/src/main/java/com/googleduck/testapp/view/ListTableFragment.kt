package com.googleduck.testapp.view

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.googleduck.testapp.adapter.TableBtnAdapter
import com.googleduck.testapp.databinding.FragmentListTableBinding

class ListTableFragment : Fragment() {

    private lateinit var binding: FragmentListTableBinding
    private lateinit var adapter: TableBtnAdapter
    lateinit var tableActivity: TablesActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListTableBinding.inflate(inflater, container, false)

        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        tableActivity = (activity as TablesActivity?)!!

        println(tableActivity?.tables?.get(0))

        binding.rvTables.setHasFixedSize(true)
        binding.rvTables.layoutManager = LinearLayoutManager(requireContext())
        adapter = tableActivity?.tables?.let { TableBtnAdapter(this@ListTableFragment, it) }!!
        binding.rvTables.adapter = adapter

        return binding.root
    }
}