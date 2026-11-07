/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.ItemRecord;
import exception.DuplicateRecordException;

public class RecordManager {

    private final List<ItemRecord> records = new ArrayList<>();

    public void addRecord(ItemRecord record)
              throws DuplicateRecordException {

        for (ItemRecord existing : records) {

            if (existing.getRecordId().equalsIgnoreCase(
                    record.getRecordId())) {

                throw new DuplicateRecordException(
                    "Duplicate record ID: " + record.getRecordId()
                );
            }
        }

        records.add(record);
    }

    public List<ItemRecord> getAllRecords() {
        return Collections.unmodifiableList(records);
    }

    public boolean hasRecords() {
        return !records.isEmpty();
    }
}