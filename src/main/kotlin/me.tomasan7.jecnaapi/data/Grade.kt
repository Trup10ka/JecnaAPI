package me.tomasan7.jecnaapi.data

import java.time.LocalDate

/**
 * @property value       Grade's value. Is 0 for N.
 * @property small       Whether the grade is small or big. (it's weight)
 * @property subject     The subject th grade is from.
 * @property teacher     The teacher, who gave you the grade.
 * @property description Description of the grade.
 */
data class Grade(val value: Int,
                 val small: Boolean,
                 val subject: String? = null,
                 val teacher: String? = null,
                 val description: String? = null,
                 val receiveDate: LocalDate? = null)
{
    /**
     * Accepts value's representing char.
     *
     * @param valueChar The value's char representation.
     */
    constructor(valueChar: Char,
                small: Boolean,
                subject: String?,
                teacher: String?,
                description: String?,
                receiveDate: LocalDate?) : this(valueCharToValue(valueChar),
                                                small,
                                                subject,
                                                teacher,
                                                description,
                                                receiveDate)

    init
    {
        require(value in 0..5) { "Grade value must be between 0 and 5. (got $value)" }
    }

    /**
     * @return This grade's [.value] as char. Returns `'N'` for value `0`.
     */
    fun valueChar() = if (value == 0) 'N' else value.toString()[0]

    companion object
    {
        /**
         * Converts value's char representation to the value.
         *
         * @param valueChar The value's char representation.
         * @return It's value.
         */
        private fun valueCharToValue(valueChar: Char): Int
        {
            require(valueChar in listOf('N', '0', '1', '2', '3', '4', '5'))

            return if (valueChar == 'N') 0 else valueChar.toString().toInt()
        }
    }
}