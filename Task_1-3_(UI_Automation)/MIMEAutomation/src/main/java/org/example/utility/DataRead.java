package org.example.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataRead {
    public static String userCreationfilePath = "userCreate.csv";
    public static String ticketCreationfilePath = "ticketCreation.csv";

    public static List<String[]> readUserDataFromCSV() {
        List<String[]> userData = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(userCreationfilePath))) {
            // Skip header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1); // include empty fields
                userData.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(userData);
        return userData;
    }

    public static List<String[]> readTicketDataFromCSV() {
        List<String[]> userData = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(ticketCreationfilePath))) {
            // Skip header line
//            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1); // include empty fields
                userData.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(userData);
        return userData;
    }

}
