package window.ui.main

import window.ui.main.titlebar.MainTitleBarButton
import util.ConfigPull
import javax.swing.*
import java.awt.*
import java.awt.Frame.MAXIMIZED_BOTH
import java.awt.event.*

class MainTitleBar(
    private val mainWindow: JFrame,
    private val mainTextArea: MainTextArea,
    private val mainTextField: MainTextField
) : JPanel(BorderLayout()) {
    private var xOffset = 0
    private var yOffset = 0
    private val configPull = ConfigPull()
    var isextended = false

    init {
        border = BorderFactory.createLineBorder(configPull.textcolor(), 2)
        background = configPull.bgcolor()

        val titleLabel = JLabel(configPull.maintitle(), SwingConstants.CENTER)
        titleLabel.foreground = configPull.textcolor()
        add(titleLabel, BorderLayout.CENTER)

        val closeButton = MainTitleBarButton("X", Color.red).apply {
            addActionListener {
                mainWindow.dispose()
            }
        }

        val extendbutton = MainTitleBarButton("O").apply {
            addActionListener {
                if (isextended) {
                    text = "O"
                    isextended = false
                    mainWindow.extendedState = Frame.NORMAL

                    //버튼 사이즈 재정의
                    closeButton.preferredSize = Dimension(20, 20)
                    this.preferredSize = Dimension(20, 20)

                    //텍스트 사이즈 재정의
                    mainTextArea.changefontsize(12)
                    mainTextField.changefontsize(12)
                    titleLabel.font = Font("Noto Sans", Font.PLAIN, 12)

                } else {
                    isextended = true
                    mainWindow.extendedState = MAXIMIZED_BOTH
                    text = "o"

                    //버튼 사이즈 재정의
                    closeButton.preferredSize = Dimension(40, 40)
                    this.preferredSize = Dimension(40, 40)

                    //텍스트 사이즈 재정의
                    mainTextArea.changefontsize(20)
                    mainTextField.changefontsize(20)
                    titleLabel.font = Font("Noto Sans", Font.PLAIN, 20)
                }
            }
        }

        val buttonPanel = JPanel(FlowLayout(FlowLayout.RIGHT, 0, 0)).apply {
            background = configPull.bgcolor()
            add(extendbutton)
            add(closeButton)
        }
        add(buttonPanel, BorderLayout.EAST)

        addMouseListener(object : MouseAdapter() {
            override fun mousePressed(e: MouseEvent) {
                xOffset = e.x
                yOffset = e.y
            }
        })
        addMouseMotionListener(object : MouseAdapter() {
            override fun mouseDragged(e: MouseEvent) {
                if (isextended) {
                    extendbutton.text = "O"
                    isextended = false
                    mainWindow.extendedState = Frame.NORMAL
                    //TODO - 이거 해결좀
//                    if (prevLocation != null && prevSize != null) {
//                        val newX = prevLocation!!.x + (mainWindow.width - prevSize!!.width) / 2
//                        val newY = prevLocation!!.y + (mainWindow.height - prevSize!!.height) / 2
//                        mainWindow.location = Point(newX, newY)
//                    }
                } else {
                    mainWindow.location = Point(
                        mainWindow.location.x + e.x - xOffset,
                        mainWindow.location.y + e.y - yOffset
                    )
                }

            }
        })
    }
}
