package com.tushar.spirals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import com.tushar.spirals.ui.theme.Color_2596BE
import com.tushar.spirals.ui.theme.SpiralsTheme
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpiralsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .background(Color_2596BE)
                        .fillMaxSize()
                        .padding(16.dp),
                    color = Color_2596BE,
                ) {
                    SpiralCircles()
                }
            }
        }
    }
}

@Composable
fun SpiralCircles() {

    val rotation1 = getCurrentRotation(1)
    val rotation2 = getCurrentRotation(2)
    val rotation3 = getCurrentRotation(3)
    val rotation4 = getCurrentRotation(4)
    val rotation5 = getCurrentRotation(5)
    val rotation6 = getCurrentRotation(6)
    val rotation7 = getCurrentRotation(7)
    val rotation8 = getCurrentRotation(8)
    val rotation9 = getCurrentRotation(9)
    val rotation10 = getCurrentRotation(20)

    Canvas(modifier = Modifier.clipToBounds()) {
        val cx = size.width / 2
        val cy = size.height / 2

        //Circles 1
        drawCircleUsingCoordinates(cx = cx, cy = cy, offSet = 30, rotateAngle = 90f, noOfSides = 4) {
            rotation1.value
        }

        //Circles 2
        drawCircleUsingCoordinates(cx = cx, cy = cy, offSet = 60, rotateAngle = 45f, noOfSides = 8) {
            rotation2.value
        }

        //Circles 3
        drawCircleUsingCoordinates(cx = cx, cy = cy, offSet = 90, rotateAngle = 30f, noOfSides = 12) {
            rotation3.value
        }

        //Circles 4
        drawCircleUsingCoordinates(cx = cx, cy = cy, offSet = 120, rotateAngle = 22.5f, noOfSides = 16) {
            rotation4.value
        }

        //Circles 5
        drawCircleUsingCoordinates(cx = cx, cy = cy, offSet = 150, rotateAngle = 18f, noOfSides = 20) {
            rotation5.value
        }

        //Circles 6
        drawCircleUsingCoordinates(cx = cx, cy = cy, offSet = 180, rotateAngle = 15f, noOfSides = 24) {
            rotation6.value
        }

        //Circles 7
        drawCircleUsingCoordinates(cx = cx, cy = cy, offSet = 210, rotateAngle = 12.86f, noOfSides = 28) {
            rotation7.value
        }

        //Circles 8
        drawCircleUsingCoordinates(cx = cx, cy = cy, offSet = 240, rotateAngle = 11.25f, noOfSides = 32) {
            rotation8.value
        }

        //Circles 9
        drawCircleUsingCoordinates(cx = cx, cy = cy, offSet = 270, rotateAngle = 10f, noOfSides = 36) {
            rotation9.value
        }

        //Circles 10
        drawCircleUsingCoordinates(cx = cx, cy = cy, offSet = 305, rotateAngle = 9f, noOfSides = 40) {
            rotation10.value
        }

    }
}

@Composable
private fun getCurrentRotation(animationTime: Int): Animatable<Float, AnimationVector1D> {
    var currentRotation by remember { mutableStateOf(0f) }

    val rotation = remember { Animatable(currentRotation) }

    LaunchedEffect(true) {
        rotation.animateTo(
            targetValue = currentRotation + 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(animationTime * 1000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        ) {
            currentRotation = value
        }
    }
    return rotation
}

private fun DrawScope.drawCircleUsingCoordinates(
    cx: Float,
    cy: Float,
    offSet: Int,
    rotateAngle: Float,
    noOfSides: Int,
    animatedAngle: () -> Float = { 0.0f }
) {
    val sizeMap = mapOf(
        4 to 8f,
        8 to 8.2f,
        12 to 8.4f,
        16 to 8.6f,
        20 to 8.8f,
        24 to 9f,
        28 to 9.2f,
        32 to 9.4f,
        36 to 9.6f,
        40 to 9.8f
    )
    var angle = animatedAngle()
    var xOffset: Float
    var yOffset: Float

    for (i in 0 until noOfSides) {
        xOffset = (cx + offSet * cos((Math.PI * angle) / 180)).toFloat()
        yOffset = (cy + offSet * sin((Math.PI * angle) / 180)).toFloat()
        drawCircle(
            SolidColor(Color.White),
            radius = sizeMap[noOfSides] ?: 8f,
            center = Offset(xOffset, yOffset)
        )
        angle += rotateAngle
    }
}