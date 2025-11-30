
/**
 * @param {string} input
 * @return {string}
 */
var originalDigits = function (input) {
    const frequencyLetters = createFrequencyLetters(input);
    const frequencyDigits = createFrequencyDigits(frequencyLetters);
    return createSortedOriginalDigits(frequencyDigits);
};

/**
 * @param {string} input
 * @return {number[]}
 */
function createFrequencyLetters(input) {
    const frequencyLetters = new Array(Util.ALPHABET_SIZE).fill(0);
    for (let i = 0; i < input.length; ++i) {
        const letter = input.codePointAt(i);
        ++frequencyLetters[letter - Util.ASCII_SMALL_CASE_A];
    }
    return frequencyLetters;
}

/**
 * @param {number[]} frequencyLetters
 * @return {number[]}
 */
function createFrequencyDigits(frequencyLetters) {
    const frequencyDigits = new Array(Util.NUMBER_OF_DIGITS).fill(0);
    for (let digit = 0; digit < Util.NUMBER_OF_DIGITS; ++digit) {

        const keyLetter = Util.keyLettersOrderedForUnambiguousQuery[digit];
        const frequencyKeyLetter = frequencyLetters[keyLetter - Util.ASCII_SMALL_CASE_A];
        if (frequencyKeyLetter === 0) {
            continue;
        }

        const orderedDigit = Util.digitsOrderedForUnambiguousQuery[digit];
        frequencyDigits[orderedDigit] += frequencyKeyLetter;

        for (let i = 0; i < Util.wordsOrderedForUnambiguousQuery[digit].length; ++i) {
            const letter = Util.wordsOrderedForUnambiguousQuery[digit].codePointAt(i);
            frequencyLetters[letter - Util.ASCII_SMALL_CASE_A] -= frequencyKeyLetter;
        }
    }
    return frequencyDigits;
}

/**
 * @param {number[]} frequencyDigits
 * @return {string}
 */
function createSortedOriginalDigits(frequencyDigits) {
    const sortedOriginalDigits = new Array();
    for (let digit = 0; digit < Util.NUMBER_OF_DIGITS; ++digit) {
        while (frequencyDigits[digit] > 0) {
            sortedOriginalDigits.push(digit);
            --frequencyDigits[digit];
        }
    }
    return sortedOriginalDigits.join('');
}

class Util {
    static NUMBER_OF_DIGITS = 10;
    static ALPHABET_SIZE = 26;
    static ASCII_SMALL_CASE_A = 'a'.codePointAt(0);

    static keyLettersOrderedForUnambiguousQuery
            = ['z'.codePointAt(0), 'w'.codePointAt(0), 'u'.codePointAt(0), 'x'.codePointAt(0), 'g'.codePointAt(0),
               'h'.codePointAt(0), 's'.codePointAt(0), 'v'.codePointAt(0), 'i'.codePointAt(0), 'n'.codePointAt(0)];

    static wordsOrderedForUnambiguousQuery
            = ["zero", "two", "four", "six", "eight", "three", "seven", "five", "nine", "one"];

    static digitsOrderedForUnambiguousQuery
            = [0, 2, 4, 6, 8, 3, 7, 5, 9, 1];
}
