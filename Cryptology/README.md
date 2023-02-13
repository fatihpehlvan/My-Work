In this project I developed an encryption method that uses matrix multiplication and matrix inversions

First, I converted the secret message into a string of numbers by arbitrarily assigning a number to each letter
of the message. Next, I converted this string of numbers into a new set of numbers by multiplying the string
by a square matrix of our choice that has an inverse. This new set of numbers represents the coded message.


To decode the message, I took the string of coded numbers and multiply it by the inverse of the matrix to
get the original string of numbers. Finally, by associating the numbers with their corresponding letters,
obtain the original message.
