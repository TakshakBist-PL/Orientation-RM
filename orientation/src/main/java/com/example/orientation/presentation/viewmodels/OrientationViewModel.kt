package com.example.orientation.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.orientation.data.OrientationSensors
import com.example.orientation.data.model.Orientation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OrientationViewModel(context: Context): ViewModel() {

    private var _orientation = MutableStateFlow(Orientation())
    val orientation: StateFlow<Orientation> get() = _orientation.asStateFlow()

    private val orientationSensor = OrientationSensors(context) { newPitch, newRoll ->
        _orientation.value = _orientation.value.copy(
            pitch = newPitch,
            roll = newRoll
        )
    }

    fun startListening() {
        orientationSensor.startListening()
    }

    fun stopListening() {
        orientationSensor.stopListening()
    }

    override fun onCleared() {
        super.onCleared()
        stopListening()
    }

}