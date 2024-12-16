package window.ui.main

import javax.swing.JScrollPane

class MainScrollPane(
    textArea: MainTextArea
): JScrollPane(textArea) {
    init{
        horizontalScrollBarPolicy = HORIZONTAL_SCROLLBAR_NEVER
        verticalScrollBarPolicy = VERTICAL_SCROLLBAR_NEVER
        border = null
    }
}