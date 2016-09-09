package com.gsearch.dictionary;

/**
 * Created by RI01796 on 2016/9/9.
 */
public class TireNode {

    private char character;
    private boolean terminal;
    private TrieNode sibling;
    private TrieNode[] children = new TrieNode[0];

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public TrieNode getSibling() {
        return sibling;
    }

    public void setSibling(TrieNode sibling) {
        this.sibling = sibling;
    }

    public TrieNode[] getChildren() {
        return children;
    }

    public void setChildren(TrieNode[] children) {
        this.children = children;
    }
}
