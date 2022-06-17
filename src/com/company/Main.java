package com.company;

import java.io.*;
import java.util.Scanner;

class Counter implements Serializable {
    double x;
    double y;
    Counter(){
        x=0;
        y=0;
    }
    void sinus(double x){
        this.x = x;
        y = this.x - Math.sin(this.x);
        System.out.println(y);
    }
}
public class Main {
    public static void main(String[] args) {
        String komand;
        Scanner in = new Scanner(System.in);
        Counter y = new Counter();
        System.out.println("Введите значение переменной (x) либо одну из команд(save/upload/check)");
        while(true) {
            komand = in.nextLine();
            try {
                double x = Double.parseDouble(komand);
                y.sinus(x);
            }
            catch (Exception ex) {
                if (komand.equalsIgnoreCase("save")) {
                    try (ObjectOutputStream op = new ObjectOutputStream(new FileOutputStream("output"))) {
                        op.writeObject(y);
                        System.out.println("Значения сохранены");
                    } catch (IOException IOex) {
                        IOex.getMessage();
                    }
                }
                else if (komand.equalsIgnoreCase("upload")){
                    try (ObjectInputStream ip = new ObjectInputStream(new FileInputStream("output"))) {
                        y = (Counter) ip.readObject();
                        System.out.println("Значения восстановлены");
                    } catch (Exception IOex) {
                        IOex.getMessage();
                    }
                }
                else if(komand.equalsIgnoreCase("check")){
                    System.out.println("Данные объекта:");
                    System.out.println("x: " + y.x + " y: "+ y.y);
                }
                else{
                    System.out.println("Не верно введена команда");
                }
            }
        }
    }
}
