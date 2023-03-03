package com.example.mittechapp.View.fragment

import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mittechapp.CommonUtils.CommonUtils
import com.example.mittechapp.Model.Items
import com.example.mittechapp.R
import com.example.mittechapp.adapter.CommonAlertAdapter
import com.example.mittechapp.databinding.FragmentCheckingBinding
import com.example.mittechapp.viewholder.GenericViewHolder
import okhttp3.internal.notifyAll

class FragmentChecking : Fragment(R.layout.fragment_checking) {
    lateinit var binding: FragmentCheckingBinding
    lateinit var recyclerView: RecyclerView

    var Arraydata: ArrayList<Items> = ArrayList()

    var CheckedArrayData: ArrayList<Items> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckingBinding.inflate(layoutInflater)
        var view = binding.root
        recyclerView =
            CommonUtils.initializeRecyclerView(
                binding.recyclerItems,
                requireContext()
            )

        onClickOnUi()
        setArray()
        checkedOnUi()

        return view
    }

    private fun setArray() {
        Arraydata.add(
            Items(
                "Sanatizer"
            )
        )

        Arraydata.add(
            Items(
                "Dummy"
            )
        )

        Arraydata.add(
            Items(
                "Tomato"
            )
        )

        Arraydata.add(
            Items(
                "Mango"
            )
        )

        Arraydata.add(
            Items(
                "Table"
            )
        )

        Arraydata.add(
            Items(
                "Tomato"
            )
        )

        Arraydata.add(
            Items(
                "Mango"
            )
        )

        Arraydata.add(
            Items(
                "Table"
            )
        )

        Arraydata.add(
            Items(
                "Tomato"
            )
        )

        Arraydata.add(
            Items(
                "Mango"
            )
        )

        Arraydata.add(
            Items(
                "Table"
            )
        )

        Arraydata.add(
            Items(
                "Tomato"
            )
        )

        Arraydata.add(
            Items(
                "Mango"
            )
        )

        Arraydata.add(
            Items(
                "Table"
            )
        )
    }

    private fun checkedOnUi() {
        binding.CheckAll.setOnCheckedChangeListener { _, b ->
            if (b) {
                Arraydata.forEach {
                    it.IsChecked = true
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
            }
        }
    }

    private fun onClickOnUi() {
        recyclerView.adapter = object : CommonAlertAdapter<Any>(
            R.layout.layout_common_checking,
            Arraydata as ArrayList<Any>
        ) {
            override fun bindData(holder: GenericViewHolder<Any>, item: Any) {
                when (item) {
                    CommonUtils.checkTypeCast<Items>(item) -> {
                        CommonUtils.checkTypeCast<Items>(item)!!.apply {

                            holder?.itemView?.findViewById<CheckBox>(R.id.CheckId)!!.isChecked =
                                this.IsChecked

                            holder?.itemView?.findViewById<CheckBox>(R.id.CheckId).let {
                                it!!.text = this.CheckId
                                it!!.setOnCheckedChangeListener { _, b ->
                                    this.IsChecked = b
                                }
                            }
                        }
                    }
                }
            }

            override fun clickHanlder(pos: Int, item: Any, aView: View) {
                Log.e("TaggData", Arraydata.toString())
            }
        }
    }

}