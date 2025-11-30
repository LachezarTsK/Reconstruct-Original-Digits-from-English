
class Solution {

    private companion object {
        const val NUMBER_OF_DIGITS = 10
        const val ALPHABET_SIZE = 26

        val keyLettersOrderedForUnambiguousQuery =
            charArrayOf('z', 'w', 'u', 'x', 'g', 'h', 's', 'v', 'i', 'n')

        val wordsOrderedForUnambiguousQuery =
            arrayOf("zero", "two", "four", "six", "eight", "three", "seven", "five", "nine", "one")

        val digitsOrderedForUnambiguousQuery =
            intArrayOf(0, 2, 4, 6, 8, 3, 7, 5, 9, 1)
    }

    fun originalDigits(input: String): String {
        val frequencyLetters = createFrequencyLetters(input)
        val frequencyDigits = createFrequencyDigits(frequencyLetters)
        return createSortedOriginalDigits(frequencyDigits)
    }

    private fun createFrequencyLetters(input: String): IntArray {
        val frequencyLetters = IntArray(ALPHABET_SIZE)
        for (letter in input) {
            ++frequencyLetters[letter - 'a']
        }
        return frequencyLetters
    }

    private fun createFrequencyDigits(frequencyLetters: IntArray): IntArray {
        val frequencyDigits = IntArray(NUMBER_OF_DIGITS)
        for (digit in 0..<NUMBER_OF_DIGITS) {

            val keyLetter = keyLettersOrderedForUnambiguousQuery[digit]
            val frequencyKeyLetter = frequencyLetters[keyLetter - 'a']
            if (frequencyKeyLetter == 0) {
                continue
            }

            val orderedDigit = digitsOrderedForUnambiguousQuery[digit]
            frequencyDigits[orderedDigit] += frequencyKeyLetter

            for (i in 0..<wordsOrderedForUnambiguousQuery[digit].length) {
                val letter = wordsOrderedForUnambiguousQuery[digit][i]
                frequencyLetters[letter - 'a'] -= frequencyKeyLetter
            }
        }
        return frequencyDigits
    }

    private fun createSortedOriginalDigits(frequencyDigits: IntArray): String {
        val sortedOriginalDigits = StringBuilder()
        for (digit in 0..<NUMBER_OF_DIGITS) {
            while (frequencyDigits[digit] > 0) {
                sortedOriginalDigits.append(digit)
                --frequencyDigits[digit]
            }
        }
        return sortedOriginalDigits.toString()
    }
}
