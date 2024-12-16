package window.ui


import window.ui.main.MainScrollPane
import window.ui.main.MainTextArea
import window.ui.main.MainTextField
import window.ui.main.MainTitleBar
import java.awt.BorderLayout
import javax.swing.JFrame

class MainWindow: JFrame() {
    private val textArea = MainTextArea()
    private val textField = MainTextField(textArea, this)
    private val scrollPane = MainScrollPane(textArea)

    init {
        //설정
        isUndecorated = true
        title = "HackHome"
        setSize(500, 400)
        defaultCloseOperation = EXIT_ON_CLOSE
        layout = BorderLayout()

        //위치: MainTextArea 인데 스크롤 추가는 여기서 해씀
        add(scrollPane, BorderLayout.CENTER)

        //위치: MainTextField
        add(textField, BorderLayout.SOUTH)

        // CustomTitleBar 클래스 인스턴스화
        val customTitleBar = MainTitleBar(this, textArea, textField)
        add(customTitleBar, BorderLayout.NORTH)


        // JFrame 보이기
        isVisible = true
    }
}