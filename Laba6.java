package com.company;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;

class Student{
    private String secondName;
    private Date birthDate;
    private String birthPlace;
    public static List<Student> AllStudents = new ArrayList<>();

    public Student(Student a){
        this.secondName = a.secondName;
        this.birthDate = a.birthDate;
        this.birthPlace = a.birthPlace;
        AllStudents.add(this);
    }
    public Student(){ }
    public static Student AddStudentInfo(){
        Scanner a = new Scanner(System.in);
        Scanner f = new Scanner(System.in);
        Student b = new Student();
        try {
            System.out.print("\tВведіть прізвище студента: ");
            b.secondName = a.nextLine();
            System.out.println("\tВведіть дату народження: ");
            int d, m, y;
            System.out.print("\t\tДень: ");
            d = a.nextInt();
            System.out.print("\t\tМісяць: ");
            m = a.nextInt();
            System.out.print("\t\tРік: ");
            y = a.nextInt();
            b.birthDate = new Date(y - 1900, m - 1, d);
            System.out.print("\tВведіть місце народження: ");
            b.birthPlace = f.nextLine();
        }
        catch(Exception ex){
            System.out.println("Введене некоректне значення");
            return AddStudentInfo();
        }
        return b;
    }
    private void OutputInfo(){
        SimpleDateFormat aboba = new SimpleDateFormat("d-MM-y");
        System.out.println(secondName + "\t|\t" + aboba.format(birthDate) + "\t|\t" + birthPlace);
    }
    public static void PrintAllStudentInfo(List<Student> a){
        System.out.println("Кількість студентів: " + a.size());
        for(int i = 0; i < a.size(); i++){
            a.get(i).OutputInfo();
        }
    }
    public static void PrintAllSortedStudentInfo(){
        List<Student> arrayToSort = new ArrayList<>();
        arrayToSort.addAll(AllStudents);
        List<Student> aa = new ArrayList<>();
        for(int i = 0; i < arrayToSort.size() - 1; i++){
            for(int j = 0; j < arrayToSort.size() - 1; j++){
                if(dateValue(arrayToSort.get(i+1).birthDate) < dateValue(arrayToSort.get(i).birthDate)){
                    Student tmp = arrayToSort.get(i+1);
                    arrayToSort.set(i+1, arrayToSort.get(i));
                    arrayToSort.set(i, tmp);
                }
            }
        }
        for(int i = 0; i < arrayToSort.size() - 1; i++){
            if(arrayToSort.get(i).birthDate.getDay() == arrayToSort.get(i+1).birthDate.getDay() &&
                    arrayToSort.get(i).birthDate.getMonth() == arrayToSort.get(i+1).birthDate.getMonth()){
                aa.add(arrayToSort.get(i));
                arrayToSort.remove(i);
                aa.add(arrayToSort.get(i+1));
                arrayToSort.remove(i+1);
            }
        }
        PrintAllStudentInfo(arrayToSort);
        System.out.println("Двійнята");
        PrintAllStudentInfo(aa);
    }
    private static int dateValue(Date a){
        int c = 0;
        c += a.getDay();
        c += a.getMonth();
        c += a.getYear();
        return c;
    }

}
class TimeTable implements Serializable{
    public int lessonNumber;
    public int dayWeek;
    public String subject;
    public String teacherSecondName;
    public boolean educationType;
    public static List<TimeTable> AllTimeTabled = new ArrayList<TimeTable>();
    public static List<TimeTable> sorted = new ArrayList<TimeTable>();

