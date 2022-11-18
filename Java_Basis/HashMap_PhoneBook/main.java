package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        PhoneBook contacts = new PhoneBook();

        try (Scanner scanner = new Scanner(System.in);) {
            fillGroups(contacts, scanner);
            fillContacts(contacts, scanner);
        }

        System.out.println(contacts)
    }

    private static void fillGroups(PhoneBook contacts, Scanner scanner){
        String tmpInput = null;
        while (true) {
            System.out.println("Введите название группы или end");
            tmpInput = scanner.nextLine();
            if(tmpInput.equals("end")) {
                break;
            }

            contacts.addGroup(tmpInput);
        }
    }
  
    private static void fillContacts(PhoneBook contacts, Scanner scanner){
        String tmpInput = null;
        while (true) {
            System.out.println("Введите имя, номер телефона [Имя 89819619704] или end");
            tmpInput = scanner.nextLine();
            if(tmpInput.equals("end")) {
                break;
            }
            final String[] contactData = tmpInput.split("\\s+");
            Contact contact = new Contact(contactData[0], contactData[1]);

            System.out.println("Введите имя групп, в которые необходимо добавить контакт ");
            String[] groupNames = scanner.nextLine().split("\\s+");

            contacts.addContact(contact, groupNames);

        }
    }
}
