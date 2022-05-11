package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Doctor implements Serializable{
    protected String docName;
    protected String speciality;

    abstract protected void OutputObject();
    abstract public void OutputListOfObjects(List<Appointment> a);
    abstract public void RemoveObject();
    abstract public int AllCountOfVisitors();
    abstract public int MinCountOfVisitors();
    abstract public int MinNameLengh();
}
class Appointment extends Doctor implements Serializable {
    private int day;
    private int shift;
    private int visitors;
    public static List<Appointment> allAppointments = new ArrayList<>();

    public Appointment(String _docName, String _speciality, int _day, int _shift, int _visitors){
        docName = _docName;
        speciality = _speciality;
        day = _day;
        shift = _shift;
        visitors = _visitors;
        allAppointments.add(this);
    }
    public Appointment(){}
    @Override protected void OutputObject(){
        System.out.println("\tПрізвище: " + docName);
        System.out.println("\tФах: " + speciality);
        System.out.println("\tДень: " + day);
        System.out.println("\tЗміна: " + shift);
        System.out.println("\tКількість відвідувачів: " + visitors);
    }
    @Override public void OutputListOfObjects(List<Appointment> a){
        System.out.println("Кількість записів: " + a.size());
        for(int i = 0; i < a.size(); i++){
            System.out.println("Запис №" + (i+1));
            a.get(i).OutputObject();
        }
    }
    @Override public void RemoveObject(){allAppointments.remove(this);}
    @Override public int AllCountOfVisitors(){
        int c = 0;
        for(int i = 0; i < allAppointments.size(); i++){
            c += allAppointments.get(i).visitors;
        }
        System.out.println("Загальна кількість відвідувачів: " + c);
        return c;
    }
    @Override public int MinCountOfVisitors(){
        Appointment min = allAppointments.get(0);
        for(int i = 0; i < allAppointments.size(); i++){
            if(min.visitors > allAppointments.get(i).visitors) min = allAppointments.get(i);
        }
        System.out.println("Мінімальна кількість відвідувачів: " + min.visitors);
        min.OutputObject();
        return min.visitors;
    }
    @Override public int MinNameLengh(){
        Appointment min = allAppointments.get(0);
        for(int i = 0; i < allAppointments.size(); i++){
            if(min.docName.toCharArray().length > allAppointments.get(i).docName.toCharArray().length)
                min = allAppointments.get(i);
        }
        System.out.println("Мінімальна довжина прізвища: " + min.docName.toCharArray().length);
        min.OutputObject();
        return min.docName.toCharArray().length;
    }

}
class File_manager{
    public static String path;
    public static void SetPath(){
        System.out.print("Введіть шлях до файлу загрузки і вигрузки: ");
        path = new Scanner(System.in).nextLine();
    }
    public static <T>void WriteList(List<T> Tlist) throws IOException {
        FileOutputStream writeData = new FileOutputStream(path + "/data.dat");
        ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
        writeStream.writeObject(Tlist);
        writeStream.flush();
        writeStream.close();
    }
    public static <T>List<T> ReadList() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path + "/data.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<T> Tlist = (List<T>) ois.readObject();
        ois.close();
        return Tlist;
    }
}
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File_manager.SetPath();
        System.out.println("1.Ввести новий прийом");
        System.out.println("2.Вивести всі прийоми");
        System.out.println("3.Видалити прийом за номером");
        System.out.println("4.Записати всі прийоми у файл");
        System.out.println("5.Записати всі прийоми з файлу");
        System.out.println("6.Сумарна кількість відвідувачів");
        System.out.println("7.Прийом з мін. кількістю відвідувачів");
        System.out.println("8.Прийом з найкоротшим прізвищем лікара");
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
                    System.out.print("\tІм'я лікара: ");
                    String name = new Scanner(System.in).nextLine();
                    System.out.print("\tФах: ");
                    String speciality = new Scanner(System.in).nextLine();
                    System.out.print("\tДень: ");
                    int day = new Scanner(System.in).nextInt();
                    System.out.print("\tЗміна: ");
                    int shift = new Scanner(System.in).nextInt();
                    System.out.print("\tКількість відвідувачів: ");
                    int visitors = new Scanner(System.in).nextInt();
                    Appointment a = new Appointment(name, speciality, day, shift, visitors);
                    break;
                case(2):
                    new Appointment().OutputListOfObjects(Appointment.allAppointments);
                    break;
                case(3):
                    int b = 0;
                    try {
                        System.out.print("Введіть номер запису(1-" + Appointment.allAppointments.size() + "): ");
                        b = new Scanner(System.in).nextInt() - 1;
                    }
                    catch(Exception ex){
                        System.out.println("Запису з таким номером нема");
                        break;
                    }
                    Appointment.allAppointments.get(b).RemoveObject();
                    System.out.println("Запис видалено");
                    break;
                case(4):
                    File_manager.WriteList(Appointment.allAppointments);
                    System.out.println("Файл записано");
                    break;
                case(5):
                    Appointment.allAppointments = File_manager.<Appointment>ReadList();
                    System.out.println("Прийоми записані");
                    break;
                case(6):
                        new Appointment().AllCountOfVisitors();
                    break;
                case(7):
                        new Appointment().MinCountOfVisitors();
                    break;
                case(8):
                        new Appointment().MinNameLengh();
                    break;
            }
        }
    }
}
