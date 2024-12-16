package util

import java.util.Properties

class ConfigLoader {
    private val properties = Properties()

    init {
        val inputStream = this::class.java.classLoader.getResourceAsStream("Config.properties")
            ?: throw IllegalArgumentException("Properties file not found")
        properties.load(inputStream)
    }

    fun getProperty(key: String): String {
        return properties.getProperty(key)
    }

    fun pushProperty(key: String, value: String){
        properties.setProperty(key, value)
    }
}