    public TimeTable(TimeTable a){
        this.lessonNumber = a.lessonNumber;
        this.dayWeek = a.dayWeek;
        this.subject = a.subject;
        this.teacherSecondName = a.teacherSecondName;
        this.educationType = a.educationType;
        AllTimeTabled.add(this);
    }
    public TimeTable(){ }
    public static TimeTable AddTimeTableInfo(){
        Scanner a = new Scanner(System.in);
        Scanner f = new Scanner(System.in);
        TimeTable b = new TimeTable();
        try {
            System.out.print("\tВведіть номер пари: ");
            b.lessonNumber = a.nextInt();
            System.out.print("\tВведіть день тижня(номер дня): ");
            b.dayWeek = a.nextInt();
            System.out.print("\tВведіть назву предмета: ");
            b.subject = f.nextLine();
            System.out.print("\tВведіть прізвище викладача: ");
            b.teacherSecondName = f.nextLine();
            System.out.print("\tВведіть форму навчання(якщо очна, то ОЧНА, якщо дистанційна то ДИСТАНЦІЙНА: ");
            String v = f.nextLine();
            if(v.equals("ОЧНА")){
                b.educationType = true;
            }
            else if(v.equals("ДИСТАНЦІЙНА")){
                b.educationType = false;
            }
            else{
                throw new RuntimeException();
            }
        }
        catch(Exception ex){
            System.out.println("Введене некоректне значення");
            return AddTimeTableInfo();
        }
        return b;
    }
    public static void DeleteTimeTableInfo(){
        System.out.println("Введіть номер запису: ");
        int n = new Scanner(System.in).nextInt();
        if(n > AllTimeTabled.size()){
            System.out.println("Номер за межами массиву!");
            DeleteTimeTableInfo();
            return;
        }
        AllTimeTabled.remove(n - 1);
    }
    public static void OutputAllTimeTableInfo(List<TimeTable> array){
        System.out.println("Кількість записів: " + array.size());
        for(int i = 0; i < array.size(); i++){
            array.get(i).OutputTimeTableInfo();
        }
    }
    public void OutputTimeTableInfo(){
        System.out.println("\tНомер пари: " + lessonNumber);
        System.out.println("\tДень тижня: " + dayWeek);
        System.out.println("\tПредмет: " + subject);
        System.out.println("\tПрізвище вчителя: " + teacherSecondName);
        if(educationType) System.out.println("\tФорма навчання: ОЧНА");
        else System.out.println("\tФорма навчання: ДИСТАНЦІЙНА");
        System.out.println();
    }
    public static void SortTabels(){
        sorted.addAll(AllTimeTabled);
        for(int i = 0; i < sorted.size()-1; i++){
            for(int j = 0; j < sorted.size()-1; j++){
                if(sorted.get(i).dayWeek > sorted.get(i+1).dayWeek){
                    TimeTable tmp = sorted.get(i);
                    sorted.set(i, sorted.get(i+1));
                    sorted.set(i+1, tmp);
                }
            }
        }
    }
    public static void SearchTabels(){
        String name;
        System.out.print("Введіть прізвище викладача: ");
        name = new Scanner(System.in).nextLine();
        for(int i = 0; i < AllTimeTabled.size(); i++){
            if(AllTimeTabled.get(i).teacherSecondName.equals(name)){
                AllTimeTabled.get(i).OutputTimeTableInfo();
            }
        }
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

    public static void main(String[] args) throws IOException, InterruptedException, ParserConfigurationException, TransformerException, SAXException, IllegalAccessException, ClassNotFoundException {
        int i = 2;
        while(i == 1) {
            System.out.println("1.Ввести нового студента");
            System.out.println("2.Вивести всіх студентів");
            System.out.println("3.Вивести всіх студентів(відсортовано за датою)");
            System.out.println("4.Вийти");
            System.out.print("Оберіть опцію: ");
            int n = 4;
            while(true) {
                try {
                    n = new Scanner(System.in).nextInt();
                    break;
                }
                catch (Exception ex){
                    System.out.println("Введене некоректне значення");
                    System.out.print("Оберіть опцію: ");
                    continue;
                }
            }
            switch (n) {
                case (1):
                    Student _student = new Student(Student.AddStudentInfo());
                    break;
                case (2):
                    Student.PrintAllStudentInfo(Student.AllStudents);
                    break;
                case (3):
                    Student.PrintAllSortedStudentInfo();
                    break;
                case (4):
                    return;
            }
        } // 1 завдання
        File_manager.SetPath();
        while(i == 2){
            System.out.println("1.Ввести запис у розклад");
            System.out.println("2.Вивести всі данні про розклад");
            System.out.println("3.Вивести відсортований массив записів");
            System.out.println("4.Пошук по прізвищу");
            System.out.println("5.Видалити запис");
            System.out.println("6.Записати у файл");
            System.out.println("7.Зчитати з файлу");
            System.out.print("Оберіть опцію: ");
            int n = 4;
            while(true) {
                try {
                    n = new Scanner(System.in).nextInt();
                    break;
                }
                catch (Exception ex){
                    System.out.println("Введене некоректне значення");
                    System.out.print("Оберіть опцію: ");
                }
            }
            switch (n) {
                case (1):
                    TimeTable _timeTable = new TimeTable(TimeTable.AddTimeTableInfo());
                    break;
                case (2):
                    TimeTable.OutputAllTimeTableInfo(TimeTable.AllTimeTabled);
                    break;
                case (3):
                    TimeTable.SortTabels();
                    TimeTable.OutputAllTimeTableInfo(TimeTable.sorted);
                    break;
                case (4):
                    TimeTable.SearchTabels();
                    break;
                case (5):
                    TimeTable.DeleteTimeTableInfo();
                    break;
                case (6):
                    File_manager.WriteList(TimeTable.AllTimeTabled);
                    System.out.println("Файл записано");
                    break;
                case (7):
                    TimeTable.AllTimeTabled = File_manager.<TimeTable>ReadList();
                    System.out.println("Файл зчитано");
                    break;
            }
        }// 2 завдання
    }
}
