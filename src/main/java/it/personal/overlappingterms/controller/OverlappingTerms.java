package it.personal.overlappingterms.controller;

import it.personal.overlappingterms.model.CourseEntry;
import it.personal.overlappingterms.model.OverlappingExams;

import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class OverlappingTerms {
    public static void main(String[] args) {

        Integer yearsConsidered;
        Integer examTermsConsidered;
        Integer maxDistanceForOverlapping;

        List<CourseEntry> courseEntries;
        List<OverlappingExams> overlappingExamsList = new ArrayList<>();

        Scanner scn = new Scanner(System.in);

        System.out.print("Years considered: ");
        yearsConsidered = Integer.parseInt(scn.nextLine());

        System.out.print("Exam terms considered: ");
        examTermsConsidered = Integer.parseInt(scn.nextLine());

        System.out.print("Maximum number of days considered for overlapping: ");
        maxDistanceForOverlapping = Integer.parseInt(scn.nextLine());

        // Read file from CSV file
        courseEntries = InputController.readCourseEntriesFromCSV(true, 2);
        System.out.println(courseEntries.size() + " entries have been read from the file.");

        // Process the data structure and fill the overlapping list
        System.out.println("Processing data...");
        for (int year = 1; year <= yearsConsidered; year++) {
            System.out.println(year + "° YEAR");
            for (int examCall = 0; examCall < examTermsConsidered; examCall++) {
                System.out.println((examCall + 1) + "° EXAM CALL");
                for (int i = 0; i < courseEntries.size(); i++) {
                    CourseEntry examOne = courseEntries.get(i);

                    if (examOne.getCourseYear().equals(year)) {
                        LocalDate dateOfFirstExam = examOne.getExamTermsDates().get(examCall);

                        for (int j = i + 1; j < courseEntries.size(); j++) {
                            CourseEntry examTwo = courseEntries.get(j);
                            LocalDate dateOfSecondExam = examTwo.getExamTermsDates().get(examCall);
                            long daysOfDifference = Math.abs(DAYS.between(dateOfFirstExam, dateOfSecondExam));

                            if (examOne.getCourseYear().equals(examTwo.getCourseYear()) &&
                                    daysOfDifference <= (long) maxDistanceForOverlapping) {
                                OverlappingExams overlappingExams = new OverlappingExams(
                                        examOne.getCourseName(),
                                        examOne.isMandatory(),
                                        examTwo.getCourseName(),
                                        examTwo.isMandatory(),
                                        daysOfDifference,
                                        year,
                                        (Objects.equals(examOne.getCourseSemester(), examTwo.getCourseSemester()))
                                );
                                System.out.println(overlappingExams);
                                overlappingExamsList.add(overlappingExams);
                            }
                        }
                    }
                }
            }
        }

        // Order the data structure
        overlappingExamsList.sort(Comparator.comparing(OverlappingExams::getYear)
                .thenComparing(OverlappingExams::getDaysOfDistanceBetweenCalls));

        System.out.println();

        // Print the list on a file and on console
        OutputController.writeOverlappingExamsToFile("overlaps.txt", overlappingExamsList);

        System.out.println(overlappingExamsList.size() + " overlapping exams have been found!");
        for (OverlappingExams entry : overlappingExamsList) {
            System.out.println(entry);
        }
    }
}
