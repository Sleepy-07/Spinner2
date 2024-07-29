package com.example.spinner

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var list = arrayListOf("one ", "two","three")
    lateinit var arrayAdapter: ArrayAdapter<String>
    private  val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_activated_1,list)

        binding.listview.adapter = arrayAdapter

        binding.listview.setOnItemClickListener { parent, view, position, id ->
           val dialog = Dialog(this)
            dialog.setContentView(R.layout.alert_dialog3)
            val new = dialog.findViewById<EditText>(R.id.Inputtext)
            val btn = dialog.findViewById<Button>(R.id.Savebtn2)

            btn.setOnClickListener {
                list.set(position,new.text.toString())
                arrayAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            dialog.show()

        }

        binding.listview.setOnItemLongClickListener{ parent, view, position, id ->

            val dialog = Dialog(this)
            dialog.setContentView(R.layout.alert_dialog2)
            val btn = dialog.findViewById<Button>(R.id.Yes)
            val btn2= dialog.findViewById<Button>(R.id.No)
            dialog.show()

            btn.setOnClickListener {
                list.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            btn2.setOnClickListener {
                Toast.makeText(this, "Item doesn't Remove", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            return@setOnItemLongClickListener true
        }


        binding.fabbtn.setOnClickListener {
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


        binding.btnSpin.setOnClickListener {
            val intent = Intent(this,Spinner::class.java)
            startActivity(intent)
        }




    }
}