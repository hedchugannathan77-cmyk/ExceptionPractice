/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package app;

import exception.DuplicateRecordException;
import model.ItemRecord;
import service.RecordManager;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final RecordManager manager = new RecordManager();

    public static void main(String[] args) throws DuplicateRecordException {

        try (scanner) {
            boolean running = true;
            
            System.out.println("==================================");
            System.out.println("     EXCEPTION-SAFE RECORDS");
            System.out.println("==================================");
            
            while (running) {
                
                System.out.println("\n1. Add Record");
                System.out.println("2. View Accepted Records");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                
                String choice = scanner.nextLine().trim();
                
                switch (choice) {
                    case "1" -> addRecord();
                        
                    case "2" -> displayRecords();
                        
                    case "3" -> {
                        running = false;
                        System.out.println("Program closed safely. Goodbye!");
                    }
                        
                    default -> System.out.println(
                                "Invalid menu choice. Please enter 1, 2, or 3."
                        );
                }
            }
        }
    }

    private static void addRecord() throws DuplicateRecordException {

        try {
            System.out.print("Enter Record ID: ");
            String id = scanner.nextLine();

            if (id.trim().isEmpty()) {
                throw new IllegalArgumentException(
                    "Record ID cannot be blank."
                );
            }

            System.out.print("Enter Item Name: ");
            String name = scanner.nextLine();

            if (name.trim().isEmpty()) {
                throw new IllegalArgumentException(
                    "Item name cannot be blank."
                );
            }

            System.out.print("Enter Quantity: ");
            String quantityInput = scanner.nextLine().trim();

            int quantity;

            try {
                quantity = Integer.parseInt(quantityInput);

            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                    "Invalid quantity. Please enter a whole number."
                );
            }

            if (quantity < 1) {
                throw new IllegalArgumentException(
                    "Quantity must be greater than zero."
                );
            }

            ItemRecord record = new ItemRecord(id, name, quantity);

            manager.addRecord(record);

            System.out.println(
                "SUCCESS: Record added successfully."
            );

        } catch (IllegalArgumentException e) {

            System.out.println("INPUT ERROR: " + e.getMessage());
            System.out.println(
                "Please try again. The program is still running."
            );

        } catch (Exception e) {

            System.out.println("UNEXPECTED ERROR: " + e.getMessage());
            System.out.println("The program will continue running.");
        }
    }

    private static void displayRecords() {

        System.out.println("\n========== ACCEPTED RECORDS ==========");

        if (!manager.hasRecords()) {
            System.out.println("No accepted records yet.");
            return;
        }

        System.out.printf(
            "%-12s %-22s %s%n",
            "Record ID",
            "Item Name",
            "Quantity"
        );

        System.out.println("----------------------------------------------");

        for (ItemRecord record : manager.getAllRecords()) {
            System.out.println(record);
        }
    }
}
