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
        println("인수받음 $properties.getProperty(key)")
        return properties.getProperty(key)
    }

    fun pushProperty(key: String, value: String){
        println("푸쉬됨 $properties.getProperty(key)")
        properties.setProperty(key, value)
    }
}
