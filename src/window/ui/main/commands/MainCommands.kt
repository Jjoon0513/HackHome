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


    fun reloadlang(){
        val newconfigLoader = ConfigLoader()
        language = LangLoader(newconfigLoader.getProperty("language"))
        println("언어가 변경되었습니다: ${newconfigLoader.getProperty("language")}")
    }

    fun commandinput(
        input: String,
    ) {

        val inputlist = input.trim().split(' ')
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