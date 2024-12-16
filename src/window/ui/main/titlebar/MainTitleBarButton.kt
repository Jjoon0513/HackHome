package window.ui.main.titlebar

import util.Animation
import util.ConfigPull
import util.interpolateColor
import java.awt.Color
import java.awt.Dimension
import java.awt.Insets
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.Timer

//TODO - 글래스 겉표면 색을 따로 지정할수있게 해줘야할듯.
// foreground 색을 변경할수 있도록.
private val configPull = ConfigPull()

class MainTitleBarButton(
    textin: String,
    textcolor: Color = configPull.textcolor()
) : JButton(textin) {
    private val configPull = ConfigPull()

    init {
        layout = null
        margin = Insets(10, 10, 10, 10)
        border = BorderFactory.createLineBorder(textcolor, 2)
        background = configPull.bgcolor()
        foreground = textcolor
        preferredSize = Dimension(20, 20)
        isFocusPainted = false

        val animation = Animation()
        val defaultBg = configPull.bgcolor()
        val defaultFg = textcolor

        var progress = 0.0
        var direction = 1
        val animationTimer = Timer(10, null)


        fun updateColors() {
            val easedProgress = animation.inoutsine(progress)
            background = interpolateColor(defaultBg, defaultFg, easedProgress)
            foreground = interpolateColor(defaultFg, defaultBg, easedProgress)
            val borderColor = interpolateColor(defaultFg, defaultBg, easedProgress)
            border = BorderFactory.createLineBorder(borderColor, 2)
        }


        animationTimer.addActionListener {
            progress += 0.02 * direction
            progress = progress.coerceIn(0.0, 1.0)

            updateColors()

            if (progress == 0.0 || progress == 1.0) {
                animationTimer.stop()
            }
        }

        addMouseListener(object : MouseAdapter() {
            override fun mouseEntered(e: MouseEvent?) {
                direction = 1 // forward
                if (!animationTimer.isRunning) animationTimer.start()
            }

            override fun mouseExited(e: MouseEvent?) {
                direction = -1 // backward
                if (!animationTimer.isRunning) animationTimer.start()
            }
        })
    }
}
