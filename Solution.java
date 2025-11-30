
public class Solution {

    private static final int NUMBER_OF_DIGITS = 10;
    private static final int ALPHABET_SIZE = 26;

    private static final char[] keyLettersOrderedForUnambiguousQuery
            = {'z', 'w', 'u', 'x', 'g', 'h', 's', 'v', 'i', 'n'};

    private static final String[] wordsOrderedForUnambiguousQuery
            = {"zero", "two", "four", "six", "eight", "three", "seven", "five", "nine", "one"};

    private static final int[] digitsOrderedForUnambiguousQuery
            = {0, 2, 4, 6, 8, 3, 7, 5, 9, 1};

    public String originalDigits(String input) {
        int[] frequencyLetters = createFrequencyLetters(input);
        int[] frequencyDigits = createFrequencyDigits(frequencyLetters);
        return createSortedOriginalDigits(frequencyDigits);
    }

    private int[] createFrequencyLetters(String input) {
        int[] frequencyLetters = new int[ALPHABET_SIZE];
        for (int letter : input.toCharArray()) {
            ++frequencyLetters[letter - 'a'];
        }
        return frequencyLetters;
    }

    private int[] createFrequencyDigits(int[] frequencyLetters) {
        int[] frequencyDigits = new int[NUMBER_OF_DIGITS];
        for (int digit = 0; digit < NUMBER_OF_DIGITS; ++digit) {

            char keyLetter = keyLettersOrderedForUnambiguousQuery[digit];
            int frequencyKeyLetter = frequencyLetters[keyLetter - 'a'];
            if (frequencyKeyLetter == 0) {
                continue;
            }

            int orderedDigit = digitsOrderedForUnambiguousQuery[digit];
            frequencyDigits[orderedDigit] += frequencyKeyLetter;

            for (int i = 0; i < wordsOrderedForUnambiguousQuery[digit].length(); ++i) {
                char letter = wordsOrderedForUnambiguousQuery[digit].charAt(i);
                frequencyLetters[letter - 'a'] -= frequencyKeyLetter;
            }
        }
        return frequencyDigits;
    }

    private String createSortedOriginalDigits(int[] frequencyDigits) {
        StringBuilder sortedOriginalDigits = new StringBuilder();
        for (int digit = 0; digit < NUMBER_OF_DIGITS; ++digit) {
            sortedOriginalDigits.repeat((char) ('0' + digit), frequencyDigits[digit]);
        }
        return sortedOriginalDigits.toString();
    }
}
