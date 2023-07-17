import data.database.BukuDao
import data.database.DatabaseConnection
import data.model.Buku
import java.awt.Dimension
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextField

class TesDb : JFrame() {
    lateinit var tfNama : JTextField
    lateinit var tfPenerbit : JTextField
    lateinit var tfId : JTextField
    lateinit var btnSave : JButton
    lateinit var panel : JScrollPane
    val c = DatabaseConnection.getConnection()

    init {
        initFrame()
        val stat = c.createStatement()
        val h = stat.executeQuery(BukuDao.getAll())
        while (h.next()) {
            val id = h.getInt(1)
            val nama = h.getString(2)
            val pen = h.getString(3)
            BukuDao.listBuku.add(Buku(id, nama, pen))
        }
        println(BukuDao.listBuku)
    }

    private fun initFrame() {
        initComponent()
        preferredSize = Dimension(800, 500)
        isVisible = true
        defaultCloseOperation = EXIT_ON_CLOSE
        title = "TES DB"
        add(panel)

    }
    private fun initComponent() {
        tfNama = JTextField()
        tfNama.apply {

        }

        tfId = JTextField()
        tfId.apply {

        }

        tfPenerbit = JTextField()
        tfPenerbit.apply {

        }

        btnSave = JButton()
        btnSave.apply {

        }

        panel = JScrollPane()
        panel.apply {
            preferredSize = Dimension(800, 400)
        }
    }
}