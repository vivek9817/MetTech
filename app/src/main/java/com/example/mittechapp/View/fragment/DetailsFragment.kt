package com.example.mittechapp.View.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mittechapp.CommonUtils.CommonUtils
import com.example.mittechapp.Model.ItemsData
import com.example.mittechapp.R
import com.example.mittechapp.adapter.CommonAlertAdapter
import com.example.mittechapp.databinding.FragmentDetailsBinding
import com.example.mittechapp.databinding.FragmentInformationBinding
import com.example.mittechapp.viewholder.GenericViewHolder

class DetailsFragment : Fragment(R.layout.fragment_details) {

    lateinit var binding: FragmentDetailsBinding
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        var view = binding.root
        recyclerView =
            CommonUtils.initializeRecyclerView(
                binding.recyclerItems,
                requireContext()
            )

        onClickOnUi()
        return view
    }


    private fun onClickOnUi() {
        var Arraydata: ArrayList<ItemsData> = ArrayList()

        Arraydata.add(
            ItemsData(
                "Tomato", "Fruits", "$0.00"
            )
        )

        Arraydata.add(
            ItemsData(
                "Mango", "Fruits", "$5.90"
            )
        )

        Arraydata.add(
            ItemsData(
                "Table", "Furniture", "$156.00"
            )
        )

        recyclerView.adapter = object : CommonAlertAdapter<Any>(
            R.layout.common_alert_rec_data,
            Arraydata as ArrayList<Any>
        ) {
            override fun bindData(holder: GenericViewHolder<Any>, item: Any) {
                when (item) {
                    CommonUtils.checkTypeCast<ItemsData>(item) -> {
                        var itemsName =
                            CommonUtils.checkTypeCast<ItemsData>(item)!!.Item
                        holder.itemView.findViewById<TextView>(R.id.item)
                            .setText(itemsName)

                        var catagory =
                            CommonUtils.checkTypeCast<ItemsData>(item)!!.Category
                        holder.itemView.findViewById<TextView>(R.id.catagry)
                            .setText(catagory)

                        var price =
                            CommonUtils.checkTypeCast<ItemsData>(item)!!.Itemprice
                        holder.itemView.findViewById<TextView>(R.id.price)
                            .setText(price)


                        var i: Int = 0
                        holder.itemView.findViewById<TextView>(R.id.edtQuantity).text = i.toString()
                        holder.itemView.findViewById<TextView>(R.id.txtAddQuantity)
                            .setOnClickListener(object : View.OnClickListener {
                                override fun onClick(p0: View?) {
                                    if (i == 20) {
                                        i = 20
                                        CommonUtils.snakeBarPopUp(
                                            binding.lindetails,
                                            "Cannot Be More Than 20"
                                        )
                                    } else {
                                        i++
                                        holder.itemView.findViewById<TextView>(R.id.edtQuantity).text =
                                            i.toString()
                                    }
                                }
                            })

                        holder.itemView.findViewById<TextView>(R.id.txtNegateQuantity)
                            .setOnClickListener {
                                if (i == 0) {
                                    i = 0
                                    CommonUtils.snakeBarPopUp(
                                        binding.lindetails,
                                        "Cannot Be Less Than 0"
                                    )
                                } else {
                                    i--
                                    holder.itemView.findViewById<TextView>(R.id.edtQuantity).text =
                                        i.toString()
                                }
                            }

                        when (CommonUtils.checkTypeCast<ItemsData>(item)!!.Item) {
                            "Mango" -> {
                                holder.itemView.findViewById<ImageView>(R.id.img1)
                                    .setImageResource(R.drawable.mango)
                            }
                            "Tomato" -> {
                                holder.itemView.findViewById<ImageView>(R.id.img1)
                                    .setImageResource(R.drawable.tomato)
                            }
                            "Table" -> {
                                holder.itemView.findViewById<ImageView>(R.id.img1)
                                    .setImageResource(R.drawable.table)
                            }
                        }

                    }
                }
            }

            override fun clickHanlder(pos: Int, item: Any, aView: View) {
                CommonUtils.checkTypeCast<ItemsData>(item).apply {
//                    CommonUtils.snakeBarPopUp(binding.linfrag1, pos.toString())
                }
            }

        }
    }

}