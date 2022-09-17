package me.tomasan7.jecnaapi.data.grade

import me.tomasan7.jecnaapi.util.Name
import me.tomasan7.jecnaapi.util.toName

/**
 * Representing grades table.
 * Stores `0` or more grades for each subject.
 */
class GradesPage private constructor(
    private val subjectsMap: Map<Name, Subject>,
    val behaviour: Behaviour
)
{
    /** All subject names. */
    val subjectNames = subjectsMap.keys

    /**
     * All subjects.
     */
    val subjects = subjectsMap.values

    /**
     * @return [Subject] with the passed [Name]. Can be `null`, when theres no subject with that name.
     */
    fun getSubjectByName(subjectName: Name) = subjectsMap.getOrDefault(subjectName, null)

    /**
     * @return [Subject] with the passed [subjectName] as it's full name. Can be `null`, when theres no subject with that name.
     */
    fun getSubjectByName(subjectName: String) = getSubjectByName(subjectName.toName())

    /**
     * @return [Subject] with the passed [Name]. Can be `null`, when theres no subject with that name.
     * @see [getSubjectByName]
     */
    operator fun get(subjectName: Name) = getSubjectByName(subjectName)

    /**
     * @return [Subject] with the passed [subjectName] as it's full name. Can be `null`, when theres no subject with that name.
     * @see [getSubjectByName]
     */
    operator fun get(subjectName: String) = getSubjectByName(subjectName)

    /**
     * This [GradesPage] as a [Map].
     * Key = subject [Name], value = [Subject].
     */
    val asMap = subjectsMap

    class Builder
    {
        private val subjects: MutableMap<Name, Subject> = HashMap()
        private lateinit var behaviour: Behaviour

        /**
         * Adds [Subject].
         * @param subject The subject to add.
         * @return This [Builder] instance back.
         */
        fun addSubject(subject: Subject): Builder
        {
            subjects[subject.name] = subject
            return this
        }

        fun setBehaviour(behaviour: Behaviour)
        {
            this.behaviour = behaviour
        }

        fun build(): GradesPage
        {
            check(::behaviour.isInitialized) { "Behaviour has not been set." }
            return GradesPage(subjects, behaviour)
        }
    }

    companion object
    {
        fun builder() = Builder()
    }
}