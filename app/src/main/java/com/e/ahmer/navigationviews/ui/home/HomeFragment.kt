@file:Suppress("LocalVariableName")

package com.e.ahmer.navigationviews.ui.home

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.e.ahmer.navigationviews.APIData
import com.e.ahmer.navigationviews.MyPagerAdapter
import com.e.ahmer.navigationviews.R
import com.e.ahmer.navigationviews.databinding.FragmentHomeBinding
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class HomeFragment : Fragment() {

    private lateinit var spinner : Spinner
    private  var viewpager : ViewPager2?=null

    private var _binding: FragmentHomeBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        //view pager images list
        viewpager=rootView.findViewById(R.id.viewPager)
        val itemPic=listOf(
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7,)

        //set the adapter with viewpager
        val adapter= MyPagerAdapter(itemPic )
        viewpager?.adapter=adapter

        //dot indicator code
        val dots = rootView.findViewById<WormDotsIndicator>(R.id.dotsIndicator)
        dots.attachTo(viewpager!!)



        //spinner data code
        spinner=rootView.findViewById(R.id.spinner)

        val itemList=listOf("Shoes","Lady Cloth","Men Cloth","Under Garments")
        val arrayAdapter= ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,itemList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=arrayAdapter

        spinner.onItemSelectedListener= object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem=parent?.getItemAtPosition(position).toString()
                Toast.makeText(requireContext(),"You selected $selectedItem ", Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }


        }
        //me pic click and view the large screen code
        val shoe=rootView.findViewById<ImageView>(R.id.shoes)

        shoe.setOnClickListener {
            val bundle= Bundle()
            bundle.getInt("image_res",R.drawable.pic5)
            findNavController().navigate(R.id.shoePic,bundle)
        }

        //me pic click and view the large screen code
        val fragpic=rootView.findViewById<ImageView>(R.id.meImage)
        fragpic.setOnClickListener {
            val bundle= Bundle()
            bundle.getInt("image_res",R.drawable.pic3)
            findNavController().navigate(R.id.fullscreenImageFragment,bundle)
        }



        //view all btn(things API )  and click the back btn then show the exit alert box code
        val viewAllBtn = rootView.findViewById<Button>(R.id.viewbtn)
        val backBtn=rootView.findViewById<ImageButton>(R.id.backBtn)

        // Set click listener
        viewAllBtn.setOnClickListener {

            val intent= Intent(requireContext(), APIData::class.java)
            startActivity(intent)

        }

        //exit button alert dialoge code

        backBtn.setOnClickListener {
            val builder= AlertDialog.Builder(requireContext())

            builder.setIcon(R.drawable.baseline_exit_to_app_24)
            builder.setTitle("Exit")
            builder.setMessage("Do you close the App?")
            builder.setNegativeButton("No", DialogInterface.OnClickListener{ DialogInterface,i ->
            })

            builder.setPositiveButton("Yes", DialogInterface.OnClickListener{ DialogInterface,i ->
                Toast.makeText(requireContext(),"you exit this Store", Toast.LENGTH_SHORT).show()
                requireActivity().finish()
            })
            builder.show()
        }


        return rootView
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




