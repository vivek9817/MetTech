package com.example.mittechapp.View

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.androidplot.xy.*
import com.example.mittechapp.CommonUtils.MitApplication
import com.example.mittechapp.CommonUtils.Resource
import com.example.mittechapp.Network.AppRepository
import com.example.mittechapp.ViewModel.CommonViewModel
import com.example.mittechapp.ViewModel.ViewModelProvide
import com.example.mittechapp.databinding.ActivityMainBinding
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var commonviewmodel: CommonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeData()
        SendRequestToServer()
        plotGraph()
    }

    private fun plotGraph() {

        commonviewmodel.getresponse.observe(this, androidx.lifecycle.Observer {
            it.getContentIfNotHandled()?.let {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {

                        val domainLabel =
                            it.data!!.data!!.filter { v -> v.Year!!.toInt() == v.Year!!.toInt() }
                        val series = arrayOf<Number>(1, 2, 3, 4, 5, 6, 8, 6, 6, 9, 5)

                        val series1: XYSeries = SimpleXYSeries(
                            Arrays.asList(* series),
                            SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
                            "Series1"
                        )

                        val series1format =
                            LineAndPointFormatter(Color.BLUE, Color.BLACK, null, null)

                        series1format.setInterpolationParams(
                            CatmullRomInterpolator.Params(
                                10,
                                CatmullRomInterpolator.Type.Centripetal
                            )
                        )

                        binding.plotData.addSeries(series1, series1format)

                        binding.plotData.graph.getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).format =
                            object : Format() {
                                override fun format(
                                    p0: Any?,
                                    p1: StringBuffer?,
                                    p2: FieldPosition?
                                ): StringBuffer {
                                    var i = Math.round((p0 as Number).toFloat())
                                    return p1!!.append(domainLabel[i])
                                }

                                override fun parseObject(p0: String?, p1: ParsePosition?): Any? {
                                    return null
                                }

                            }
//                        PanZoom.attach(binding.plotData)
                    }
                    is Resource.Error -> {}
                }
            }
        })
    }

    private fun SendRequestToServer() {
        commonviewmodel.setRequestToTheServer()
        binding.btnNext.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initializeData() {
        val repository = AppRepository()
        val factory = ViewModelProvide(application as MitApplication, repository, this)
        commonviewmodel = ViewModelProvider(this, factory).get(CommonViewModel::class.java)
    }
}