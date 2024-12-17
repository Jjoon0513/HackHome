package window.ui.main.commands

import util.ConfigLoader
import util.LangLoader

class ChangeLang(
    maincommands: MainCommands
) {
    private val mc = maincommands
    private val cl = ConfigLoader()
    private var langloader = LangLoader(cl.getProperty("language"))

    private fun help(): String {
        return langloader.getProperty("ChangeLang_help")
    }

    fun changelang(inp: List<String>): String {
        return try {
            langloader = LangLoader(inp[1]) // 새 언어 로드
            cl.pushProperty("language", inp[1]) // 설정 저장
            mc.reloadlang(inp[1]) // MainCommands 동기화
            langloader.getProperty("ChangeLang_finish")
        } catch (e: IndexOutOfBoundsException) {
            help()
        } catch (e: IllegalArgumentException) {
            "\"${inp[1]}\" " + langloader.getProperty("ChangeLang_cantfind")
        }
    }
}
