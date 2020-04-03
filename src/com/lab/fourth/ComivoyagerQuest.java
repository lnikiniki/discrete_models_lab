package com.lab.fourth;

import com.lab.Transformer;
import com.lab.second.Postman;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ComivoyagerQuest {
    private final static int CVD = 10;
    private final static int CVV = 5;

    private static void CVWalk(boolean[] city, Potik[] umGraf, int kordGPS, int kordSTR) {
        while (!Postman.fullWay(city)) {
            int min = 6428;
            int nD = 0;
            for (int i = 0; i < CVD; i++)
                if ((umGraf[i].A == kordGPS || umGraf[i].B == kordGPS) && (!city[umGraf[i].B] || !city[umGraf[i].A]))
                    if (umGraf[i].C < min) {
                        min = umGraf[i].C;
                        if (umGraf[i].A == kordGPS)
                            nD = umGraf[i].B;
                        else
                            nD = umGraf[i].A;
                    }

            city[nD] = true;
            CVWalk(city, umGraf, nD, kordSTR);
            if (Postman.fullWay(city)) {
                System.out.println(Transformer.numToUpperLetter(nD) + "-" + Transformer.numToUpperLetter(kordGPS));
                return;
            } else {
                city[nD] = false;
                umGraf[nD].C = 6248;
                return;
            }
        }
        System.out.println(Transformer.numToUpperLetter(kordSTR) + "-" + Transformer.numToUpperLetter(kordGPS));
    }

    public static void main(String[] args) {

        boolean[] city = new boolean[CVV];
        Potik[] umGraf = new Potik[CVD];
        char textOut, textIn;

        for (int i = 0; i < CVV; i++)
            city[i] = false;

        try (FileReader reader = new FileReader("com/lab/fourth/notes4u")) {
            int i = 0;
            int numb = 0;
            int c;
            int numbOut = 0, numbIn = 0;
            while ((c = reader.read()) != -1) {
                if ((char) c == '\n') {
                    umGraf[i] = new Potik(numbOut, numbIn, numb);
                    numb = 0;
                    i++;
                } else if (c >= 65) {
                    numbOut = Transformer.upperLetterToNum((char) c);
                    numbIn = Transformer.upperLetterToNum((char) c);
                } else if (Character.getNumericValue(c) >= 0 && Character.getNumericValue(c) <= 9) {
                    numb *= 10;
                    numb += Character.getNumericValue(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner in = new Scanner(System.in);
        System.out.print("Input place to start: ");
        String firstDot = in.next();
        city[Transformer.upperLetterToNum(firstDot.charAt(0))] = true;
        CVWalk(city, umGraf, Transformer.upperLetterToNum(firstDot.charAt(0)), Transformer.upperLetterToNum(firstDot.charAt(0)));
    }
}
