/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExceptionPracticej;

/**
 *
 * @author jude
 */
import java.util.ArrayList;

import java.util.Scanner;

 

// Custom exception for duplicate record IDs

class DuplicateRecordException extends Exception {

    public DuplicateRecordException(String message) {

        super(message);

    }

}

 

// Class representing one record

class ItemRecord {

    private final String recordId;

    private final String name;

    private final int quantity;

 

    public ItemRecord(String recordId, String name, int quantity) {

        if (recordId == null || recordId.trim().isEmpty()) {

            throw new IllegalArgumentException(

                "Record ID cannot be blank.");

        }

 

        if (name == null || name.trim().isEmpty()) {

            throw new IllegalArgumentException(

                "Name cannot be blank.");

        }

 

        if (quantity < 1) {

            throw new IllegalArgumentException(

                "Quantity must be at least 1.");

        }

 

        this.recordId = recordId.trim();

        this.name = name.trim();

        this.quantity = quantity;

    }

 

    public String getRecordId() {

        return recordId;

    }

 

    public String getName() {

        return name;

    }

 

    public int getQuantity() {

        return quantity;

    }

}

 

// Main console application

public class ExceptionSafeRecordValidator {

    private static final Scanner input = new Scanner(System.in);

    private static final ArrayList<ItemRecord> records =

        new ArrayList<>();

 

    public static void main(String[] args) {

        try (input) {
            boolean running = true;
            
            
            
            System.out.println("=== Exception-Safe Record Validator ===");
            
            
            
            while (running) {
                
                System.out.println(" 1. Add Record");
                
                System.out.println("2. View Records");
                
                System.out.println("3. Exit");
                
                System.out.print("Enter your choice: ");
                
                
                
                String choice = input.nextLine().trim();
                
                
                
                switch (choice) {
                    
                    case "1" -> addRecord();
                        
                    case "2" -> displayRecords();
                        
                    case "3" -> {
                        running = false;
                        
                        System.out.println("Program closed safely.");
                    }
                        
                    default -> System.out.println(
                                
                                "Invalid choice. Please enter 1, 2, or 3.");
                        
                }
                
            }
        }

    }

 

    private static void addRecord() {

        System.out.print("Enter Record ID: ");

        String id = input.nextLine();

 

        System.out.print("Enter Name: ");

        String name = input.nextLine();

 

        System.out.print("Enter Quantity: ");

        String quantityText = input.nextLine();

 

        try {

            // Convert text to an integer

            int quantity = Integer.parseInt(quantityText.trim());

 

            // Validate values before accepting the record

            ItemRecord record = new ItemRecord(id, name, quantity);

 

            // Check for duplicate IDs

            for (ItemRecord existing : records) {

                if (existing.getRecordId().equalsIgnoreCase(

                        record.getRecordId())) {

                    throw new DuplicateRecordException(

                        "Record ID already exists. Use another ID.");

                }

            }

 

            // Store only valid, nonduplicate records

            records.add(record);

            System.out.println("Record added successfully.");

 

        } catch (NumberFormatException e) {

            System.out.println(

                "Error: Quantity must be a whole number, such as 5.");

 

        } catch (IllegalArgumentException e) {

            System.out.println("Validation error: " + e.getMessage());

 

        } catch (DuplicateRecordException e) {

            System.out.println("Duplicate error: " + e.getMessage());

        }

 

        System.out.println(

            "The program is still running. You may try again.");

    }

 

    private static void displayRecords() {

        if (records.isEmpty()) {

            System.out.println("No records have been added.");

            return;

        }

 

        System.out.println("\\n--- Accepted Records ---");

 

        for (ItemRecord record : records) {

            System.out.println(

                "ID: " + record.getRecordId()

                + " | Name: " + record.getName()

                + " | Quantity: " + record.getQuantity());

        }

 

        System.out.println("Total records: " + records.size());

    }

}