package helper

object Extension {
    fun Any.addSpace(space: Int = 20) : String {
        return String.format("%${space}s", this)
    }

}