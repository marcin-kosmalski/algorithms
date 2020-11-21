Approach 1: Dynamic Programming + Counting
Intuition

First, call a positive integer X valid if X <= N and X only consists of digits from D. Our goal is to find the number of valid integers.

Say N has K digits. If we write a valid number with k digits (k < K), then there are (D\text{.length})^k(D.length) 
k
  possible numbers we could write, since all of them will definitely be less than N.

Now, say we are to write a valid K digit number from left to right. For example, N = 2345, K = 4, and D = '1', '2', ..., '9'. Let's consider what happens when we write the first digit.

If the first digit we write is less than the first digit of N, then we could write any numbers after, for a total of (D\text{.length})^{K-1}(D.length) 
K−1
  valid numbers from this one-digit prefix. In our example, if we start with 1, we could write any of the numbers 1111 to 1999 from this prefix.

If the first digit we write is the same, then we require that the next digit we write is equal to or lower than the next digit in N. In our example (with N = 2345), if we start with 2, the next digit we write must be 3 or less.

We can't write a larger digit, because if we started with eg. 3, then even a number of 3000 is definitely larger than N.

Algorithm

Let dp[i] be the number of ways to write a valid number if N became N[i], N[i+1], .... For example, if N = 2345, then dp[0] would be the number of valid numbers at most 2345, dp[1] would be the ones at most 345, dp[2] would be the ones at most 45, and dp[3] would be the ones at most 5.

Then, by our reasoning above, dp[i] = (number of d in D with d < S[i]) * ((D.length) ** (K-i-1)), plus dp[i+1] if S[i] is in D.
