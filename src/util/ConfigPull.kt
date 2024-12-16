package util

import java.awt.Color

class ConfigPull {
    private val config = ConfigLoader()

    private val bgcolor = config.getProperty("BackgroundColor")
    private val textcolor = config.getProperty("TextColor")


    fun bgcolor(): Color {return Color(bgcolor.toInt(16))}

    fun textcolor(): Color {return Color(textcolor.toInt(16))}

    fun maintitle(): String {return config.getProperty("MainTitle")
    }
}