package com.yoidukigembu.passmanager.utils

import java.util.ArrayList
import java.util.Collections

object PasswordStrings {

    /**
     * 大文字アルファベットリスト
     */
    val LARGE_ALPHABET_LIST: List<String>
    /**
     * 小文字アルファベットリスト
     */
    val SMALL_ALPHABET_LIST: List<String>
    /**
     * 数字リスト
     */
    val NUMBER_LIST: List<String>
    /**
     * 大文字アルファベットリスト最大インデックス
     */
    val MAX_LARGE_ALPHABET_INDEX: Int
    /**
     * 小文字アルファベットリスト最大インデックス
     */
    val MAX_SMALL_ALPHABET_INDEX: Int
    /**
     * 数字アルファベットリスト最大インデックス
     */
    val MAX_NUMBER_INDEX: Int

    /**
     * パスワード文字の取得(1文字)
     */
    fun generatePasswordStr(): String {
        val random = Math.floor(Math.random() * 300).toInt()
        if (random < 125) {
            return generateLargeAlphabet()
        }

        return if (random < 250) {
            generateSmallAlphabet()
        } else generateNumber()

    }


    /**
     * 大文字アルファベットの取得(1文字)
     */
    private fun generateLargeAlphabet(): String {
        while (true) {
            val random = Math.floor(Math.random() * 40).toInt()
            if (random < MAX_LARGE_ALPHABET_INDEX) {
                return LARGE_ALPHABET_LIST[random]
            }
        }
    }

    /**
     * 小文字アルファベットの取得(1文字)
     */
    private fun generateSmallAlphabet(): String {
        while (true) {
            val random = Math.floor(Math.random() * 40).toInt()
            if (random < MAX_SMALL_ALPHABET_INDEX) {
                return SMALL_ALPHABET_LIST[random]
            }
        }
    }

    /**
     * 数字の取得(1文字)
     */
    private fun generateNumber(): String {
        while (true) {
            val random = Math.floor(Math.random() * 10).toInt()
            if (random < MAX_NUMBER_INDEX) {
                return NUMBER_LIST[random]
            }
        }
    }

    init {
        val largeList = ArrayList<String>()
        largeList.add("A")
        largeList.add("B")
        largeList.add("C")
        largeList.add("D")
        largeList.add("E")
        largeList.add("F")
        largeList.add("G")
        largeList.add("H")
        largeList.add("J")
        largeList.add("K")
        largeList.add("L")
        largeList.add("M")
        largeList.add("N")
        largeList.add("P")
        largeList.add("Q")
        largeList.add("R")
        largeList.add("S")
        largeList.add("T")
        largeList.add("U")
        largeList.add("V")
        largeList.add("W")
        largeList.add("X")
        largeList.add("Y")
        largeList.add("Z")

        val smallList = ArrayList<String>()
        smallList.add("a")
        smallList.add("b")
        smallList.add("c")
        smallList.add("d")
        smallList.add("e")
        smallList.add("f")
        smallList.add("g")
        smallList.add("h")
        smallList.add("j")
        smallList.add("k")
        smallList.add("m")
        smallList.add("n")
        smallList.add("p")
        smallList.add("q")
        smallList.add("r")
        smallList.add("s")
        smallList.add("t")
        smallList.add("u")
        smallList.add("v")
        smallList.add("w")
        smallList.add("x")
        smallList.add("y")
        smallList.add("z")

        val numberList = ArrayList<String>()
        numberList.add("2")
        numberList.add("3")
        numberList.add("4")
        numberList.add("5")
        numberList.add("6")
        numberList.add("7")
        numberList.add("8")
        numberList.add("9")

        LARGE_ALPHABET_LIST = Collections.unmodifiableList(largeList)
        SMALL_ALPHABET_LIST = Collections.unmodifiableList(smallList)
        NUMBER_LIST = Collections.unmodifiableList(numberList)

        MAX_LARGE_ALPHABET_INDEX = LARGE_ALPHABET_LIST.size
        MAX_SMALL_ALPHABET_INDEX = SMALL_ALPHABET_LIST.size
        MAX_NUMBER_INDEX = NUMBER_LIST.size
    }
}