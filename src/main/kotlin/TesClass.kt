import Util.hexColor
import data.database.BukuDao
import data.database.DatabaseConnection
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import data.model.Buku
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class TesClass : JFrame(), ActionListener {
    private lateinit var side: JPanel
    private lateinit var top: JPanel
    private lateinit var center: JPanel
    private lateinit var btnTest: JButton
    private lateinit var lbTest: JLabel
    private lateinit var lb2: JLabel
    private var click = 0
    private var observable: Observable<String>
    private lateinit var jtf: JTextField
    val c = DatabaseConnection.getConnection()
    var id : Int

    init {
        val h = c.createStatement().executeQuery(BukuDao.getAll())
        while (h.next()) {
            val id = h.getInt(1)
            val nama = h.getString(2)
            val pen = h.getString(3)
            BukuDao.listBuku.add(Buku(id, nama, pen))
        }
        id = BukuDao.listBuku.last().idbuku
        observable = Observable.create { it: ObservableEmitter<String> ->
            it.onNext(
                jtf.text
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
        top.apply {
            layout = GridLayout(3, 3)
            preferredSize = Dimension(800, 50)
            background = hexColor("282828")
        }
        btnTest.addActionListener(this)
        center.apply {
            preferredSize = Dimension(750, 450)
            background = hexColor()
            add(lbTest)
            add(btnTest)
            add(lb2)
            add(jtf)
        }
        side.apply {
            preferredSize = Dimension(50, 500)
            background = hexColor("25344A")
        }
    }

    private fun initjtf() {
        jtf = JTextField()
        jtf.apply {
            preferredSize = Dimension(200, 30)
            document.addDocumentListener(object : DocumentListener {
                override fun insertUpdate(e: DocumentEvent) {
                    initObserver()
                }

                override fun removeUpdate(e: DocumentEvent) {
                    initObserver()
                }

                override fun changedUpdate(e: DocumentEvent) {

                }
            })
        }
    }

    private fun initObserver() {

        observable.subscribe(
            { item: String -> lb2.text = item },
            { ex: Throwable -> println(ex.message) }
        ) { println("COMPLETE") }.dispose()
    }


    override fun actionPerformed(e: ActionEvent?) {
        if (e != null) {
            when (e.source) {
                btnTest-> {
                    click++
                    println("BTN CLICK $click")

                    try {
                        id++
                        val x = BukuDao.insert(Buku(id, jtf.text, "penerbit $id"))
                        println(x)
                        c.createStatement().execute(x)
                        val h = c.createStatement().executeQuery(BukuDao.getAll())
                        while (h.next()) {
                            val id = h.getInt(1)
                            val nama = h.getString(2)
                            val pen = h.getString(3)
                            BukuDao.listBuku.add(Buku(id, nama, pen))
                        }
                        val b = BukuDao.listBuku.last()
                        println(b)
                        lbTest.text = "${b.idbuku}, ${b.namabuku}, ${b.penerbit}"
                    } catch (e : Exception) {
                        println(e.printStackTrace())
                    }

                }
            }
        }
    }
}
