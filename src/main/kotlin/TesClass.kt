import Util.hexColor
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.event.ActionEvent
import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class TesClass : JFrame() {
    private var side: JPanel? = null
    private var top: JPanel? = null
    private var center: JPanel? = null
    private var btnTest: JButton? = null
    private var lbTest: JLabel? = null
    private var lb2: JLabel? = null
    private var click = 0
    private var observable: Observable<String>? = null
    private var jtf: JTextField? = null

    init {
        observable = Observable.create { it: ObservableEmitter<String> ->
            it.onNext(
                jtf!!.text
            )
        }
        initFrame()
    }

    private fun initFrame() {
        initComponent()
        layout = BorderLayout()
        size = Dimension(800, 500)
        isVisible = true
        defaultCloseOperation = EXIT_ON_CLOSE
        title = "KOTLINRX"
        add(side, BorderLayout.WEST)
        add(top, BorderLayout.NORTH)
        add(center, BorderLayout.CENTER)
    }

    fun initComponent() {
        top = JPanel()
        center = JPanel()
        side = JPanel()
        btnTest = JButton("CLICC")
        lbTest = JLabel("EMPTY")
        lb2 = JLabel("SADASDSD")
        initjtf()
        top!!.apply {
            layout = GridLayout(3, 3)
            preferredSize = Dimension(800, 50)
            background = hexColor("282828")
        }
        btnTest!!.addActionListener { e: ActionEvent? ->
            click++
            println("BTN CLICK $click")
            lbTest!!.text = "CHANGED YO $click"
        }
        center!!.apply {
            preferredSize = Dimension(750, 450)
            background = hexColor()
            add(lbTest)
            add(btnTest)
            add(lb2)
            add(jtf)
        }
        side!!.apply {
            preferredSize = Dimension(50, 500)
            background = hexColor("25344A")
        }
    }

    private fun initjtf() {
        jtf = JTextField()
        jtf!!.apply {
            preferredSize = Dimension(200, 30)
            document.addDocumentListener(object : DocumentListener {
                override fun insertUpdate(e: DocumentEvent) {
                    initObservable()
                }

                override fun removeUpdate(e: DocumentEvent) {
                    initObservable()
                }

                override fun changedUpdate(e: DocumentEvent) {

                }
            })
        }
    }

    private fun initObservable() {

        observable!!.subscribe(
            { item: String -> lb2!!.text = item },
            { ex: Throwable -> println(ex.message) }
        ) { println("COMPLETE") }.dispose()
    }
}
