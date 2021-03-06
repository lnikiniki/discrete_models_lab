package com.lab.third;

import com.lab.Transformer;
import com.lab.fourth.Potik;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MaxThread {
    private final static int T = 7;
    private final static int N = 6;

    public static void main(String[] args) {

        Potik[] tred = new Potik[T];
        Kpp[] Sr = new Kpp[N];
        int maxTer;


        try (FileReader reader = new FileReader("com/lab/third/notes5")) {
            int i = 0;
            int numb = 0;
            int c;
            int numbOut = 0, numbIn = 0;
            while ((c = reader.read()) != -1) {

                if ((char) c == '\n') {
                    tred[i] = new Potik(numbOut, numbIn, numb);
                    numb = 0;
                    i++;
                } else if (c >= 65) {
                    numbOut = Transformer.upperLetterToNum((char) c);
                    if (numbOut == 19) {
                        numbOut = N - 2;
                    } else if (numbOut == 18) {
                        numbOut = N - 1;
                    }

                    while ((c = reader.read()) < 65) ;
                    numbIn = Transformer.upperLetterToNum((char) c);
                    if (numbIn == 19) {
                        numbIn = N - 2;
                    } else if (numbIn == 18) {
                        numbIn = N - 1;
                    }
                } else if (Character.getNumericValue(c) >= 0 && Character.getNumericValue(c) <= 9) {
                    numb *= 10;
                    numb += Character.getNumericValue(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < N; i++) {
            Sr[i] = new Kpp();
            Sr[i].name = i;
            Sr[i].input = 0;
            Sr[i].output = 0;
        }
        for (int i = 0; i < N; i++)
            for (int j = 0; j < T; j++) {
                if (Sr[i].name == tred[j].A)
                    Sr[i].output += tred[j].C;
                if (Sr[i].name == tred[j].B)
                    Sr[i].input += tred[j].C;
            }
        Sr[N - 2].input = Sr[N - 2].output;
        Sr[N - 1].output = Sr[N - 1].input;
        for (int i = 0; i < N; i++)
            Sr[i].prop = Math.max(Sr[i].input, Sr[i].output);
        maxTer = Math.max(Sr[N - 1].prop, Sr[N - 2].prop);

        for (int j = 0; j < T; j++)
            if (tred[j].B == N - 1)
                Sr[N - 1].prop -= Math.min(tred[j].C, Sr[tred[j].A].prop);

        for (int i = N - 3; i >= 0; i--)
            for (int j = 0; j < T; j++)
                if (tred[j].B == i)
                    Sr[i].prop -= Math.min(tred[j].C, Sr[tred[j].A].prop);

        System.out.println("Thread: " + maxTer);
    }
}
