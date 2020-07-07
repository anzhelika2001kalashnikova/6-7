package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    private ArrayList<com.company.ValueContainer> list;
    private ArrayList<Integer> array;
    private int s;
    private Scanner scanner = new Scanner(System.in);

    public Input(){
        newArray();
        newS();
        solution();

        boolean flag = true;
        do{
            System.out.print("\nSelect Action:\n" +
                    "1. Input new Array.\nNow Array: ");
            if(array.size() > 0)
                for (Integer c: array
                ) {
                    System.out.print(c + ", ");
                }
            System.out.print("\n2. Input new S.\nNow S = " + s + ";\n3. Solution.\n4. Exit.\n\nInput Action Number: ");
            int switchNum = scanner.nextInt();
            switch (switchNum){
                case 1 : {
                    newArray();
                    break;
                }
                case 2 : {
                    newS();
                    break;
                }
                case 3 : {
                    solution();
                    break;
                }
                case 4 : {
                    flag = false;
                    break;
                }
                default:{
                    System.out.println("Error! Input again.");
                }
            }
        }while(flag);
    }

    private void newArray(){
        array = new ArrayList<>();
        int a;
        System.out.println("Input elements. If value is'nt int, then input is completed.");
        do{
            a = scanner.nextInt();
            array.add(a);
        } while (scanner.hasNextInt());
        scanner.next();

    }

    private void newS(){
        System.out.print("Input S.\nS = ");
        s = scanner.nextInt();
    }

    private void solution(){
        int[] arrayHelp = new int[array.size()];
        for (int i = 0; i < arrayHelp.length; i++)
            arrayHelp[i] = array.get(i);
        list = new MyTree().solution(arrayHelp, s);
        if(list.size() > 0)
            for (com.company.ValueContainer c: list
            ) {
                c.print();
            }
        else
            System.out.println("No results");
    }

}
