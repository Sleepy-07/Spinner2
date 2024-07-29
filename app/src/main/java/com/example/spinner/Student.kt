package com.example.spinner

data class Student(
    var rollNo : Int? = 0,
    var name : String? = "",
    var subject : String? = ""
){
    override fun toString(): String {
        return "$rollNo\n$name\n$subject"
    }
}
