
#include <span>
#include <array>
#include <vector>
#include <ranges>
using namespace std;

class Solution {

    static const int NUMBER_OF_DIGITS = 10;
    static const int ALPHABET_SIZE = 26;

    inline static const array<char, NUMBER_OF_DIGITS> keyLettersOrderedForUnambiguousQuery
            = { 'z', 'w', 'u', 'x', 'g', 'h', 's', 'v', 'i', 'n' };

    inline static const array<const string, NUMBER_OF_DIGITS> wordsOrderedForUnambiguousQuery
            = { "zero", "two", "four", "six", "eight", "three", "seven", "five", "nine", "one" };

    inline static const array<int, NUMBER_OF_DIGITS> digitsOrderedForUnambiguousQuery
            = { 0, 2, 4, 6, 8, 3, 7, 5, 9, 1 };

public:
    string originalDigits(const string& input) const {
        array<int, ALPHABET_SIZE> frequencyLetters = createFrequencyLetters(input);
        array<int, NUMBER_OF_DIGITS> frequencyDigits = createFrequencyDigits(frequencyLetters);
        return createSortedOriginalDigits(frequencyDigits);
    }

private:
    array<int, ALPHABET_SIZE> createFrequencyLetters(const string& input) const {
        array<int, ALPHABET_SIZE> frequencyLetters{};
        for (const auto& letter : input) {
            ++frequencyLetters[letter - 'a'];
        }
        return frequencyLetters;
    }

    array<int, NUMBER_OF_DIGITS> createFrequencyDigits(span<int> frequencyLetters) const {
        array<int, NUMBER_OF_DIGITS> frequencyDigits{};
        for (int digit = 0; digit < NUMBER_OF_DIGITS; ++digit) {

            char keyLetter = keyLettersOrderedForUnambiguousQuery[digit];
            int frequencyKeyLetter = frequencyLetters[keyLetter - 'a'];
            if (frequencyKeyLetter == 0) {
                continue;
            }

            int orderedDigit = digitsOrderedForUnambiguousQuery[digit];
            frequencyDigits[orderedDigit] += frequencyKeyLetter;

            for (int i = 0; i < wordsOrderedForUnambiguousQuery[digit].length(); ++i) {
                char letter = wordsOrderedForUnambiguousQuery[digit][i];
                frequencyLetters[letter - 'a'] -= frequencyKeyLetter;
            }
        }
        return frequencyDigits;
    }

    string createSortedOriginalDigits(span<int> frequencyDigits) const {
        string sortedOriginalDigits;
        for (int digit = 0; digit < NUMBER_OF_DIGITS; ++digit) {
            while (frequencyDigits[digit] > 0) {
                sortedOriginalDigits.push_back('0' + digit);
                --frequencyDigits[digit];
            }
        }
        return sortedOriginalDigits;
    }
};
