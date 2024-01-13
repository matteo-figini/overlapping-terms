package it.personal.overlappingterms.controller;

import it.personal.overlappingterms.*;
import it.personal.overlappingterms.model.CourseEntry;
import it.personal.overlappingterms.model.OverlappingExams;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class OverlappingTerms {
    public static void main(String[] args) {

        String inputFileName;
        int overlapLevel;
        int examTermsConsidered;
        int daysConsideredForOverlapping = 2;

        List<CourseEntry> courseEntries;

        Scanner scn = new Scanner(System.in);

        System.out.print("Input file name: ");
        inputFileName = scn.nextLine();

        System.out.print("Exam terms considered: ");
        examTermsConsidered = Integer.parseInt(scn.nextLine());

        System.out.print("Level of overlap desired [1/2/3/4]: ");
        overlapLevel = Integer.parseInt(scn.nextLine());

        // Read file from CSV file
        courseEntries = InputController.readCourseEntriesFromCSV(inputFileName, true, examTermsConsidered);
        System.out.println(courseEntries.size() + " entries have been read from the file.");

        // Process the data structure and fill the overlapping list
        System.out.println("Processing data...");

        OverlapLevel level = null;
        switch (overlapLevel) {
            case 1:
                level = new OverlapLevelOne();
                break;
            case 2:
                System.out.print("Number of days considered as overlap: ");
                daysConsideredForOverlapping = Integer.parseInt(scn.nextLine());
                level = new OverlapLevelTwo(daysConsideredForOverlapping);
                break;
            case 3:
                level = new OverlapLevelThree();
                break;
            case 4:
                System.out.print("Number of days considered as overlap: ");
                daysConsideredForOverlapping = Integer.parseInt(scn.nextLine());
                level = new OverlapLevelFour(daysConsideredForOverlapping);
                break;
            default:
                System.out.println("Unable to consider the desired level of overlap: " + overlapLevel);
        }

        List<OverlappingExams> overlappingExamsList;
        if (level != null) {
            overlappingExamsList = level.computeOverlappingExams(courseEntries, examTermsConsidered);
            overlappingExamsList.sort(Comparator.comparing(OverlappingExams::getExamCallWithOverlapping)
                    .thenComparing(OverlappingExams::getFirstExamDate)
                    .thenComparing(OverlappingExams::getSecondExamDate)
            );

            // Print the list on a file and on console
            String reducedInputFileName = inputFileName.substring(0, inputFileName.length() - 4);
            String outputFileName = "overlaps_lv" + overlapLevel + "_" + reducedInputFileName +  ".txt";
            OutputController.writeOverlappingExamsToFile(outputFileName, overlappingExamsList);

            System.out.println();

            System.out.println(overlappingExamsList.size() + " overlapping exams have been found!");
            for (OverlappingExams entry : overlappingExamsList) {
                System.out.println(entry);
            }
        }
    }
}
