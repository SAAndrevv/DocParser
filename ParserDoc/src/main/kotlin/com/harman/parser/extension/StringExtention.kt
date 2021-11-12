package com.harman.parser.extension


import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList

/**
 * String complement function to split elements
 * @return List<String>
 */
fun String.splitElements(regex: Char = ','): List<String> {
    val stack =  ArrayDeque<Char>()
    val arr = ArrayList<String>()
    val buff = StringBuffer()

    fun appendToList(){
        if (buff.isNotEmpty())
            buff.deleteCharAt(buff.length - 1)

        arr.add(buff.toString())
        buff.delete(0, buff.length)

    }
    for (c in this) {
        buff.append(c)
        when (c) {
            '(' -> stack.addLast('(')
            ')' -> if (!stack.isEmpty()) stack.removeLast()
            regex -> if (stack.isEmpty()) appendToList()
        }
    }

    arr.add(buff.toString())

    return arr
}
