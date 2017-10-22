package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private List<PersonInfo> list;

    public static void main(String[] args) {
        new Main().run(args);
    }

    private void run(String[] args) {
        init();
        Scanner scanner = new Scanner(System.in);
        while(true){
            printMenu();
            int i = scanner.nextInt();
            switch (i){
                case 0: printText("Пока");
                    return;
                case 1: createContact(scanner);
                    break;
                case 2: printContacts();
                    break;
                case 3: deleteContact(scanner);
                    break;
                case 4: editContact(scanner);
                    break;
                default: printText("Выберите один из пунктов меню");
                    break;
            }
        }
    }

    private void editContact(Scanner scanner) {
        if (list.isEmpty()) {
            printNoContacts();
        }
        else {
            printText("Введите индекс контакта: ");
            int i = scanner.nextInt();
            if(i < list.size() && i >= 0){
                String name = getName(scanner);
                String email = getEmail(scanner);
                list.get(i).setName(name);
                list.get(i).setEmail(email);
                printText("Контакт изменен");
            }
            else {
                printNoContactByIndex();
            }
        }
    }

    private void printMenu() {
        printText("\nМеню:\n" +
                        "0: Выход\n" +
                        "1: Создать контакт\n" +
                        "2: Вывести список контактов\n" +
                        "3: Удалить контакт\n" +
                        "4: Изменить контакт\n");
    }

    private void deleteContact(Scanner scanner) {
        if (list.isEmpty()) {
            printNoContacts();
        }
        else {
            printText("Введите индекс контакта: ");
            int i = scanner.nextInt();
            if(i < list.size() && i >= 0){
                list.remove(i);
                printText("Контакт успешно удален!");
            }
            else {
                printNoContactByIndex();
            }
        }
    }

    private void printNoContactByIndex() {
        printText("Нет контакта с таким индексом\n");
    }

    private void printNoContacts() {
        printText("Список контактов пустой!\n");
    }

    private void printContacts() {
        if (list.isEmpty()){
            printNoContacts();
        }
        for (int i = 0; i < list.size(); i++) {
            printText("№ " + i + ": " + list.get(i).toString());
        }
    }

    private void createContact(Scanner scanner) {
        String name = getName(scanner);

        String email = getEmail(scanner);

        list.add(new PersonInfo(name, email));

        printText("Новый контакт добавлен");
    }

    private String getEmail(Scanner scanner) {
        printText("Введите email: ");
        String email;
        while(true){
           email = scanner.next();
           if (!email.isEmpty() && email.length() > 6 && email.contains("@")){
               String[] split = email.split("@");
               if (split.length == 2 && !split[1].isEmpty() && split[1].contains(".")){
                    String[] split1 = split[1].split("\\.");
                    if (split1.length == 2 && !split1[0].isEmpty() && !split1[1].isEmpty()){
                        return email;
                    }
               }
           }
           printText("Введите корректный email");
        }
    }

    private String getName(Scanner scanner) {
        printText("Введите имя: ");
        String name;
        while (true){
            name = scanner.next();
            if (!name.isEmpty() && name.length() > 5){
                return name;
            }
            else {
                printText("Длина имени должна быть больше 5 символов! ");
            }
        }
    }

    private void printText(String s) {
        System.out.println(s);
    }

    private void init() {
        list = new ArrayList<PersonInfo>();
    }
}
