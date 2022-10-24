package helper

import java.sql.Connection
import java.sql.DriverManager

object Connection {
    fun initializeConnectionToDB() : Connection {
        val jdbcUrl = "jdbc:mysql://localhost:3306/task3"
        val username = "root"
        val password = "root"
        return DriverManager.getConnection(jdbcUrl, username, password)
    }
}