package com.lab.second;

import com.lab.Transformer;
import com.lab.first.Eji;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Postman {
    private final static int M = 10;

    public static boolean fullWay(boolean[] gasse) {
        for (boolean b : gasse) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    private static boolean Walk(boolean[] gasse, Eji[] wvGraf, int kordGPS) {
        if (fullWay(gasse))
            return true;
        else {
            for (int i = 0; i < M; i++)
                if (!gasse[i] && (wvGraf[i].A == kordGPS || wvGraf[i].B == kordGPS)) {
                    gasse[i] = true;
                    if (wvGraf[i].A == kordGPS) {
                        Walk(gasse, wvGraf, wvGraf[i].B);
                        if (fullWay(gasse)) {
                            System.out.println(Transformer.numToUpperLetter(wvGraf[i].B) + "-" + Transformer.numToUpperLetter(wvGraf[i].A));
                            return true;
                        } else
                            gasse[i] = false;
                    } else {
                        Walk(gasse, wvGraf, wvGraf[i].A);
                        if (fullWay(gasse)) {
                            System.out.println(Transformer.numToUpperLetter(wvGraf[i].A) + "-" + Transformer.numToUpperLetter(wvGraf[i].B));
                            return true;
                        } else
                            gasse[i] = false;
                    }
                }
            return false;
        }
    }

    public static void main(String[] args) {

        boolean[] gasse = new boolean[M];
        Eji[] wvGraf = new Eji[M];
        int textOut;
        char textIn;

        try (FileReader reader = new FileReader("com/lab/second/notes2u")) {
            int i = 0;
            int c;
            int numbOut = 0, numbIn = 0;
            while ((c = reader.read()) != -1) {

                if ((char) c == '\n') {
                    wvGraf[i] = new Eji(numbOut, numbIn);
                    i++;
                } else if (c >= 65) {
                    numbOut = Transformer.upperLetterToNum((char) c);

                    while ((c = reader.read()) < 65) ;
                    numbIn = Transformer.upperLetterToNum((char) c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner in = new Scanner(System.in);
        System.out.print("Input place to start: ");
        String firstDot = in.next();

        if (Walk(gasse, wvGraf, Transformer.upperLetterToNum(firstDot.charAt(0))))
            System.out.println("Congradulations");
        else System.out.println("no way");
    }
}
