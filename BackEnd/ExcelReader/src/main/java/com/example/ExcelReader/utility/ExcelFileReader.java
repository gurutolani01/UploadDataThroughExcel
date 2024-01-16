
package com.example.ExcelReader.utility;

import com.example.ExcelReader.entity.Candidate;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelFileReader {

    public static List<Candidate> readCandidatesFromExcel(InputStream fileInputStream) throws IOException {
        List<Candidate> candidates = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(fileInputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // Skip header row

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Candidate candidate = new Candidate();

                Cell nameCell = row.getCell(0);
                Cell emailCell = row.getCell(1);
                Cell phoneNumberCell = row.getCell(2);

                candidate.setName(getCellValueAsString(nameCell));
                candidate.setEmail(getCellValueAsString(emailCell));
                candidate.setPhoneNumber(getCellValueAsString(phoneNumberCell));

                candidates.add(candidate);
//                Row row = rowIterator.next();
//                Candidate candidate = new Candidate();
//
//                candidate.setName(row.getCell(0).getStringCellValue());
//                candidate.setEmail(row.getCell(1).getStringCellValue());
//                String phoneNum = String.valueOf((row.getCell(2).getNumericCellValue()));
//                candidate.setPhoneNumber(phoneNum);
//
//                candidates.add(candidate);
            }
        }

        return candidates;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return null;
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            // Handle other cell types if needed
            default:
                return null;
        }
    }
}

