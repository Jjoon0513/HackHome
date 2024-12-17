package window.ui.main.commands

import window.ui.MainWindow
import window.ui.main.MainTextArea
import util.ConfigLoader
import util.LangLoader

class MainCommands(
    private val textArea: MainTextArea,
    private val mainWindow: MainWindow
) {
    private val configLoader = ConfigLoader()
    private var language = LangLoader(configLoader.getProperty("language"))
    private val changelang = ChangeLang(this)


    fun reloadlang(lang: String) {
        println("언어설정 변경")
        configLoader.pushProperty("language", lang)
        language = LangLoader(lang)
    }

    fun commandinput(
        input: String,
    ) {


        val inputlist = input.trim().split(' ')
        println("명령 들어옴 ->$inputlist")
        when (inputlist[0]) {
            //종료 명령어
            "exit", "bye" -> {
                textArea.append(language.getProperty("MainCommands_exit") + "\n\n")
                mainWindow.dispose()
            }

            //전체 삭제
            "clear", "cls" -> {
                textArea.text = ""
            }

            //언어 변경
            "lang" -> {
                textArea.append(changelang.changelang(inputlist) + "\n\n")
            }

            //도움!!!!!!!!!!!!!!!!!
            "help" -> {
                textArea.append(language.getProperty("MainCommands_help") + "\n\n")
            }

            //아무것도 입력하지 않았을때
            "" -> {
                textArea.append("")
            }

            //명령어가 아닐때
            else -> {
                val unknownCommandMessage = "\"${inputlist[0]}\" " +
                        language.getProperty("MainCommands_unknown")
                textArea.append(unknownCommandMessage + "\n\n")
            }
        }
    }
}