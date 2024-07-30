package com.example.spinner

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spinner.databinding.ActivityListBaseAdapterBinding

class ListBaseAdapter : AppCompatActivity() {
    lateinit var binding: ActivityListBaseAdapterBinding
    var list = arrayListOf("One","two","three")
    var Slist = arrayListOf<Student>()
    var listAdapter = ListAdapter(this,Slist)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityListBaseAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Slist.add(Student(51,"Sahil","DSA"))
        Slist.add(Student(30,"Sam","CNS"))
        Slist.add(Student(41,"ram","Java"))
        binding.Listviewnew.adapter=listAdapter


        binding.Addbtn.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.add_list)

            val rollno = dialog.findViewById<EditText>(R.id.InputRollNo)
            val name = dialog.findViewById<EditText>(R.id.InputStudentName)
            val subject = dialog.findViewById<EditText>(R.id.InputSubject)
            val btn = dialog.findViewById<Button>(R.id.ADD_list)
            dialog.show()
            btn.setOnClickListener{
                val Irollno = rollno.text.toString()
                val Iname = name.text.toString()
                val Isubject = subject.text.toString()
                if(Irollno.isNullOrEmpty() || Iname.isNullOrEmpty() || Isubject.isNullOrEmpty()){
                    Toast.makeText(this, "Please enter a valid details", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                else{
                Slist.add(Student(Irollno.toInt(),Iname,Isubject))
                listAdapter.notifyDataSetChanged()
                dialog.dismiss()
                }
            }
        }
    }
}