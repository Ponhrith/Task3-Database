import helper.Connection
import model.People
import screen.UserScreen
import java.io.File


class Main {
    companion object{
        @JvmStatic
        fun main(args : Array<String>){
            val con = Connection.initializeConnectionToDB()
            val people = People()
            val userScreen = UserScreen()
            userScreen.menu(con, people)
            println("===> Application has shut down")
        }
    }
}