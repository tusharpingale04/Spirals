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
import com.tushar.spirals.MainActivity.Companion.CIRCLE_SIZES
import com.tushar.spirals.ui.theme.*
import com.tushar.spirals.ui.theme.SpiralsTheme
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {

    companion object {
        val CIRCLE_SIZES = mapOf(
            4 to Pair(8f, DarkGreen100),
            8 to Pair(8.2f, DarkGreen90),
            12 to Pair(8.4f, DarkGreen80),
            16 to Pair(8.6f, DarkGreen70),
            20 to Pair(8.8f, DarkGreen60),
            24 to Pair(9f, DarkGreen50),
            28 to Pair(9.2f, DarkGreen40),
            32 to Pair(9.4f, DarkGreen30),
            36 to Pair(9.6f, DarkGreen20),
            40 to Pair(9.8f, DarkGreen10)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpiralsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .background(Color.Blue)
                        .fillMaxSize()
                        .padding(16.dp),
                    color = Color.Blue,
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
    var angle = animatedAngle()
    var xOffset: Float
    var yOffset: Float

    val (dimens, color) = CIRCLE_SIZES[noOfSides] ?: Pair(8f, DarkGreen100)

    for (i in 0 until noOfSides) {
        xOffset = (cx + offSet * cos(Math.toRadians(angle.toDouble()))).toFloat()
        yOffset = (cy + offSet * sin(Math.toRadians(angle.toDouble()))).toFloat()
        drawCircle(
            SolidColor(color),
            radius = dimens,
            center = Offset(xOffset, yOffset)
        )
        angle += rotateAngle
    }
}