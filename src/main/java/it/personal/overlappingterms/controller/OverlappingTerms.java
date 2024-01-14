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
        int daysConsideredForOverlapping;

        boolean orderFirstByName = false;

        for (String arg : args) {
            if (arg.equalsIgnoreCase("--help") || arg.equalsIgnoreCase("-h")) {
                showHelpMenu();
            } else if (arg.equalsIgnoreCase("--name") || arg.equalsIgnoreCase("-n")) {
                // Order the list of exams first by name
                orderFirstByName = true;
            }
        }

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
            String repeatedOverlaps = null;

            if (!orderFirstByName) {
                overlappingExamsList.sort(Comparator.comparing(OverlappingExams::getExamCallWithOverlapping)
                        .thenComparing(OverlappingExams::getFirstExamDate)
                        .thenComparing(OverlappingExams::getSecondExamDate)
                );
            } else {
                overlappingExamsList.sort(Comparator.comparing(OverlappingExams::getFirstExamName)
                        .thenComparing(OverlappingExams::getSecondExamName)
                        .thenComparing(OverlappingExams::getFirstExamDate)
                        .thenComparing(OverlappingExams::getSecondExamDate)
                );
                repeatedOverlaps = findRepeatedOverlap (overlappingExamsList);
            }

            // Print the list on a file and on console
            String reducedInputFileName = inputFileName.substring(0, inputFileName.length() - 4);
            String outputFileName = "overlaps_lv" + overlapLevel + "_" + reducedInputFileName +  ".txt";
            OutputController.writeOverlappingExamsToFile(outputFileName, overlappingExamsList, repeatedOverlaps);

            System.out.println();

            System.out.println(overlappingExamsList.size() + " overlapping exams have been found!");
            for (OverlappingExams entry : overlappingExamsList) {
                System.out.println(entry);
            }
        }
    }

    /**
     * Show the help menu.
     */
    private static void showHelpMenu () {
        System.out.println("HELP MENU");
        System.out.println("LEVELS:");
        System.out.println("1: All and only the exams with different names belonging to the same year and the same semester, " +
                "that are set as \"mandatory\" and have a distance of two days or less between the first exam term and " +
                "the second exam term.");
        System.out.println("2. All and only the exams, mandatory or not, with different names, that belong to the same year " +
                "and the same semester and have a distance in days between the first exam term and the second exam term " +
                "lower or equal to a value specified by the user.");
        System.out.println("3. All and only the exams, mandatory or not, with different names, regardless of the year " +
                "and the semester, that are scheduled on the same date and in the same time slot.");
        System.out.println("4. All and only the exams, mandatory or not, with different names, regardless of the year " +
                "and the semester, that are scheduled with a distance in days between the first exam term and the " +
                "second exam term lower or equal to a value specified by the user.");
    }

    /**
     * Finds if there are repeated overlaps between two exams among the sessions.
     * Requires that the list of the overlapping exams is sorted by the name of the two overlapping exams.
     * @param overlappingExamsList List of overlapping exams
     * @return A string containing the repeated overlaps.
     */
    private static String findRepeatedOverlap (List<OverlappingExams> overlappingExamsList) {
        StringBuilder repeatedOverlaps = new StringBuilder();
        for (int i = 0; i < overlappingExamsList.size() - 1; i++) {
            OverlappingExams firstElement = overlappingExamsList.get(i);
            OverlappingExams secondElement = overlappingExamsList.get(i+1);
            if (firstElement.getFirstOverlappingCourse().getCourseName().equals(
                    secondElement.getFirstOverlappingCourse().getCourseName()) &&
                    firstElement.getSecondOverlappingCourse().getCourseName().equals(
                            secondElement.getSecondOverlappingCourse().getCourseName())
            ) {
                repeatedOverlaps.append("ATTENZIONE! Questi due esami si sovrappongono in più di una sessione:\n");
                repeatedOverlaps.append("\t").append(firstElement).append("\n");
                repeatedOverlaps.append("\tsi sovrappone con\n");
                repeatedOverlaps.append("\t").append(secondElement).append("\n");
            }
        }
        return repeatedOverlaps.toString();
    }
}
