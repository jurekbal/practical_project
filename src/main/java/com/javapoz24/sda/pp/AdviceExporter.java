package com.javapoz24.sda.pp;

import com.javapoz24.sda.pp.model.Slip;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

public class AdviceExporter {

    private final String path = PropertiesManager.getProperty("dirPath");

    public void exportToTextFile(List<Slip> allAdvices) {
        try  {
            PrintWriter fileWriter = new PrintWriter(path+"cytaty.txt");

            for (Slip slip : allAdvices) {
                fileWriter.println("| Slip Id: " + slip.getSlipId() + " | Id: " + slip.getId() +  " | Advice: " + slip.getAdvice() + " |");
            }

            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //eksport do eksela
    public void exportToSheet(List<Slip> allAdvice) {
        Workbook workbook = new XSSFWorkbook(); //utworzenie workbooka (xlsx)

        Sheet sheet = workbook.createSheet("My favourite advices"); //utworzenie arkuszu

        //naglowki tabeli
        String[] columns = {"slipId", "id", "advice"};
        createHeaderRow(columns, sheet);

        //wypelnienie danymi
        createDataRows(sheet, allAdvice);

        //resize column tak żeby pasowały do zawartosci
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        //zapis do pliku
        saveWorkbookToFile(workbook);
    }

    private void createHeaderRow(String[] columns, Sheet sheet) {
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }
    }

    private void createDataRows(Sheet sheet, List<Slip> slips) {
        int rowNumber = 1;

        for (Slip slip : slips) {
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(slip.getSlipId());
            row.createCell(1).setCellValue(slip.getId());
            row.createCell(2).setCellValue(slip.getAdvice());
        }
    }

    private void saveWorkbookToFile(Workbook workbook) {
        //zapis do pliku!
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path + "cytaty.xlsx");
            workbook.write(fileOutputStream);

            fileOutputStream.close();
            workbook.close();
        } catch (IOException e) {
            System.out.println("Nie udało się weksportować cytatów!");
        }
    }
}
