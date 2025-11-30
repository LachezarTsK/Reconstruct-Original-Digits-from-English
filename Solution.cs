
using System;

public class Solution
{
    private static readonly int NUMBER_OF_DIGITS = 10;
    private static readonly int ALPHABET_SIZE = 26;

    private static readonly char[] keyLettersOrderedForUnambiguousQuery
            = { 'z', 'w', 'u', 'x', 'g', 'h', 's', 'v', 'i', 'n' };

    private static readonly string[] wordsOrderedForUnambiguousQuery
            = { "zero", "two", "four", "six", "eight", "three", "seven", "five", "nine", "one" };

    private static readonly int[] digitsOrderedForUnambiguousQuery
            = { 0, 2, 4, 6, 8, 3, 7, 5, 9, 1 };

    public string OriginalDigits(string input)
    {
        int[] frequencyLetters = CreateFrequencyLetters(input);
        int[] frequencyDigits = CreateFrequencyDigits(frequencyLetters);
        return CreateSortedOriginalDigits(frequencyDigits);
    }

    private int[] CreateFrequencyLetters(string input)
    {
        int[] frequencyLetters = new int[ALPHABET_SIZE];
        foreach (int letter in input)
        {
            ++frequencyLetters[letter - 'a'];
        }
        return frequencyLetters;
    }

    private int[] CreateFrequencyDigits(int[] frequencyLetters)
    {
        int[] frequencyDigits = new int[NUMBER_OF_DIGITS];
        for (int digit = 0; digit < NUMBER_OF_DIGITS; ++digit)
        {
            char keyLetter = keyLettersOrderedForUnambiguousQuery[digit];
            int frequencyKeyLetter = frequencyLetters[keyLetter - 'a'];
            if (frequencyKeyLetter == 0)
            {
                continue;
            }

            int orderedDigit = digitsOrderedForUnambiguousQuery[digit];
            frequencyDigits[orderedDigit] += frequencyKeyLetter;

            for (int i = 0; i < wordsOrderedForUnambiguousQuery[digit].Length; ++i)
            {
                char letter = wordsOrderedForUnambiguousQuery[digit][i];
                frequencyLetters[letter - 'a'] -= frequencyKeyLetter;
            }
        }
        return frequencyDigits;
    }

    private string CreateSortedOriginalDigits(int[] frequencyDigits)
    {
        StringBuilder sortedOriginalDigits = new();
        for (int digit = 0; digit < NUMBER_OF_DIGITS; ++digit)
        {
            while (frequencyDigits[digit] > 0)
            {
                sortedOriginalDigits.Append((char)('0' + digit));
                --frequencyDigits[digit];
            }
        }
        return sortedOriginalDigits.ToString();
    }
}
