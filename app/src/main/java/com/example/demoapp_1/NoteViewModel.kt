package com.example.demoapp_1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes: LiveData<List<Note>>

    init{
        val dao = NoteDatabase.getDatabase(application).getnoteDao()
        val repository = NoteRepository(dao)
        allNotes = repository.all_notes
    }
}