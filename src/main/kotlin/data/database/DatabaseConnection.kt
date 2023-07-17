package data.database

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DatabaseConnection {

    companion object {
        fun getConnection() : Connection {
            val url = "jdbc:mysql://localhost:3306/perpus?user=root&password=root"
            try {
                Class.forName("com.mysql.cj.jdbc.Driver")
                println("connected")

            } catch (e : SQLException) {
                println("1st ${e.message}")
            }
            try {
                println("CONECCTED")
            } catch (e : SQLException) {
                println("2nd ${e.message}")
            }

            return DriverManager.getConnection(url)
        }
    }
}