package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


interface ListAble{
    void Remove();
    void PrintObject();
    static void AddObject(){}
    static void PrintAllObjects(){}
}
class Product implements ListAble, Serializable{
    public String name;
    public int price;
    public static List<Product> productTypes = new ArrayList<>();

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
        productTypes.add(this);
    }
    public static void AddObject(){
        System.out.print("\tІм'я: ");
        String name = new Scanner(System.in).nextLine();
        System.out.print("\tЦіна: ");
        int price = new Scanner(System.in).nextInt();
        Product a = new Product(name, price);
    }
    public void PrintObject() {
        System.out.println("\tНазва: " + this.name);
        System.out.println("\tЦіна: " + this.price);
    }
    public static void PrintAllObjects(){
        for(int i = 0; i < productTypes.size(); i++){
            System.out.println("Товар №" + (i + 1));
            productTypes.get(i).PrintObject();
        }
    }
    public void Remove(){
        productTypes.remove(this);
    }
}
class Report implements ListAble, Serializable{
    private int day;
    private String product;
    private int count;
    public static List<Report> allReports = new ArrayList<>();

    public Report(int day, String product, int count) {
        this.day = day;
        this.product = product;
        this.count = count;
        allReports.add(this);
    }
    public static void AddObject(){
        if (Product.productTypes.size() == 0){
            System.out.println("Треба записати товар!!");
            return;
        }
        System.out.print("\tДень: ");
        int day = new Scanner(System.in).nextInt();
        String product = "";
        String b = null;
        while(true){
            System.out.print("\tНазва товару(");
            for (int i = 0; i < Product.productTypes.size(); i++) {
                if (i == Product.productTypes.size() - 1) {
                    System.out.print(Product.productTypes.get(i).name);
                    break;
                }
                System.out.print(Product.productTypes.get(i).name + ", ");
            }
            System.out.print("): ");
            product = new Scanner(System.in).nextLine();
            if (IdenteficateProduct(product) == null) {
                System.out.println("Товар не знайдено");
                continue;
            }
            b = IdenteficateProduct(product).name;
            break;
        }
        System.out.print("\tКількість продажів: ");
        int count = new Scanner(System.in).nextInt();
        Report a = new Report(day, b, count);
    }
    public void PrintObject(){
        System.out.println("\tДень: " + this.day);
        System.out.println("\tТовар: " + this.product);
        System.out.println("\tКількість: " + this.count);
    }
    public static void PrintAllObjets(){
        for(int i = 0; i < allReports.size(); i++){
            System.out.println("Звіт №" + (i+1));
            allReports.get(i).PrintObject();
        }
    }
    public void Remove() {
        allReports.remove(this);
    }
    public static void RichedMoneyFromProduct(){
        class ProductMoney{
            public Product a;
            public int b;
            public ProductMoney(Product a, int b) {
                this.a = a;
                this.b = b;
            }
        }
        List<ProductMoney> aboba = new ArrayList<>();
        for(int i = 0; i < Product.productTypes.size(); i++){
            Product a = Product.productTypes.get(i);
            int cash = 0;
            for(int j = 0; j < allReports.size(); j++){
                if(allReports.get(j).product.equals(Product.productTypes.get(i).name))
                    cash += allReports.get(j).count * Product.productTypes.get(i).price;
            }
            aboba.add(new ProductMoney(a,cash));
        }
        for(int i = 0; i < aboba.size() - 1; i++){
            for(int j = 0; j < aboba.size() - 1; j++){
                if(aboba.get(j).b < aboba.get(j + 1).b){
                    ProductMoney a = aboba.get(j);
                    aboba.set(j, aboba.get(j + 1));
                    aboba.set(j + 1, a);
                }
            }
        }
        for(int i = 0; i < aboba.size(); i++){
            System.out.print("Кількість виручки за(" + aboba.get(i).a.name + "): ");
            System.out.println(aboba.get(i).b);
        }
    }
    private static Product IdenteficateProduct(String name){
        Product a = null;
        for(int i = 0; i < Product.productTypes.size(); i++){
            if(name.equals(Product.productTypes.get(i).name)){
                a = Product.productTypes.get(i);
                break;
            }
        }
        return a;
    }
}
class File_manager{
    public static String path;
    public static void SetPath(){
        System.out.print("Введіть шлях до файлу загрузки і вигрузки: ");
        path = new Scanner(System.in).nextLine();
    }
    public static <T>void WriteList(List<T> Tlist, String fileName) throws IOException {
        FileOutputStream writeData = new FileOutputStream(path + "/" + fileName + ".dat");
        ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
        writeStream.writeObject(Tlist);
        writeStream.flush();
        writeStream.close();
    }
    public static <T>List<T> ReadList(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path + "/" + fileName + ".dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<T> Tlist = (List<T>) ois.readObject();
        ois.close();
        return Tlist;
    }
}
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File_manager.SetPath();
        System.out.println("1.Ввести новий товар");
        System.out.println("2.Ввести новий звіт");
        System.out.println("3.Вивести всі товари");
        System.out.println("4.Вивести всі звіти");
        System.out.println("5.Видалити товар за номером");
        System.out.println("6.Видалити звіт за номером");
        System.out.println("7.Кількість виручки за кожен товар");
        System.out.println("8.Записати всі товари у файл");
        System.out.println("9.Записати всі товари з файлу");
        System.out.println("10.Записати всі звіти у файл");
        System.out.println("11.Записати всі звіти з файлу");
        int n = 0;
        while(true) {
            try {
                System.out.print("Оберіть опцію: ");
                n = new Scanner(System.in).nextInt();
            }
            catch (Exception ex){
                System.out.println("Введене некоректне значення");
                continue;
            }
            switch(n){
                case(1):
                    Product.AddObject();
                    break;
                case(2):
                    Report.AddObject();
                    break;
                case(3):
                    Product.PrintAllObjects();
                    break;
                case(4):
                    Report.PrintAllObjets();
                    break;
                case(5):
                    System.out.print("Введіть номер товару: ");
                    int nn = new Scanner(System.in).nextInt();
                    if(nn < 1 || nn > Product.productTypes.size()){
                        System.out.println("Товару з таким номером нема");
                        break;
                    }
                    Product.productTypes.remove(nn-1);
                    break;
                case(6):
                    System.out.print("Введіть номер звіту: ");
                    int nnn = new Scanner(System.in).nextInt();
                    if(nnn < 1 || nnn > Report.allReports.size()){
                        System.out.println("Звіту з таким номером нема");
                        break;
                    }
                    Report.allReports.remove(nnn-1);
                    break;
                case(7):
                    Report.RichedMoneyFromProduct();

                    break;
                case(8):
                    File_manager.WriteList(Product.productTypes, "products");
                    System.out.println("Товари записані");
                    break;
                case(9):
                    Product.productTypes = File_manager.<Product>ReadList("products");
                    System.out.println("Товари введені");
                    break;
                case(10):
                    File_manager.WriteList(Report.allReports, "reports");
                    System.out.println("Звіти записані");
                    break;
                case(11):
                    Report.allReports = File_manager.<Report>ReadList("reports");
                    System.out.println("Звіти введені");
                    break;
            }
        }
    }
}
