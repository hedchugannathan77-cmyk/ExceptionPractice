/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class ItemRecord {
    private final String recordId;
    private final String itemName;
    private final int quantity;

    public ItemRecord(String recordId, String itemName, int quantity) {
        if (recordId == null || recordId.trim().isEmpty()) {
            throw new IllegalArgumentException("Record ID cannot be blank.");
        }
        if (itemName == null || itemName.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be blank.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        this.recordId = recordId.trim();
        this.itemName = itemName.trim();
        this.quantity = quantity;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return String.format("%-12s %-22s %d", recordId, itemName, quantity);
    }
}

