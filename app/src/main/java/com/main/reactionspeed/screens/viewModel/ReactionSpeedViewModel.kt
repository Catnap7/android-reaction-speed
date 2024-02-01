package com.main.reactionspeed.screens.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ReactionSpeedViewModel : ViewModel() {

    private var _firstRecord by mutableIntStateOf(0)
    private var _secondRecord by mutableIntStateOf(0)
    private var _thirdRecord by mutableIntStateOf(0)

    fun updateRecordUiState(firstRecord: Int, secondRecord: Int, thirdRecord: Int) {
        this._firstRecord = firstRecord
        this._secondRecord = secondRecord
        this._thirdRecord = thirdRecord
    }

    fun getRecordsAvg() : Int {
        return (_firstRecord + _secondRecord + _thirdRecord) / 3
    }

}

