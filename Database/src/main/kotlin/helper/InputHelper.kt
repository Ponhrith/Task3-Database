package helper


object InputHelper {
    fun getIntFromInput(fieldName: String) : Int {
        return kotlin.runCatching {
            readLine()?.toInt() ?: 0
        }.onFailure {
            print("Invalid input $fieldName, please input $fieldName again: ")
        }.getOrElse { getIntFromInput(fieldName) }
    }

    fun getFloatFromInput(fieldName: String) : Float {
        return kotlin.runCatching{
            readLine()?.toFloat() ?: 0F
        }.onFailure{
            print("Invalid input $fieldName, please input the $fieldName again: ")
        }.getOrElse{ getFloatFromInput(fieldName) }
    }


}