package com.harman.parser

import com.harman.parser.extension.splitElements

/**
 * Table of Elements class extends Element
 */
class Table(text: String, lines: Int, column: Int, width: Int): Element(text, width) {
    private val table: ArrayList<ArrayList<Element>> = ArrayList()
    private val data: List<String>
    private var countIterableElement: Int
    private var lineIterator: Iterator<ArrayList<Element>>
    private var columnIterator: Iterator<Element>
    private lateinit var element: Element
    private var line: ArrayList<Element>
    private var isIterate = true
    private val widthColumn: Int
    private var delimiter = false


    init {
        if (lines > 1 && lines % 2 != 0 ||
                column > 1 && column % 2 != 0 ||
            (lines < 1 || column < 1)) {
            throw IllegalArgumentException("The number of rows or columns must be at least one " +
                    "and divisible by 2 without a remainder")
        }
        widthColumn = width / column
        data = text.splitElements()

        var iter = 0

        for (i in 0 until lines) {
            val lst = ArrayList<Element>()
            for (j in 0 until column) {
                try {
                    Element.getElement(data[iter], widthColumn)?.let { lst.add(it) }
                    iter++
                } catch (e: IndexOutOfBoundsException) {
                    throw IllegalArgumentException("Incorrect count of element")
                }

            }
            table.add(lst)
        }

        lineIterator = table.iterator()
        line = lineIterator.next()
        countIterableElement = line.count()
        columnIterator = line.iterator()
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
        if (isMain) Element.string = "|"

        while (columnIterator.hasNext() && countIterableElement != 0) {
            if (delimiter) {
                Element.string += "".padEnd(width - 1, '-') + "|"
                delimiter = false
                break
            }

            element = columnIterator.next()

            if (element.hasNext()) {
                element.next()

                if (!element.hasNext()) {
                    countIterableElement--
                }
            } else {
                Element.string += "".padEnd(widthColumn - 1) + "|"
            }
            if (countIterableElement == 0 && columnIterator.hasNext()) {
                while (columnIterator.hasNext()){
                    columnIterator.next()
                    Element.string += "".padEnd(widthColumn - 1) + "|"
                }
            }
        }

        if (isMain) print()

        if (countIterableElement != 0) {
            columnIterator = line.iterator()
        }
        else if (lineIterator.hasNext()) {
            line = lineIterator.next()
            countIterableElement = line.count()
            columnIterator = line.iterator()
            delimiter = true
        }
        else {
            isIterate = false
        }
    }

}