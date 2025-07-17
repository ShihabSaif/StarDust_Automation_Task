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

    // Main method
//    public static void main(String[] args) throws InterruptedException {
//        List<String[]> users = readUserDataFromCSV();
//
//        for (int i = 0; i < users.size(); i++) {
//            String[] user = users.get(i);
//            System.out.println("User " + (i + 1) + ":");
//            System.out.println("  Email      : " + user[0]);
//            System.out.println("  Username   : " + user[1]);
//            System.out.println("  Password   : " + user[2]);
//            System.out.println("  ConfirmPwd : " + user[3]);
//            System.out.println("  First Name : " + user[4]);
//            System.out.println("  Last Name  : " + user[5]);
//            System.out.println("  Dept ID    : " + user[6]);
//            System.out.println("  Role       : " + user[7]);
//            System.out.println("  Address    : " + user[8]);
//            System.out.println("  Phone      : " + user[9]);
//            System.out.println("-------------------------------------------------");
//        }
//    }

}
