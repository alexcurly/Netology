package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {

    private Map< String, List<Contact> > storage = new HashMap< String , List<Contact>>();
    // Добавим группы контактов
    public boolean addGroup(String groupName) {
        if(storage.containsKey(groupName)) {
            return false;
        }
        storage.put(groupName, new ArrayList<>());
        return true;
    }

    // Добавим контакты
    public boolean addContact(Contact contact, String[] groupNames) {
        for (String groupName : groupNames) {
            if (!storage.containsKey(groupName)) {
                return false;
            }
            final List<Contact> groupContactList = storage.get(groupName);
            groupContactList.add(contact);
        }
        return true;
    }

    // Вывести все на экран

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(DEFAULT_LENGTH_PER_CONTACT * storage.size());
        for (Map.Entry<String, List<Contact>> groupContacts : storage.entrySet()) {
            sb
                        .append(groupContacts.getKey())
                        .append("\n");
            for (Contact contact : groupContacts.getValue()) {
                sb
                        .append("\t")
                        .append(contact)
                        .append("\n");
            }
        }
        return sb.toString();
    }

    private static final int DEFAULT_LENGTH_PER_CONTACT = 60;
}



