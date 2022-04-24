package com.company;

import java.util.Random;
import java.util.Scanner;

class laba4{

    public void Z1(){
        Scanner a = new Scanner(System.in);
        int[][] mtrx = new int[][]{{3,-2,4,9},{0,3,10,3},{5,-4,-6,0}};
        for(int i = 0; i < mtrx.length; i++){
            if(i != 0)System.out.println();
            for(int j = 0; j < mtrx[0].length; j++){
                System.out.print("\t" + mtrx[i][j]);
            }
        }
        System.out.println();
        System.out.println("\tМаксимальне число: " + Math.max(mtrx[2][3],mtrx[1][1]));
    }

    public void Z2(){
        Scanner a = new Scanner(System.in);
        Random r = new Random();
        int n,m;
        System.out.print("\tВведіть кількість рядків: ");
        n = a.nextInt();
        System.out.print("\tВведіть кількість стовбців: ");
        m = a.nextInt();
        int[][] mtrx = new int[n][m];
        for(int i = 0; i < mtrx.length; i++){
            for(int j = 0; j < mtrx[0].length; j++){
                mtrx[i][j] = r.nextInt(200) - 100;
            }
        }
        for(int i = 0; i < mtrx.length; i++){
            if(i != 0)System.out.println();
            for(int j = 0; j < mtrx[0].length; j++){
                System.out.print("\t" + mtrx[i][j]);
            }
        }
        System.out.println();
        if(mtrx.length != mtrx[0].length){
            System.out.println("Матриця не є ортономованою");
            return;
        }
        for(int i = 0; i < mtrx.length; i++){
            for(int j = 0; j < mtrx[0].length; j++){
                if(mtrx[i][j] != mtrx[j][i]){
                    System.out.println("Матриця не є ортономованою");
                    return;
                }
            }
        }
        System.out.println("Матриця є ортономованою");
    }
}
public class Main {

    public static void main(String[] args) {
	    laba4 laba = new laba4();
        System.out.println("1 Завдання:");
        laba.Z1();
        System.out.println("2 Завдання:");
        laba.Z2();
    }
}
