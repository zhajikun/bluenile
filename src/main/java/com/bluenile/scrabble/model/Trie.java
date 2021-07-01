package com.bluenile.scrabble.model;

import org.springframework.util.ObjectUtils;

public class Trie {

    public static final int TOTAL_ALPHABET_NUMBER = 26;

    private static TrieNode root;

    public Trie() {
        root = new Trie.TrieNode(TOTAL_ALPHABET_NUMBER);
    }

    public void insert(String word) {
        if (ObjectUtils.isEmpty(word)) {
            return;
        }

        TrieNode node = root;
        char[] charArr = word.toLowerCase().toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            int index = charArr[i] - 'a';
            if (null == node.child[index]) {
                node.child[index] = new TrieNode(TOTAL_ALPHABET_NUMBER);
                node.child[index].setValue(charArr[i]);
            } else {
                node.child[index].setNum(node.child[index].getNum() + 1);
            }

            node  = node.child[index];
        }

        node.setEndOfWord(true);
    }

    public boolean isWord(String input){
        if(ObjectUtils.isEmpty(input)){
            return false;
        }

        TrieNode node = root;
        char[] charArr = input.trim().toLowerCase().toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            int index = charArr[i] - 'a';
            if(node.child[index] != null){
                node = node.child[index];
            } else {
                return false;
            }
        }
        return node.isEndOfWord();
    }


    public static TrieNode getRoot() {
        return root;
    }


    static class TrieNode {
        private int num;
        private boolean isEndOfWord;
        private char value;
        private TrieNode[] child;

        public TrieNode(int size) {
            num = 1;
            this.isEndOfWord = false;
            this.child = new TrieNode[size];
        }

        public boolean isEndOfWord() {
            return isEndOfWord;
        }

        public void setEndOfWord(boolean endOfWord) {
            isEndOfWord = endOfWord;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public void setValue(char value) {
            this.value = value;
        }
    }
}
