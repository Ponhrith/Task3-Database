package screen

import model.People
import model.Options
import java.sql.Connection
import java.util.Scanner
import java.io.File


class UserScreen {
    private val people = mutableListOf<People>()
    private val tableName = "people"
    fun menu(connection: Connection, person: People){
        val option = Options()
        println("1. Click (1) to read user's info.")
        println("2. Click (2) to insert user's info.")
        println("3. Click (3) to search user's info.")
        println("4. Click (4) to update user's info.")
        option.userChoice = readLine() ?: "1"
        when (option.userChoice) {
            "1" ->{
                readUserInfo(connection, person)
                backToMenu(connection, person)
            }
            "2" ->{
                insertUserInfo(connection, person)
                backToMenu(connection,person)
            }
            "3" ->{
                searchUserInfo(connection, person)
                backToMenu(connection, person)
            }
            "4" ->{
                updateUserInfo(connection, person)
                backToMenu(connection, person)
            }

        }
    }

    private fun readUserInfo(connection: Connection, person: People){


        val stringBuilder = StringBuilder()
        "Name,Age,Gender,Height,Address,Contact".split(",").forEach{item ->
            stringBuilder.append(String.format("%20s", item))
        }
        println(stringBuilder)
        val sql = "SELECT * FROM $tableName"
        val rs = connection.createStatement().executeQuery(sql)
        while (rs.next()) {
            val name = rs.getString("name")
            val age = rs.getInt("age")
            val gender = rs.getString("gender")
            val height = rs.getFloat("height")
            val address = rs.getString("address")
            val contact = rs.getString("contact")

            people.add(People(name,age,gender,height,address,contact))

        }
        people.forEach{
            println(it.print())
        }
    }

    private fun insertUserInfo(connection: Connection, person: People){

        print("Please enter your name: ")
        person.name = readLine() ?: "Default Name"
        print("Please enter your age: ")
        person.age = People.getAgeFromInput()
        print("Please enter your gender: ")
        person.gender = readLine() ?: "Default Gender"
        print("Please enter your height: ")
        person.height = People.getHeightFromInput()
        print("Please enter your address: ")
        person.address = readLine() ?: "Default Address"
        print("Please enter your contact: ")
        person.contact = readLine() ?: "Default Contact"

        insertData(connection, person)

    }

    private fun searchUserInfo(connection: Connection, person: People){
        val option = Options()
        println(
            "-> Search user by:\n" +
                    "1. Name\n" +
                    "2. Age\n" +
                    "3. Gender\n" +
                    "4. Height\n" +
                    "5. Address\n" +
                    "6. Contact"
        )
        option.searchUser = readLine() ?: "1"
        print("-> Please input query: ")
        val query = readLine() ?: ""
        val sql  = when (option.searchUser) {
            "1" -> "SELECT * FROM $tableName WHERE name LIKE '%$query%'"
            "2" -> "SELECT * FROM $tableName WHERE age = $query"
            "3" -> "SELECT * FROM $tableName WHERE gender = '$query'"
            "4" -> "SELECT * FROM $tableName WHERE height = $query"
            "5" -> "SELECT * FROM $tableName WHERE address LIKE '%$query%'"
            "6" -> "SELECT * FROM $tableName WHERE contact LIKE '%$query%'"
            else -> "SELECT * FROM $tableName"
        }
        val rs = connection.createStatement().executeQuery(sql)
        println("Found People:")
        while (rs.next()) {
            val name = rs.getString("name")
            val age = rs.getInt("age")
            val gender = rs.getString("gender")
            val height = rs.getFloat("height")
            val address = rs.getString("address")
            val contact = rs.getString("contact")

            people.add(People(name,age,gender,height,address,contact))

        }
        people.forEach{
            println(it.print())
        }

    }
    private fun updateUserInfo(connection: Connection, person: People){
        val option = Options()
        println(
            "-> Update user by:\n" +
                    "1. Name\n" +
                    "2. Age\n" +
                    "3. Gender\n" +
                    "4. Height\n" +
                    "5. Address\n" +
                    "6. Contact"
        )
        option.searchUser = readLine() ?: "1"
        val idNum = Scanner (System.`in`)
        println("-> Please enter your ID number: ")
        val id:Int = idNum.nextInt()
        print("-> Please input the update query: ")
        val query = readLine() ?: ""
        val sql  = when (option.searchUser) {
            "1" -> "UPDATE $tableName SET name = '$query' WHERE id = $id"
            "2" -> "UPDATE $tableName SET age = $query WHERE id = $id"
            "3" -> "UPDATE $tableName SET gender = '$query' WHERE id = $id"
            "4" -> "UPDATE $tableName SET height = $query WHERE id = $id"
            "5" -> "UPDATE $tableName SET address = '$query' WHERE id = $id"
            "6" -> "UPDATE $tableName SET contact = '$query' WHERE id = $id"
            else -> "SELECT * FROM $tableName"
        }
        val rs = connection.createStatement().executeUpdate(sql)
        println("===> Data already updated!")

    }

    private fun backToMenu(connection: Connection, person: People){
        print("Press 'q' to quit and press any key to go back to main menu: ")
        when (readLine() ?: "y"){
            "q"->{
                return
            }
            else -> {
                menu(connection, person)
            }
        }
    }

    private fun insertData(connection: Connection, person: People) {
        val sql = "INSERT INTO $tableName(name, age, gender, height, address, contact) VALUES('${person.name}', ${person.age}, '${person.gender}',${person.height}, '${person.address}', '${person.contact}' )"
        val rs = connection.createStatement().executeUpdate(sql)

    }
}