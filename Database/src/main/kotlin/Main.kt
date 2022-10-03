import model.People
import java.sql.DriverManager


fun main(){
    val jdbcUrl = "jdbc:mysql://localhost:3306/task3"

    val connection = DriverManager.getConnection(jdbcUrl, "root", "root")
    val query = connection.prepareStatement("SELECT * FROM people")
    val result = query.executeQuery()
    val people = mutableListOf<People>()

    val stringBuilder = StringBuilder()
    "Name,Age,Gender,Height,Address,Contact".split(",").forEach{item ->
        stringBuilder.append(String.format("%20s", item))
    }
    println(stringBuilder)
    while (result.next()) {
        val name = result.getString("name")
        val age = result.getInt("age")
        val gender = result.getString("gender")
        val height = result.getFloat("height")
        val address = result.getString("address")
        val contact = result.getString("contact")

        people.add(People(name,age,gender,height,address,contact))

    }
    people.forEach{
        println(it.print())
    }


}