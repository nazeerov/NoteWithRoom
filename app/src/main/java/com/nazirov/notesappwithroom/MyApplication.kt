package com.nazirov.notesappwithroom

import android.app.Application
import android.os.Handler
import android.os.Looper
import com.nazirov.notesappwithroom.database.NoteRepository
import com.nazirov.notesappwithroom.model.Note
import java.util.concurrent.Executors

class MyApplication : Application() {
    companion object{
        var notes=ArrayList<Note>()
    }

    override fun onCreate() {
        super.onCreate()
        loadOldNotes()
    }

    private fun loadOldNotes() {
        val repository= NoteRepository(this)
        val exacutor= Executors.newSingleThreadExecutor()
        val handler= Handler(Looper.getMainLooper())
        exacutor.execute{
            var notess =repository.getNotes()
            handler.post {
                notes.addAll(notess as ArrayList<Note>)
            }
        }

    }
}