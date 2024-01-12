package it.personal.overlappingterms.controller;

import it.personal.overlappingterms.*;
import it.personal.overlappingterms.model.CourseEntry;
import it.personal.overlappingterms.model.OverlappingExams;

import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class OverlappingTerms {
    public static void main(String[] args) {

        String inputFileName;
        Integer overlapLevel;
        Integer examTermsConsidered;

        List<CourseEntry> courseEntries;

        Scanner scn = new Scanner(System.in);

        System.out.print("Input file name: ");
        inputFileName = scn.nextLine();

        System.out.print("Exam terms considered: ");
        examTermsConsidered = Integer.parseInt(scn.nextLine());

        System.out.print("Level of overlap desired [1/2/3/4]: ");
        overlapLevel = Integer.parseInt(scn.nextLine());

        // Read file from CSV file
        courseEntries = InputController.readCourseEntriesFromCSV(true, examTermsConsidered);
        System.out.println(courseEntries.size() + " entries have been read from the file.");

        // Process the data structure and fill the overlapping list
        System.out.println("Processing data...");

        OverlapLevel level = null;
        switch (overlapLevel) {
            case 1:
                level = new OverlapLevelOne();
                break;
            case 2:
                level = new OverlapLevelTwo();
                break;
            case 3:
                level = new OverlapLevelThree();
                break;
            case 4:
                level = new OverlapLevelFour();
                break;
            default:
                System.out.println("Unable to consider the desired level of overlap: " + overlapLevel);
        }

        List<OverlappingExams> overlappingExamsList = new ArrayList<>();
        if (level != null) {
            overlappingExamsList = level.computeOverlappingExams(courseEntries, examTermsConsidered);
            overlappingExamsList.sort(Comparator.comparing(OverlappingExams::getExamCallWithOverlapping));
        }
        System.out.println();

        // Print the list on a file and on console
        OutputController.writeOverlappingExamsToFile("overlaps.txt", overlappingExamsList);

        System.out.println(overlappingExamsList.size() + " overlapping exams have been found!");
        for (OverlappingExams entry : overlappingExamsList) {
            System.out.println(entry);
        }
    }
}
