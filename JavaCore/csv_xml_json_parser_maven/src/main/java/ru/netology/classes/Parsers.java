package ru.netology.classes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Parsers {
    public static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);
            CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();
            return csvToBean.parse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Employee> parseXML(String fileName) {
        List<Employee> result = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fileName);
            Element staff = document.getDocumentElement();
            NodeList nodeList = staff.getElementsByTagName("employee");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                result.add(getEmployeeByElement(element));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Employee getEmployeeByElement(Element element) {
        return new Employee(
                Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()),
                element.getElementsByTagName("firstName").item(0).getTextContent(),
                element.getElementsByTagName("lastName").item(0).getTextContent(),
                element.getElementsByTagName("country").item(0).getTextContent(),
                Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent())
        );
    }

    public static String listToJson(List<Employee> list) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting() //чтоб JSON был не в одну строку =)
                .create();
        return gson.toJson(list, new TypeToken<List<Employee>>() {
        }.getType());
    }

    public static List<Employee> jsonToList(String json) {
        Gson gson = new GsonBuilder().create();
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(json);
            List<Employee> result = new ArrayList<>();
            for (Object o : jsonArray) {
                Employee employee = gson.fromJson(o.toString(), Employee.class);
                result.add(employee);
            }
            return result;
        } catch (NullPointerException | ParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}