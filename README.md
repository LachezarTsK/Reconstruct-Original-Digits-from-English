# Reconstruct-Original-Digits-from-English
Challenge at LeetCode.com. Tags: Hash Table, Math, String, Counting Sort.

------------------------------------------------------------------------------------------

The problem statement guarantees that all letters in the input can be used and it requires that the solution uses all of them. Since some letters in the digits, expressed in words, are shared, the major task is to design the queries in such a way that at every step, the possibility for an ambiguous selection (query) is excluded.

- An ambiguous query, example

Input: “nenienonevesnine”, with letters ordered properly and words separated, it is “nine one seven nine”.

If the key letter that is used for the current query is “n”, it is not certain whether, for instance, there are only several “nine”, or only several “one”, or only several “seven”, or some combination of these? In other words, how many of these “n” do we take to form the current digit, at the current query. Remember that no letters can be left over, after running the program. We cannot simply take all 5 of “n” and assume, for instance, that there will be another 5 of “o” and 5 of “e” to form 5 of “one” and that some of the selected "n" will not be needed for some other digit(s) that share this letter. Or choose some other ambiguous letter, for another digit, with the similar assumption.

Of course, in this case the frequency of “o” and “e” (or whatever other letters, in general) can be checked but having in mind the size of the input, doing such checks at every step, will almost certainly result in exceeding the time limits for this problem. Or at best, it will result in a runtime, which is at least 10x slower than an optimized code. And this in addition to the spaghetti code that inevitably will result from doing these checks at every step.

 

- An unambiguous query, example

Input: “zooneerzeroorezzero”, with letters ordered properly and words separated, it is “zero one zero zero zero”.

If the key letter that is used for the current query is “z”, then we know that its frequency is 4. And the letter “z” is not shared among any other digits. So, we can be sure that we can also take 4 of “e”, 4 of “r” and 4 of “o” without the danger of taking more / less than needed of these letters for the current query. Thus, at the current query, we form 4 of “zero”.

Another key consideration is that a letter can be unambiguous either in absolute or in relative terms.

A letter is unambiguous in absolute terms, when it occurs only in one digit, in general. As an example, the letter “z” occurs only in “zero” and not in any other digit.

A letter is unambiguous in relative terms, when all the other digits that share this letter are already unambiguously selected and the remainder frequency of this letter occurs only in one remaining digit, still to be formed. As an example, let’s take the letter “n”, which is shared among “one”, “seven”, “nine” and “ten”. When all the other possibility for choosing “n” is already exhausted and we formed, for instance, unambiguously a certain number of “seven”, “nine” and “ten”, then we are sure that the remainder frequency of “n” will go only to form “one” and thus take the corresponding frequency of “e” and “o” to form the correct frequency of “one”, without the danger of taking more / less letters than needed for the current query.  

The first queries should always be those, where the letters are unambiguous in absolute terms. Only in this way, we can reach the stage where the selection of letters is based on being unambiguous in relative terms.

There are several possible arrangements of the key letters, based on the above considerations. One of these arrangements is applied for the solution presented here.
