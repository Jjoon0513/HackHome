package window.ui.main

import window.ui.main.commands.MainCommands
import window.ui.MainWindow
import util.ConfigPull
import java.awt.Dimension
import java.awt.Font
import java.awt.event.ActionEvent
import javax.swing.BorderFactory
import javax.swing.JTextField

class MainTextField(
    textArea: MainTextArea,
    mainWindow: MainWindow
) : JTextField() {
    init {
        val configcolor = ConfigPull()

        background = configcolor.bgcolor()
        foreground = configcolor.textcolor()
        border = BorderFactory.createLineBorder(configcolor.textcolor(), 2)

        // 선호 크기 설정
        preferredSize = Dimension(200, 40)

        font = Font("Noto Sans", Font.PLAIN, 12)


        // 커맨드 설정
        val command = MainCommands(textArea, mainWindow)
        addActionListener { _: ActionEvent ->
            val input = text
            textArea.append(">>> $input\n")
            text = ""
            // 커맨드 확인
            command.commandinput(input)
        }
    }

    fun changefontsize(size: Int) {
        font = Font("Noto Sans", Font.PLAIN, size)
    }

}
