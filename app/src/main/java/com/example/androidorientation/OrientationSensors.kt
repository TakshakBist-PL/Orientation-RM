package com.example.androidorientation

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

class OrientationSensors(
    context: Context,
    val onOrientationChanged: (Double, Double) -> Unit
): SensorEventListener {

    private val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val rotationVectorSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)

    private val rotationMatrix = FloatArray(9)
    private val orientationAngles = FloatArray(3)

    fun startListening(){
        rotationVectorSensor?.also { sensor: Sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    fun stopListening(){
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        p0?.let {
            if (it.sensor.type == Sensor.TYPE_ROTATION_VECTOR){
                SensorManager.getRotationMatrixFromVector(rotationMatrix, it.values)
                SensorManager.getOrientation(rotationMatrix,orientationAngles)

                val pitch = Math.toDegrees(orientationAngles[1].toDouble())
                val roll = Math.toDegrees(orientationAngles[2].toDouble())

                Log.d("OrientationSensor", "pitch: $pitch and roll: $roll")

                onOrientationChanged(pitch,roll)
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        println("Accuracy changed")
    }


}