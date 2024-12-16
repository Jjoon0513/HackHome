package util

import java.io.InputStreamReader
import java.util.Properties

class LangLoader(
    langName: String
) {
    private val properties = Properties()

    init {
        val inputStream = this::class.java.classLoader.getResourceAsStream("translate/${langName}.properties")
            ?: throw IllegalArgumentException("Properties file not found")
        InputStreamReader(inputStream, Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
    }

    fun getProperty(key: String): String {
        return properties.getProperty(key)
    }
}