package com.lab;

public class Transformer {

    public static char numToUpperLetter(int num) {
        return (char) (num + 65);
    }

    public static int upperLetterToNum(char s) {
        return (int) s - 65;
    }
}
