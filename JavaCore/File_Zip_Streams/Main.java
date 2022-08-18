package com.company;

import java.io.*;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static ArrayList<String> saveFileList = new ArrayList<>();

    public static void main(String[] args) {

        StringBuilder history = new StringBuilder();



//Создание Папок и Файлов
        history.append(
                folderCreator("C://Games"));
        history.append(
                folderCreator("C://Games//src"));
        history.append(
                folderCreator("C://Games//res"));
        history.append(
                folderCreator("C://Games//savegames"));
        history.append(
                folderCreator("C://Games//temp"));
        fileCreator("C://Games//temp", "temp.txt");

        history.append(
                folderCreator("C://Games//src//main"));
        history.append(
                fileCreator("C://Games//src//main", "Main.java"));
        history.append(
                fileCreator("C://Games//src//main", "Utils.java"));

        history.append(
                folderCreator("C://Games//src//test"));
        history.append(
                folderCreator("C://Games//res//drawables"));
        history.append(
                folderCreator("C://Games//res//vectors"));
        history.append(
                folderCreator("C://Games//res//icons"));

        // Запись данных в файл temp.txt
        try (FileWriter tempWrite = new FileWriter("C://Games//temp//temp.txt", false)) {
            tempWrite.write(String.valueOf(history));
            tempWrite.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Считывание данных из файла temp.txt
        try (FileReader tempReader = new FileReader("C://Games//temp//temp.txt")) {
            // читаем посимвольно
            int c;
            while ((c = tempReader.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


        // Создание трех экземпляров класса GameProgress
        GameProgress Hero1 = new GameProgress(100, 1, 1, 100);
        GameProgress Hero2 = new GameProgress(150, 2, 2, 150);
        GameProgress Hero3 = new GameProgress(250, 3, 3, 250);

        saveGame("C://Games//savegames", Hero3);
        saveGame("C://Games//savegames", Hero2);
        saveGame("C://Games//savegames", Hero1);
        saveGame("C://Games//savegames", Hero3);

        zipFiles("C://Games//savegames", saveFileList);


//
//        File folderGame = new File("C://Games");
//        folderGame.mkdir();
//        history.append("\nДобавлена папка - . Директория ");
//
//
//        File folderGameSrc = new File("C://Games//src");
//        folderGameSrc.mkdirs();
//        history.append("\nДобавлена папка - . Директория ");
//
//        File folderGameSavegames = new File("C://Games//savegames");
//        folderGameSavegames.mkdirs();
//        history.append("\nДобавлена папка - . Директория ");
//
//        File folderGameTemp = new File("C://Games//temp");
//        folderGameTemp.mkdirs();
//        history.append("\nДобавлена папка - . Директория ");
//
//        File folderGameRes = new File("C://Games//res");
//        folderGameRes.mkdirs();
//        history.append("\nДобавлена папка - . Директория ");
//
//        File folderGameResIcons = new File("C://Games//res//icons");
//        folderGameResIcons.mkdirs();
//        history.append("\nДобавлена папка - . Директория ");
//
//        File folderGameResVectors = new File("C://Games//res//vectors");
//        folderGameResVectors.mkdirs();
//        history.append("\nДобавлена папка - . Директория ");
//
//        File folderGameResDrawables = new File("C://Games//res//drawables");
//        folderGameResDrawables.mkdirs();
//        history.append("\nДобавлена папка - . Директория ");
//
//        File folderGameSrcMain = new File("C://Games//src//main");
//        folderGameSrcMain.mkdirs();
//        history.append("\nДобавлена папка - . Директория ");
//
//        File fileMain = new File("C://Games//src//main//Main.java");
//        try {
//            if (fileMain.createNewFile())
//                System.out.println("Файл был создан");
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        File fileUtils = new File("C://Games//src//main//Utils.java");
//        try {
//            if (fileUtils.createNewFile())
//                System.out.println("Файл был создан");
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        File filetemp = new File("C://Games//temp//temp.txt");
//        try {
//            if (filetemp.createNewFile())
//                System.out.println("Файл был создан");
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
    }

    public static String folderCreator(String directoryName) {
        boolean prob = new File(directoryName).mkdirs();
        if(prob){
            return ("\nДобавлена папка - " + directoryName.substring(directoryName.lastIndexOf('/') + 1) + ". Директория: " + directoryName);
        } else {
            return ("\n" + directoryName.substring(directoryName.lastIndexOf('/') + 1) + " не была создана");
        }
    }

    public static String fileCreator(String directoryName, String fileName) {
        boolean prob = false;
        try {
            if (new File(directoryName, fileName).createNewFile())
                prob = true;
        } catch (IOException ex) {
            prob = false;
            System.out.println(ex.getMessage());
        }
        if(prob){
            return ("\nДобавлен фаил - " + fileName + ". Директория: " + directoryName);
        } else {
            return ("\n" + fileName + " не был создан");
        }
    }

    public static void saveGame(String saveFileDirectory, GameProgress hero) {
        //Формирование названия файла
        ++GameProgress.saveCounter;
        String saveFileName = "Save_" + GameProgress.saveCounter + ".dat";

        //Сериализация (Сохранение объекта).
        try (FileOutputStream saveFile = new FileOutputStream(saveFileDirectory + "//" +saveFileName);
             ObjectOutputStream objSaveFile = new ObjectOutputStream(saveFile)) {
            objSaveFile.writeObject(hero);
            saveFileList.add(saveFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipFiles(String zipFileDirectory, List<String> saveFileList) {

//        try {
//            FileOutputStream zipFile = new FileOutputStream("zipOfSaveFiles.zip");
//            ZipOutputStream zipOut = new ZipOutputStream(zipFile);
//            for(int i = 0; i < saveFileList.size(); i++ ) {
//
//                //выбираем фаил который будем архивировать
//                FileInputStream filesForZip = new FileInputStream(saveFileList.get(i)) {
//
//                    ZipEntry entry = new ZipEntry(saveFileList.get(i));
//                    zipOut.putNextEntry();
//                    // считываем содержимое файла в массив byte
//                    byte[] buffer = new byte[filesForZip.available()];
//                    filesForZip.read(buffer);
//                    // добавляем содержимое к архиву
//                     zipOut.write(buffer);
//                    // закрываем текущую запись для новой записи
//                    zipOut.closeEntry();
//                    };
//            }
//            zipOut.close();
//            zipFile.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try { ZipOutputStream zout = new ZipOutputStream(new FileOutputStream( zipFileDirectory + "//" + "zipOfSaveFiles.zip"));

            for(int i = 0; i < saveFileList.size(); i++ ) {
                try (//выбираем фаил который будем архивировать
                     FileInputStream filesForZip = new FileInputStream(zipFileDirectory + "//" + saveFileList.get(i))) {


                    ZipEntry entry = new ZipEntry(saveFileList.get(i));
                    zout.putNextEntry(entry);
                    // считываем содержимое файла в массив byte
                    byte[] buffer = new byte[filesForZip.available()];
                    filesForZip.read(buffer);
                    // добавляем содержимое к архиву
                    zout.write(buffer);
                    // закрываем текущую запись для новой записи
                    zout.closeEntry();
                } catch (Exception e) {
                    e.getMessage();
                }
            }
            zout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        for (String s : saveFileList){
//            System.out.println("\n" + s);
//        }
//
//        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream( zipFileDirectory + "//" + "zipOfSaveFiles.zip"));
//                //выбираем фаил который будем архивировать
//             FileInputStream filesForZip = new FileInputStream(zipFileDirectory + "//" + saveFileList.get(0))) {
//
//
//            ZipEntry entry = new ZipEntry(saveFileList.get(0));
//            zout.putNextEntry(entry);
//            // считываем содержимое файла в массив byte
//            byte[] buffer = new byte[filesForZip.available()];
//            filesForZip.read(buffer);
//            // добавляем содержимое к архиву
//            zout.write(buffer);
//            // закрываем текущую запись для новой записи
//            zout.closeEntry();
//        } catch (Exception e) {
//            e.getMessage();
//        }

    }



}