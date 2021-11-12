package com.harman.parser

/**
 * Abstract base class Element
 */
abstract class Element(val text: String, val width: Int) {
    /**
     * if item is at the top of the hierarchy
     */
    var isMain = false

    companion object {
        private val regexPar = """paragraph\((.+)\)""".toRegex()
        private val regexASCII = """ASCIIart\((.+)\)\((.+)\)""".toRegex()
        private val regexTable = """table\((\d+)\)\((\d+)\)\((.+)\)""".toRegex()
        var string: String = ""

        /**
         * Fabric of Elements static method
         * @return specific type Element?
         */
        fun getElement(text: String, width: Int): Element? {
            val res: List<String>
            when {
                regexPar.matches(text) -> { res = regexPar.matchEntire(text)?.groupValues!!.subList(1, 2)
                    return Paragraph(res[0], width)}
                regexASCII.matches(text) -> { res = regexASCII.matchEntire(text)?.groupValues!!.subList(1,3)
                    return ASCII(res[1], res[0].toInt(), width)}
                regexTable.matches(text) -> { res = regexTable.matchEntire(text)?.groupValues!!.subList(1, 4)
                    return Table(res[2], res[0].toInt(), res[1].toInt(), width)}
                else -> return null
            }
        }

    }

    /**
     * Checking for the existence of the next iteration element method
     * @return Boolean
     */
    abstract fun hasNext(): Boolean

    /**
     * Next element of iteration method
     */
    abstract fun next()

    /**
     * final print line of element method
     */
    fun print() {
        println(string)
        string = ""
    }

}