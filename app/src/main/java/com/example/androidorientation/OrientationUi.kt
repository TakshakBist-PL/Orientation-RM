package com.example.androidorientation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androidorientation.Utility.isPortrait

@Composable
fun OrientationUI(viewModel: OrientationViewModel) {

    val orientation by viewModel.orientation.collectAsState()

    DisposableEffect(Unit) {
        viewModel.startListening()

        onDispose {
            viewModel.stopListening()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Fixed circle
        Surface(
            shape = CircleShape,
            color = Color.Green,
            modifier = Modifier.size(150.dp)
        ) {}

        // Moving circle based on pitch and roll
        val sensitivity = 2
        val offsetX = orientation.roll * sensitivity
        val offsetY = (orientation.pitch + 90) * sensitivity

        Surface(
            shape = CircleShape,
            color = if (isPortrait(orientation.pitch, orientation.roll)) Color.Blue else Color.Red,
            modifier = Modifier
                .offset(x = offsetX.dp, y = offsetY.dp)
                .size(150.dp)
        ) {}
    }
}
