package com.example.todoapp.feature_todo.presentation.add_edit_task

import androidx.compose.ui.focus.FocusState

sealed class AddEditTaskEvent{
    data class EnteredTitle(val value:String):AddEditTaskEvent()
    data class ChangeTitleFocus(val focusState: FocusState):AddEditTaskEvent()
    data class EnteredDetail(val value:String):AddEditTaskEvent()
    data class ChangeDetailFocus(val focusState: FocusState):AddEditTaskEvent()
    data class ImportantCheck(val isImportant:Boolean):AddEditTaskEvent()
    object SaveTask:AddEditTaskEvent()

}