package com.example.demoapp_1

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp_1.NoteViewModel


class MainActivity : AppCompatActivity(),InoteAdapter{
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recylclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recylclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesAdapter(this,this)
        recylclerView.adapter = adapter

        //viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        //viewModel = ViewModelProvider(this,
            //ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
                NoteViewModel::class.java
            )
        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adapter.updateList(it)
            }
        })
    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.text} deleted",Toast.LENGTH_LONG).show()
    }

    fun submitNote(view: View) {
        val input = findViewById<EditText>(R.id.EditNote)
        val noteText = input.text.toString()
        if(noteText.isNotEmpty()) {
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"$noteText inserted",Toast.LENGTH_LONG).show()
        }
    }

}