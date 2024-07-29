package com.example.spinner

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spinner.databinding.ActivitySpinnerBinding


var Gloc = 0
class Spinner : AppCompatActivity() {
    lateinit var binding: ActivitySpinnerBinding
    var list = mutableListOf("one","Two","Three")
    lateinit var arrayAdapter: ArrayAdapter<String>
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivitySpinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        arrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_activated_1,list)
        binding.spselect.adapter=arrayAdapter


        binding.spselect.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                Gloc = position



            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.fabbtn2.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.alert_dialog)
            val text = dialog.findViewById<EditText>(R.id.Input)
            val btn = dialog.findViewById<Button>(R.id.Savebtn)

            btn.setOnClickListener {
                val inputtext = text.text.toString()
                if(inputtext.isNullOrEmpty()){
                    Toast.makeText(this, "Enter a valid item name", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                else {
                    list.add(inputtext)
                    arrayAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }

            dialog.show()
        }

        binding.fabbtn22.setOnClickListener {
//            val dialog = Dialog(this)
//            dialog.setContentView(R.layout.alert_dialog22)
//            val text = dialog.findViewById<EditText>(R.id.edit)
//            val btn = dialog.findViewById<Button>(R.id.Yes)
//            dialog.show()
//            btn.setOnClickListener {
//                    val input = text.text.toString()
//                if(input.isNullOrEmpty()){
//                    Toast.makeText(this, "Please enter a valid postion", Toast.LENGTH_SHORT).show()
//                    dialog.dismiss()
//                }
//                else {
//                   val pos = input.toInt()
//
//                    if (pos < 1 || pos > list.size) {
//                        Toast.makeText(this, "Please enter a valid postion", Toast.LENGTH_SHORT).show()
//                        dialog.dismiss()
//
//                    }
//                    else if (list.size==1){
//                        Toast.makeText(this, "Can't remove last item" , Toast.LENGTH_SHORT).show()
//                        dialog.dismiss()
//
//                    }
//
//                    else {
//                        list.removeAt(pos - 1)
//                        arrayAdapter.notifyDataSetChanged()
//                        dialog.dismiss()
//                    }
//                }
//        }


            val dialog = Dialog(this)
            dialog.setContentView(R.layout.alert_dialog2)
            val btn = dialog.findViewById<Button>(R.id.Yes)
            val btn2= dialog.findViewById<Button>(R.id.No)
            dialog.show()

            btn.setOnClickListener {
                if(list.size == 1){
                    Toast.makeText(this, "Can't remove last item" , Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                else {
                list.removeAt(Gloc)
                arrayAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            }
            btn2.setOnClickListener {
                Toast.makeText(this, "Item doesn't Remove", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        }


        binding.fabbtn23.setOnClickListener{
//            val dialog = Dialog(this)
//            dialog.setContentView(R.layout.alert_dialog33)
//            val text1 = dialog.findViewById<EditText>(R.id.Inputtext2)
//            val text2 = dialog.findViewById<EditText>(R.id.Inputnumber)
//            val btn = dialog.findViewById<Button>(R.id.Savebtn23)
//
//                dialog.show()
//            btn.setOnClickListener {
//                val input1 = text1.text.toString()
//                val input2 = text2.text.toString()
//                if(input1.isNullOrEmpty() || input2.isNullOrEmpty()){
//                Toast.makeText(this, "Please enter a valid details", Toast.LENGTH_SHORT).show()
//                dialog.dismiss()
//                }
//                else {
//                val loc = input2.toInt()
//                    if(loc < 1 || loc > list.size ){
//                    Toast.makeText(this, "Please enter a valid postion", Toast.LENGTH_SHORT).show()
//                    dialog.dismiss()
//                    }
//                    else{
//                    list.set(loc-1,input1)
//                    arrayAdapter.notifyDataSetChanged()
//                    dialog.dismiss()
//                    }
//                }
//            }


            val dialog = Dialog(this)
            dialog.setContentView(R.layout.alert_dialog3)
            val new = dialog.findViewById<EditText>(R.id.Inputtext)
            val btn = dialog.findViewById<Button>(R.id.Savebtn2)

            btn.setOnClickListener {
                list.set(Gloc,new.text.toString())
                arrayAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            dialog.show()

        }

    }
}
