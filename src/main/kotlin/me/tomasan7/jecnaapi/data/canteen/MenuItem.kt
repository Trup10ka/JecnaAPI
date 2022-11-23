package me.tomasan7.jecnaapi.data.canteen

data class MenuItem(
    val description: ItemDescription?,
    val allergens: List<String>? = null,
    val price: Float,
    val enabled: Boolean,
    val ordered: Boolean,
    var orderPath: String,
    var putOnExchangePath: String? = null,
)
{
    /**
     * Updates the [orderPath] and possibly [putOnExchangePath] with the new [time].
     */
    fun updateTime(time: Long)
    {
       orderPath = orderPath.replace(TIME_REPLACE_REGEX, time.toString())
       putOnExchangePath = putOnExchangePath?.replace(TIME_REPLACE_REGEX, time.toString())
    }

    companion object
    {
        private val TIME_REPLACE_REGEX = Regex("""(?<=time=)\d{13}""")
    }
}