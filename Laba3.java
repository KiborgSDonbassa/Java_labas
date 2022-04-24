package com.company;

import java.util.Random;
import java.util.Scanner;

class laba4{
    public void Z1(){
        Scanner a = new Scanner(System.in);
        int lengh = 0;
        try{
            System.out.print("Введіть розмір массиву:");
            lengh = a.nextInt();
        }
        catch (Exception ex){
            System.out.println("Невірне значення");
            Z1();
        }
        int[] mass = new int[lengh];
        for(int i = 0; i < lengh; i++){
            try {
                System.out.print("\tВведіть " + (i + 1) + " елемент массиву: ");
                mass[i] = a.nextInt();
            }
            catch (Exception ex){
                System.out.println("Невірне значення");
                i--;
                continue;
            }
        }
        System.out.println("Різниця між першим і останнім елементом: " + Math.abs(mass[0] - mass[lengh-1]));
    }
    public void Z2(){
        Scanner a = new Scanner(System.in);
        int lengh = 0;
        int type;
        try{
            System.out.print("Введіть розмір массиву: ");
            lengh = a.nextInt();
        }
        catch (Exception ex){
            System.out.println("Невірне значення");
            Z2();
        }
        int[] mass = new int[lengh];
        while(true){
            System.out.print("Спосіб введення(1. Рандом, 2. Вручну): ");
            try{
                type = a.nextInt();
                if(type < 1 || type > 2){
                    System.out.println("Невірне значення");
                    continue;
                }
            }
            catch (Exception ex){
                System.out.println("Невірне значення");
                continue;
            }
            break;
        }
        switch (type){
            case(1):
                Random r = new Random();
                for(int i = 0; i < lengh; i++){
                    mass[i] = r.nextInt(200) - 100;
                }
                for(int i = 0; i < lengh; i++){
                    System.out.println(mass[i]);
                }
                break;
            case(2):
                for(int i = 0; i < lengh; i++){
                    try {
                        System.out.print("\tВведіть " + (i + 1) + " елемент массиву: ");
                        mass[i] = a.nextInt();
                    }
                    catch (Exception ex){
                        System.out.print("\tНевірне значення");
                        i--;
                        continue;
                    }
                }
                for(int i = 0; i < lengh; i++){
                    System.out.println(mass[i]);
                }
                break;
        }
        int max = mass[0];
        for(int i = 0; i < lengh; i++){
            if(Math.abs(mass[i]) > Math.abs(max)){
                max = mass[i];
            }
        }
        System.out.println("Максимальний модуль в массиві: " + Math.abs(max));
        int pos = -1;
        for(int i = 0; i < lengh; i++){
            if (mass[i] == 0) {
                pos = i;
            }
        }
        if(pos == -1){
            System.out.println("Нульового елементу нема");
        }
        else{
            int sum = 0;
            for(int i = pos; i < lengh; i++){
                sum += mass[i];
            }
            System.out.println("Сумма: " + sum);
        }
    }
}
public class Main {

    public static void main(String[] args) {
        laba4 laba = new laba4();
        System.out.println("1 Завдання: ");
        laba.Z1();
        System.out.println("2 Завдання: ");
        laba.Z2();
    }
}
