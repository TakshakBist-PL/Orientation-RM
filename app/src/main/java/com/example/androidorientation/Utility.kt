package com.example.androidorientation

import kotlin.math.abs

object Utility {

    fun isPortrait(pitch: Double, roll: Double): Boolean {
        val pitchThreshold = 20
        val rollThreshold = 20

        // Check if the pitch is around ±90 degrees (device is upright)
        val isPitchVertical = abs(abs(pitch) - 90) < pitchThreshold

        // Check if the roll is near 0 (normal portrait) or near 180 (upside-down portrait)
        val isRollUpright = abs(roll) < rollThreshold || abs(roll) > 180 - rollThreshold

        return isPitchVertical && isRollUpright
    }

}