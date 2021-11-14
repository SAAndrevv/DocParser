package com.harman.parser

/**
 * Paragraph element class extends Element
 */
class Paragraph(text: String, width: Int): Element(text, width) {
    private val content: String
    private var isIterate = true
    var countWords = 0
        private set


    init {
        content = if (text.length > width)
            text.substring(0, width - 1)
        else
            text

        countWordsInArray()
    }

    /**
     * Count words in array method
     */
    private fun countWordsInArray() {
        val regexWord = """(\b[a-zA-Z]+\b)""".toRegex()

        countWords = content.split(" ", "-").count { regexWord.matches(it) }
    }

    /**
     * Checking for the existence of the next iteration element method
     * @return Boolean
     */
    override fun hasNext(): Boolean = isIterate

    /**
     * Next element of iteration method
     */
    override fun next() {
        isIterate = false

        when (isMain) {
            true -> { Element.string += content
                print() }
            false -> Element.string += content.padEnd(width - 1) + "|"

        }
    }

}