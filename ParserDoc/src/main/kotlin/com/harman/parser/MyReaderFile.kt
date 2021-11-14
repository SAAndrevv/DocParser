package com.harman.parser

import java.io.File

/**
 * Read and print file class
 * Work only with even tables!!!
 */
class MyReaderFile(path: String, private var width: Int) {
    private val text: String
    private val elements: ArrayList<Element?> = ArrayList()
    private var countWords = 0

    init {
        //converting a number to a power of two
        if (width > 0 && width and (width - 1) != 0) {
            var num = 1
            while (width > num) {
                num = num shl 1
            }
            width = num shr 1

        } else if (width <= 0) {
            width = 64
        }

        val buff = File(path).bufferedReader()
        text = buff.use { it.readText() }
        convertTextToPrint()
    }

    /**
     * Convert text on List of elements method
     */
    private fun convertTextToPrint() {
        val data = text.split(";").map { it.trim() }
        for (d in data) {
            val element = Element.getElement(d, width)
            element?.isMain = true

            elements.add(element)

        }
    }

    /**
     * print elements method
     */
    fun print() {
        println()
        for (element in elements) {
            if (element != null) {
                while (element.hasNext()) {
                    element.next()
                }
                when (element) {
                    is Table -> countWords += element.countWordsInTable
                    is Paragraph -> countWords += element.countWords
                }

            }
        }
        println("\nCount of words in file $countWords")

    }

}