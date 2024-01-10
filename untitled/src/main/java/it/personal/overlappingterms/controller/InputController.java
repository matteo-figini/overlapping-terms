package it.personal.overlappingterms.controller;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import it.personal.overlappingterms.model.CourseEntry;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InputController {

    public static List<CourseEntry> readCourseEntriesFromCSV (boolean yearConsidered, int examTerms) {
        List<CourseEntry> courseEntryList = new ArrayList<>();
        // TODO: give the possibility to parametrize the path
        String filePath = "C:\\Users\\matte\\Desktop\\exam_terms.csv";
        CSVReader csvReader = null;

        final String dateFormat = "dd/MM/yyyy";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(';')
                .withIgnoreQuotations(true)
                .build();
        int recordPrefixedLength = 3 + examTerms;
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
                    List<LocalDate> examDates = new ArrayList<>();
                    for (int i = (yearConsidered ? 4 : 3); i < record.length; i++) {
                        LocalDate date = LocalDate.parse(record[i], formatter);
                        examDates.add(date);
                    }

                    if (yearConsidered) {
                        entry = new CourseEntry(
                                    record[0],
                                    Integer.parseInt(record[1]),
                                    Integer.parseInt(record[2]),
                                    (Objects.equals(record[3], "S")),
                                    examDates);
                    } else {
                        entry = new CourseEntry(
                                record[0],
                                Integer.parseInt(record[1]),
                                (Objects.equals(record[2], "S")),
                                examDates);
                    }
                    courseEntryList.add(entry);
                } else {
                    System.out.println("Record out of length!");
                }
            }

            csvReader.close();

        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }

        return courseEntryList;
    }
}
