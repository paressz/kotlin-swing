import Util.hexColor
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.GridLayout
import javax.swing.BorderFactory
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.WindowConstants

class TesClass : JFrame() {
    private val componentWidth = 800

    init {
        initcomponent()
    }

    private fun initcomponent() {
        size = Dimension(componentWidth, 500)
        layout = BorderLayout()
        add(
            CenterPanel(),
            BorderLayout.CENTER
        )
        add(
            TopPanel(),
            BorderLayout.NORTH
        )
        add(
            SidePanel(),
            BorderLayout.WEST
        )
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
        isUndecorated = true
    }
    private fun TopPanel() : JPanel {
        val panel = JPanel()
        panel.apply {
            layout = GridLayout(3,3)
            preferredSize = Dimension(componentWidth, 50)
            background = hexColor("282828")
            val jl : MutableList<JLabel>  = mutableListOf()
            for (i in 0..8) {
                jl.add(JLabel())
                this.add(jl[i])
            }

            jl[3].apply {
                border = BorderFactory.createEmptyBorder(0,50,0,0)
                text = "APLIKASI RAPOR SDN XTA"
                foreground = hexColor("FFFFFF")
                isVisible = true
            }
        }
        return panel
    }
    private fun CenterPanel() : JPanel {
        val panel = JPanel()
        panel.apply {
            preferredSize = Dimension(750, 450)
            background = hexColor("BEBEBE")

        }
        return panel
    }
    private fun SidePanel(): JPanel {
        val panel = JPanel()
        panel.apply {
            preferredSize = Dimension(50, 500)
            background = hexColor("25344A")

        }
        return panel
    }
}