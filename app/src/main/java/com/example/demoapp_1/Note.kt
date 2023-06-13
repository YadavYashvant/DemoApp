package com.example.demoapp_1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class Note(@ColumnInfo(name = "text")val text:String){
           @PrimaryKey(autoGenerate = true)val idty = 0

}