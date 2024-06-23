package com.example.notepad_app.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notepad_app.data.note
import com.example.notepad_app.data.noteDao
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class noteViewModel(
    private val dao: noteDao
) : ViewModel() {

    private val isSortedByDateAdded = MutableStateFlow(true)

    private val notes = isSortedByDateAdded.flatMapLatest { sortedByDateAdded ->
        if (sortedByDateAdded) {
            dao.getOrderByDateAddedBy()
        } else {
            dao.getOrderByTitle()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    var _state = MutableStateFlow(noteState())
    var state = combine(_state, isSortedByDateAdded, notes) {
            state, isSortedByDateAdded, notes ->
            state.copy(
                notes= notes
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),noteState())

    fun onEvent(event: noteEvent) {
        when(event) {
            is noteEvent.deleteNote -> {

                viewModelScope.launch {
                    dao.deleteNote(event.note)
                }
            }

            is noteEvent.saveNote -> {
                val note = note(
                    title = event.title,
                    disp = event.disp,
                    dateAdded = System.currentTimeMillis()

                )
                viewModelScope.launch {
                    dao.upsertNote(note)
                }
                _state.value = noteState(
                    title = mutableStateOf(""),
                    disp = mutableStateOf("")
                )
            }

            is noteEvent.sortNotes -> {
                isSortedByDateAdded.value = !isSortedByDateAdded.value
            }
        }
    }
}

