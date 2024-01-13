package it.personal.overlappingterms.controller;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import it.personal.overlappingterms.model.CourseEntry;
import it.personal.overlappingterms.model.ExamDate;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InputController {

    public static List<CourseEntry> readCourseEntriesFromCSV (String filePath, boolean yearConsidered, int examTerms) {
        List<CourseEntry> courseEntryList = new ArrayList<>();
        CSVReader csvReader = null;

        final String dateFormat = "dd/MM/yyyy";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(';')
                .withIgnoreQuotations(true)
                .build();
        int recordPrefixedLength = 3 + examTerms * 2;
        if (yearConsidered) {
            recordPrefixedLength++;
        }

        try {
            csvReader = new CSVReaderBuilder(new FileReader(filePath))
                    .withSkipLines(0)
                    .withCSVParser(parser)
                    .build();

            String[] record;
            while ((record = csvReader.readNext()) != null) {
                if (record.length == recordPrefixedLength) {
                    // This is needed to avoid IndexOutOfBound
                    CourseEntry entry;
                    List<ExamDate> examDates = new ArrayList<>();
                    for (int i = (yearConsidered ? 4 : 3); i < record.length; i++) {
                        LocalDate date = LocalDate.parse(record[i++], formatter);
                        String timeSlot = record[i];
                        examDates.add(new ExamDate(date, timeSlot));
                    }
                    if (yearConsidered) {
                        entry = new CourseEntry(
                                record[0],
                                Integer.parseInt(record[1]),
                                Integer.parseInt(record[2]),
                                (record[3].equalsIgnoreCase("S")),
                                examDates
                        );
                    } else {
                        entry = new CourseEntry(
                                record[0],
                                Integer.parseInt(record[1]),
                                (record[2].equalsIgnoreCase("S")),
                                examDates
                        );
                    }
                    courseEntryList.add(entry);
                } else {
                    System.out.println("ERROR: Record out of length!");
                }
            }

            csvReader.close();

        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }

        return courseEntryList;
    }
}
