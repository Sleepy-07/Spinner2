package com.example.spinner

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class ListAdapter(var context: Context,var list : ArrayList<Student>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return  position.toLong()
    }

    @SuppressLint("ViewHolder", "MissingInflatedId", "SuspiciousIndentation")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       val view = LayoutInflater.from(parent?.context).inflate(R.layout.layout_file,parent,false)

        val tvRollNo = view.findViewById<TextView>(R.id.RollNo)
        tvRollNo.setText(list[position].rollNo.toString())

        val tvName = view.findViewById<TextView>(R.id.Name)
        tvName.setText(list[position].name)

        val tvSubject = view.findViewById<TextView>(R.id.Subject)
        tvSubject.setText(list[position].subject)


        val Update = view.findViewById<Button>(R.id.Update)
        val Delete = view.findViewById<Button>(R.id.Delete)

        Delete.setOnClickListener {
         val dialog = Dialog(context)
            dialog.setContentView(R.layout.alert_dialog2)
            val btn = dialog.findViewById<Button>(R.id.Yes)
            dialog.show()

            btn.setOnClickListener {
                list.remove(list[position])
                notifyDataSetChanged()
                dialog.dismiss()
            }

        }

        Update.setOnClickListener {
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.update_list)
            val rollno = dialog.findViewById<EditText>(R.id.InputRollNo)
            val name = dialog.findViewById<EditText>(R.id.InputStudentName)
            val subject = dialog.findViewById<EditText>(R.id.InputSubject)
            val btn = dialog.findViewById<Button>(R.id.Update_list)

            dialog.show()
            btn.setOnClickListener {
                val Irollno = rollno.text.toString()
                val Iname = name.text.toString()
                val Isubject = subject.text.toString()
                if(Irollno.isNullOrEmpty() || Iname.isNullOrEmpty() || Isubject.isNullOrEmpty()){
                    Toast.makeText(context, "Please enter a valid details", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                else{
                list.set(position, Student(Irollno.toInt(),Iname,Isubject))
                notifyDataSetChanged()
                dialog.dismiss()
            }
            }
        }

        return view

    }
}