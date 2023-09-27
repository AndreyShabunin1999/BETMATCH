package com.googleduck.testapp.view

// Import the library in the code
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.googleduck.testapp.adapter.StatisticsAdapter
import com.googleduck.testapp.adapter.TableAdapter
import com.googleduck.testapp.databinding.FragmentTableBinding
import com.googleduck.testapp.model.DataCommand
import com.googleduck.testapp.model.NewsModel
import org.json.JSONObject

class TableFragment : Fragment() {

    lateinit var tableActivity: TablesActivity
    private lateinit var binding: FragmentTableBinding
    private var dataCommand = ArrayList<DataCommand>()
    private lateinit var adapter: TableAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTableBinding.inflate(inflater, container, false)

        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding.tvTable.setHasFixedSize(true)
        binding.tvTable.layoutManager = LinearLayoutManager(requireContext())

        tableActivity = (activity as TablesActivity?)!!

        binding.titleTableCommand.text = tableActivity.sharedPref.getString("COUNTRY", "")

        parseJSON(tableActivity.tables[tableActivity.sharedPref.getInt("INDEX", 0)].data.toString())

        return binding.root
    }

    fun parseJSON(jsonString: String) {

        val jsonObject = JSONObject(jsonString)

        var index: Int = 1

        while(true){
            try {
                val temp: String = jsonObject.getString(index.toString())
                val jsonObjectCommand = JSONObject(temp)
                val command = jsonObjectCommand.getString("Команда")
                val game = jsonObjectCommand.getInt("Игры")
                val v = jsonObjectCommand.getInt("В")
                val n = jsonObjectCommand.getInt("Н")
                val p = jsonObjectCommand.getInt("П")
                val balls = jsonObjectCommand.getString("Мячи")
                val points = jsonObjectCommand.getInt("Очки")
                val perPoints = jsonObjectCommand.getString("% очков")
                dataCommand.add(DataCommand(command,game,v,n,p,balls,points,perPoints))
            } catch (e: org.json.JSONException){
                println(dataCommand)
                adapter = TableAdapter(dataCommand)
                binding.tvTable.adapter = adapter
                break
            } finally {
                index++
            }
        }
    }
}