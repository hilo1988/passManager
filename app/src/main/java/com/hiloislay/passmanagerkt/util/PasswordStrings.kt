package com.hiloislay.passmanagerkt.util

import org.apache.commons.lang3.RandomUtils
import java.util.*
import kotlin.collections.ArrayList

object PasswordStrings {

    /**
     * 大文字アルファベットリスト
     */
    private val LARGE_ALPHABET_LIST = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')

    /**
     * 小文字アルファベットリスト
     */
    private val SMALL_ALPHABET_LIST = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
    /**
     * 数字リスト
     */
    private val NUMBER_LIST = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

    /**
     * 記号リスト
     */
    private val SYMBOL_LIST = Arrays.asList('_', '-', '!', '&', '#', '$', '?', '%')


    /**
     * パスワード文字の取得(1文字)
     */
    fun generatePasswordStr(length: Int, isSmallAlpha: Boolean, isBigAlpha: Boolean, isNumber: Boolean, isSymbol: Boolean): String {

        val typeList = LetterType.getTypeList(isSmallAlpha, isBigAlpha, isNumber, isSymbol)
        val typeSize = typeList.size
        val sb = StringBuilder()

        for (i in 0..length) {
            val type = typeList[RandomUtils.nextInt(0, typeSize)]
            val list =
                    when (type) {
                        LetterType.BIG_ALPHABET -> {
                            LARGE_ALPHABET_LIST
                        }
                        LetterType.SYMBOL -> {
                            SYMBOL_LIST
                        }
                        LetterType.NUMBER -> {
                            NUMBER_LIST
                        }
                        LetterType.SMALL_ALPHABET -> {
                            SMALL_ALPHABET_LIST
                        }
                    }

            sb.append(list[RandomUtils.nextInt(0, list.size)])
        }
        return sb.toString()
    }


    private enum class LetterType {
        SMALL_ALPHABET,
        BIG_ALPHABET,
        NUMBER,
        SYMBOL;


        companion object {
            fun getTypeList(isSmallAlpha: Boolean,
                            isBigAlpha: Boolean,
                            isNumber: Boolean,
                            isSymbol: Boolean): List<LetterType> {

                val list = ArrayList<LetterType>()
                if (isSmallAlpha) {
                    list.add(SMALL_ALPHABET)
                }
                if (isBigAlpha) {
                    list.add(BIG_ALPHABET)
                }
                if (isNumber) {
                    list.add(NUMBER)
                }
                if (isSymbol) {
                    list.add(SYMBOL)
                }
                return list
            }
        }

    }

}