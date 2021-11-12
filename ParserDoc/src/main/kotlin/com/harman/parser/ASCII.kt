package com.harman.parser


/**
 * ASCII element class extends Element
 */
class ASCII(text: String, lenghtString: Int, width: Int): Element(text, width) {
    private var content: List<String>
    private var listIterator: Iterator<String>
    private lateinit var str: String

    init {
        content = text.chunked(lenghtString)
        if (lenghtString > width) {
            content = content.map { it.substring(0, width - 1) }
        }

        listIterator = content.listIterator()
    }

    /**
     * Checking for the existence of the next iteration element method
     * @return Boolean
     */
    override fun hasNext(): Boolean = listIterator.hasNext()

    /**
     * Next element of iteration method
     */
    override fun next() {
        if (listIterator.hasNext()) {
            str = listIterator.next()

            when (isMain) {
                true -> { Element.string += str
                    print() }
                false -> Element.string += str.padEnd(width - 1) + "|"
            }
        }
    }

}