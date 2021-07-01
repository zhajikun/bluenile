###Scrabble Rest Service
## Part 1. Parse the dictional.txt file and save into a Tire Tree structure
- Tire tree is displayed as below:
 
```
      root
  /    |   \
  t   a     b
  |   |     |
  h   n     y
  |   | \   \
  e   s  y   e
  |  |   |
  i  r   w
  |  |   |
  r  e   e
  |
  r

```

- To implement this tire tree, for the beginning root and each node, I create 26 child node char array for it, because only 26 letters exists in english words.
```aidl
               root
          / / / |     \ \ \
         a  b c d ... x  y z
```

- The benefit for this using 26 child node array is that the finding of the child node is O(1) time. 
- The draw back for this 26 child node implementation is that wasting some memory, because not all 26 child node are used.
  -- also waste memory to store the unused pointer.
  
- Store the dictionary requires M * log N, where M is maximum string length and N is number of keys in tree.
- Seach the dictional requries O(M)


## Part 2. Give a input word ("hat") how to get all the combination of each charaters

### Part 2.1 combination
#### Select n characters from a total of m characters
--  O（M * N）
### Part 2.2 permutation
-- O （M * log N）

## Part 3. Score:  After we got the list of existing word in dictional, we call the calcScore method to give each word a score
### The list of words will be coverted to Map<word, score> (implmentation:  HashMap<String, Integer>)
-- O (M * N) , M is the number of words in the list and N is the character numers in the word

### Part 4. Sort: The JAVA 8's stream API will be called to sort the HashMap by value.












