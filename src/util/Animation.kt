package util

import java.awt.Color
import kotlin.math.*

class Animation {
    fun inoutsine(x: Double, min: Double = 0.0, max: Double = 1.0): Double {
        val clampedX = x.coerceIn(0.0, 1.0)
        return (sin((clampedX * PI) - (PI / 2)) / 2 + 0.5) * (max - min) + min
    }
}

fun interpolateColor(start: Color, end: Color, fraction: Double): Color {
    val r = (start.red + (end.red - start.red) * fraction).toInt().coerceIn(0, 255)
    val g = (start.green + (end.green - start.green) * fraction).toInt().coerceIn(0, 255)
    val b = (start.blue + (end.blue - start.blue) * fraction).toInt().coerceIn(0, 255)
    return Color(r, g, b)
}