
package main
import "strings"

const NUMBER_OF_DIGITS = 10
const ALPHABET_SIZE = 26

var keyLettersOrderedForUnambiguousQuery = 
     []byte{'z', 'w', 'u', 'x', 'g', 'h', 's', 'v', 'i', 'n'}

var wordsOrderedForUnambiguousQuery = 
     []string{"zero", "two", "four", "six", "eight", "three", "seven", "five", "nine", "one"}

var digitsOrderedForUnambiguousQuery = 
     []int{0, 2, 4, 6, 8, 3, 7, 5, 9, 1}

func originalDigits(input string) string {
    frequencyLetters := createFrequencyLetters(input)
    frequencyDigits := createFrequencyDigits(frequencyLetters)
    return createSortedOriginalDigits(frequencyDigits)
}

func createFrequencyLetters(input string) []int {
    frequencyLetters := make([]int, ALPHABET_SIZE)
    for i := range input {
        letter := input[i]
        frequencyLetters[letter - 'a']++
    }
    return frequencyLetters
}

func createFrequencyDigits(frequencyLetters []int) []int {
    frequencyDigits := make([]int, NUMBER_OF_DIGITS)
    for digit := range NUMBER_OF_DIGITS {

        keyLetter := keyLettersOrderedForUnambiguousQuery[digit]
        frequencyKeyLetter := frequencyLetters[keyLetter - 'a']
        if frequencyKeyLetter == 0 {
            continue
        }

        orderedDigit := digitsOrderedForUnambiguousQuery[digit]
        frequencyDigits[orderedDigit] += frequencyKeyLetter

        for i := range wordsOrderedForUnambiguousQuery[digit] {
            letter := wordsOrderedForUnambiguousQuery[digit][i]
            frequencyLetters[letter - 'a'] -= frequencyKeyLetter
        }
    }
    return frequencyDigits
}

func createSortedOriginalDigits(frequencyDigits []int) string {
    sortedOriginalDigits := strings.Builder{}
    for digit := range NUMBER_OF_DIGITS {
        for frequencyDigits[digit] > 0 {
            sortedOriginalDigits.WriteByte(byte('0' + digit))
            frequencyDigits[digit]--
        }
    }
    return sortedOriginalDigits.String()
}
