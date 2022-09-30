package model
import helper.Extension.addSpace

data class People(
var name: String = "",
var age: Int = 0,
var gender: String = "",
var height: Float = 0F,
var address: String = "",
var contact: String = "",
){
    fun print() : String {
        return "${name.addSpace()}${age.addSpace()}${gender.addSpace()}" +
                "${height.addSpace()}${address.addSpace()}${contact.addSpace()}"
    }
}