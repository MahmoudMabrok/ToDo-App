package com.polotika.todoapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.polotika.todoapp.R
import com.polotika.todoapp.pojo.data.models.NoteModel
import com.polotika.todoapp.pojo.data.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(repository: NotesRepository, dispatchers: Dispatchers) :
    BaseViewModel(repository = repository, dispatcher = dispatchers) {

        val title = MutableLiveData<String>()
        val body = MutableLiveData<String>()
        val priority = MutableLiveData<String>()

    val addNoteState = MutableStateFlow<AddNoteState>(AddNoteState.EmptyState)

    fun onAddClicked(){
        if (title.value !=null && body.value!=null) {
            val note = NoteModel(
                id= 0,
                title = title.value.toString(),
                description = body.value.toString(),
                priority = getPriority(priority.value.toString())
            )
            addNote(noteModel = note)
            addNoteState.value = AddNoteState.CompleteState

        } else {
            addNoteState.value = AddNoteState.EmptyDataState
        }
    }


    }

sealed class AddNoteState(){
    object EmptyState:AddNoteState()
    object EmptyDataState:AddNoteState()
    object CompleteState:AddNoteState()
}